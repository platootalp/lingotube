<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-2 bg-gradient-success">
    </base-header>

    <b-container fluid class="p-4 bg-white">
      <card class="p-4 bg-dark mb-4" header-bg-variant="dark">
        <b-card-title>
          <h1 class="mb-3 text-white">{{ this.categoryInfo.name }}</h1>
          <span class="text-secondary">{{ this.categoryInfo.description }}</span>
        </b-card-title>
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
import {getCategoryInfo, getPageVideosByCategoryId} from "@/api/video";

export default {
  name: "category",
  props: ['id'],
  data() {
    return {
      categoryInfo: '',
      videos: [],
      total: 0, // 总条数
      currentPage: 1, // 当前页码
      pageSize: 4, // 每页显示的视频数量
      sort: 1,// 排序方式
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
    this.fetchCategoryInfo();
    this.fetchPageVideosByCategoryId();
    // 监听路由变化，在路由变化时重新获取视频信息
    this.$watch('$route', () => {
      this.fetchCategoryInfo();
      this.fetchPageVideosByCategoryId();
    });
  },
  mounted() {
  },
  methods: {
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
      this.fetchPageVideosByCategoryId();
    },
    async fetchCategoryInfo() {
      try {
        const response = await getCategoryInfo(this.id);
        // 检查返回的状态码
        if (response.data.code === 200) {
          this.categoryInfo = response.data.data;
        } else {
          console.error(`获取失败: ${response.data.message}`);
        }
      } catch (error) {
        console.error('未知错误:', error);
      }
    },
    async fetchPageVideosByCategoryId() {
      try {
        const response = await getPageVideosByCategoryId(this.id, this.currentPage, this.pageSize, this.sort);
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
