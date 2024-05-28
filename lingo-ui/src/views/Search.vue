<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-2 bg-gradient-success">
    </base-header>

    <b-container fluid class="p-4 bg-white">
      <card class="p-4 bg-dark mb-4" header-bg-variant="dark">
        <b-card-title>
          <h1 class="mb-3 text-white">关于{{ this.$route.query.q }}的搜索结果</h1>
          <b-button variant="dark" class="ni ni-ungroup"
                    @click="toggleFilters">
            <span>筛选</span>
          </b-button>
        </b-card-title>

        <!-- 过滤表单 -->
        <b-collapse v-model="showFilters" id="filters-collapse" visible>
          <b-card>
            <b-form @submit.prevent="applyFilters">
              <b-form-group label="排序方式">
                <b-form-radio-group v-model="filters.sort" :options="sortOptions"></b-form-radio-group>
              </b-form-group>
              <b-form-group label="视频时长">
                <b-form-checkbox-group v-model="filters.duration" :options="durationOptions"></b-form-checkbox-group>
              </b-form-group>
              <b-form-group label="视频分类">
                <b-form-checkbox-group v-model="filters.category" :options="categoryOptions"></b-form-checkbox-group>
              </b-form-group>
              <b-form-group label="视频分级">
                <b-form-checkbox-group v-model="filters.level" :options="levelOptions"></b-form-checkbox-group>
              </b-form-group>

              <div class="text-right">
                <b-button variant="primary" @click="applyFilters">应用筛选</b-button>
              </div>
            </b-form>
          </b-card>
        </b-collapse>

        <b-card-body>

        </b-card-body>
        <b-container class="p-4 bg-dark">
          <b-card-group deck>
            <!-- 使用 v-for 遍历每行视频组 -->
            <b-row v-for="(row, rowIndex) in groupedVideos" :key="rowIndex" class="mb-4">
              <!-- 使用 v-for 遍历每个视频 -->
              <b-col v-for="(video, index) in row" :key="index" class="custom-col">
                <router-link :to="`/video/${video.id}/${video.levelName}`" class="page-link bg-dark"
                             style="border: none;">
                  <b-card :img-src="video.thumbnailUrl" :img-alt="video.alt" img-top>
                    <b-card-body>{{ video.title }}</b-card-body>
                    <b-card-footer>
                      <div class="content">
                        <i class="ni ni-glasses-2">
                          <span class="ml-2">{{ formatNumber(video.views) }}</span>
                        </i>
                        <span class="level">{{ video.levelName }}</span>
                      </div>
                    </b-card-footer>
                  </b-card>
                </router-link>
              </b-col>
            </b-row>
          </b-card-group>
        </b-container>

        <div>
          <!-- 分页组件 -->
          <b-pagination
            v-model="currentPage"
            :total-rows="total"
            :per-page="pageSize"
            aria-controls="video-list"
            @change="handlePageChange"
            align="center"
          ></b-pagination>
        </div>
      </card>
    </b-container>

  </div>
</template>

<script>

import {search} from "@/api/search";

export default {
  name: "search",
  props: ['id'],
  data() {
    return {
      showFilters: false,
      filters: {
        sort: '',
        duration: [],
        category: [],
        level: []
      },
      sortOptions: [
        {text: '上传时间', value: '1'},
        {text: '播放量', value: '2'},
        {text: '时长', value: '3'}
      ],
      durationOptions: [
        {text: '0-3 分钟', min: 0, max: 180, value: '0-3'},
        {text: '3-10 分钟', min: 181, max: 600, value: '3-10'},
        {text: '10 分钟以上', min: 601, max: null, value: '10+'}
      ],
      categoryOptions: [
        {text: '动画', value: '动画'},
        {text: '音乐', value: '音乐'},
        {text: '儿童', value: '儿童'},
        {text: '学习资源', value: '学习资源'},
        {text: '科学技术', value: '科学技术'},
        {text: '经济金融', value: '经济金融'},
        {text: '艺术娱乐', value: '艺术娱乐'},
        {text: '休闲旅行', value: '休闲旅行'},
        {text: '健康福祉', value: '健康福祉'},
        {text: '新闻时事', value: '新闻时事'}
      ],
      levelOptions: [
        {text: 'A1', value: 'A1'},
        {text: 'A2', value: 'A2'},
        {text: 'B1', value: 'B1'},
        {text: 'B2', value: 'B2'},
        {text: 'C1', value: 'C1'},
        {text: 'C2', value: 'C2'}
      ],
      params: {
        key: '',
        levels: [],
        categories: [],
        minDuration: '0',
        maxDuration: null,
        sortBy: '1',
        pageNum: '1',
        pageSize: '4'
      },
      videos: [],
      total: 0, // 总条数
      currentPage: 1, // 当前页码
      pageSize: 4, // 每页显示的视频数量
    }
  },
  computed: {
    groupedVideos() {
      // 每行显示的视频数量
      const videosPerRow = 2;
      // 将视频数组按照每行显示的数量进行分组
      return this.videos.reduce((acc, video, index) => {
        const rowIndex = Math.floor(index / videosPerRow);
        if (!acc[rowIndex]) {
          acc[rowIndex] = [];
        }
        acc[rowIndex].push(video);
        return acc;
      }, []);
    }
  },
  created() {
    this.search();
    // 监听路由变化，在路由变化时重新获取视频信息
    this.$watch('$route', () => {
      this.search();
    });
  },
  mounted() {
  },
  methods: {
    toggleFilters() {
      this.showFilters = !this.showFilters;
    },
    formatNumber(num) {
      if (num >= 10000) {
        return (num / 10000).toFixed(1) + '万';
      } else {
        return num;
      }
    },
    handlePageChange(page) {
      this.currentPage = page;
      console.log('currentPage:', this.currentPage);
      // 调用获取分页视频数据的方法
      this.search();
    },
    applyFilters() {
      // 将 levels 和 categories 转换为字符串
      this.params.levels = this.filters.level.join(',');
      this.params.categories = this.filters.category.join(',');

      // 视频时长筛选
      if (this.filters.duration.length > 0) {
        const selectedDuration = this.filters.duration[0]; // 假设只有一个时长被选中
        const durationOption = this.durationOptions.find(du => du.value === selectedDuration);
        if (durationOption) {
          this.params.minDuration = durationOption.min;
          this.params.maxDuration = durationOption.max;
        }
      } else {
        // 如果没有选择时长，则设置默认值
        this.params.minDuration = '0';
        this.params.maxDuration = null;
      }

      // 排序方式
      if (this.filters.sort) {
        this.params.sortBy = this.filters.sort;
      } else {
        // 如果没有选择排序方式，则设置默认值
        this.params.sortBy = '1';
      }
      console.log(this.params);
      this.search();
    },
    async search() {
      try {
        this.params.key = this.$route.query.q;
        this.params.pageNum = this.currentPage;
        this.params.pageSize = this.pageSize;
        const response = await search(this.params);
        // 检查返回的状态码
        if (response.data.code === 200) {
          this.videos = response.data.data.list;
          this.total = response.data.data.total;
        } else {
          console.error(`获取失败: ${response.data.message}`);
        }
      } catch (error) {
        console.error('未知错误:', error);
      }
    },
  }
}
</script>

<style scoped>
.content {
  display: flex; /* 内部元素也使用 Flex 布局 */
  justify-content: space-between; /* 左右对齐 */
  width: 100%; /* 确保内容占满整个容器 */
}

/* 自定义样式 */
.custom-col {
  /* 设置每个列的最大宽度 */
  max-width: 50%; /* 每行最多显示两个卡片，即一半的宽度 */
}
</style>
