<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
    </base-header>

    <!-- 轮播图 -->
    <b-container fluid class="mt--7">
      <template>
        <div class="carousel-container">
          <b-carousel
            id="carousel-fade"
            v-model="slide"
            :interval="4000"
            fade
            controls
            indicators
            background="#ababab"
            img-width="1024"
            img-height="240"
            style="text-shadow: 1px 1px 2px #333;"
            @sliding-start="onSlideStart"
            @sliding-end="onSlideEnd"
          >
            <!-- 使用v-for循环生成幻灯片 -->
            <b-carousel-slide v-for="(slide, index) in slides" :key="index">
              <template v-slot:img>
                <img
                  class="d-block img-fluid w-100"
                  :src="slide.imageSrc"
                  :alt="slide.alt"
                >
              </template>
            </b-carousel-slide>
          </b-carousel>
        </div>
      </template>
    </b-container>
    <!-- 间隔   -->
    <b-container fluid class="p-4 bg-white">
      <card>
      </card>
    </b-container>

    <b-container fluid class="p-4 bg-white">
      <card class="p-4 bg-dark mb-4" header-bg-variant="dark">
        <h3 slot="header" class="mb-0 text-white">热门视频</h3>
        <b-container class="p-4 bg-dark">
          <b-card-group deck>
            <!-- 使用 v-for 遍历每行视频组 -->
            <b-row v-for="(row, rowIndex) in groupedTrendingVideos" :key="rowIndex" class="mb-4">
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
      </card>

      <card class="p-4 bg-dark mb-4" header-bg-variant="dark">
        <h3 slot="header" class="mb-0 text-white">最新视频</h3>
        <b-container class="p-4 bg-dark">
          <b-card-group deck>
            <!-- 使用 v-for 遍历每行视频组 -->
            <b-row v-for="(row, rowIndex) in groupedLatestVideos" :key="rowIndex" class="mb-4">
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
      </card>

      <card class="p-4 bg-dark mb-4" header-bg-variant="dark">
        <h3 slot="header" class="mb-0 text-white">推荐视频</h3>
        <b-container class="p-4 bg-dark">
          <b-card-group deck>
            <!-- 使用 v-for 遍历每行视频组 -->
            <b-row v-for="(row, rowIndex) in groupedRecommendedVideos" :key="rowIndex" class="mb-4">
              <!-- 使用 v-for 遍历每个视频 -->
              <b-col v-for="(video, index) in row" :key="index" class="custom-col">
                <router-link :to="`/video/${video.id}/${video.levelName}`"
                             class="page-link bg-dark" style="border: none;">
                  <b-card :img-src="video.thumbnailUrl" :img-alt="video.alt" img-top>
                    <b-card-body>{{ video.title }}</b-card-body>
                    <b-card-footer>
                      <div class="content">
                        <i class="ni ni-glasses-2 mr-2">
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
      </card>
    </b-container>

  </div>
</template>

<script>
import {getTrendingVideos, getLatestVideos, getRecommendedVideos} from "@/api/video";

export default {
  name: "home",
  data() {
    return {
      trendingVideos: [],
      latestVideos: [],
      recommendedVideos: [],
      slide: 0,// 当前幻灯片索引
      sliding: null,
      slides: [ // 幻灯片数据数组
        {imageSrc: 'https://picsum.photos/1024/480/?image=10', alt: 'Image 1'},
        {imageSrc: 'https://picsum.photos/1024/480/?image=12', alt: 'Image 2'},
        {imageSrc: 'https://picsum.photos/1024/480/?image=22', alt: 'Image 3'},
        {imageSrc: 'https://picsum.photos/1024/480/?image=33', alt: 'Image 4'},
        {imageSrc: 'https://picsum.photos/1024/480/?image=55', alt: 'Image 5'}
      ]
    };
  },
  computed: {
    // 将视频列表分组为每行两个视频
    groupedTrendingVideos() {
      const rows = [];
      for (let i = 0; i < this.trendingVideos.length; i += 2) {
        rows.push(this.trendingVideos.slice(i, i + 2));
      }
      return rows;
    },
    groupedLatestVideos() {
      const rows = [];
      for (let i = 0; i < this.latestVideos.length; i += 2) {
        rows.push(this.latestVideos.slice(i, i + 2));
      }
      return rows;
    },
    groupedRecommendedVideos() {
      const rows = [];
      for (let i = 0; i < this.recommendedVideos.length; i += 2) {
        rows.push(this.recommendedVideos.slice(i, i + 2));
      }
      return rows;
    }
  },
  created() {
    // 在组件创建后发送 HTTP 请求获取热门视频数据
    this.fetchTrendingVideos();
    this.fetchLatestVideos();
    this.fetchRecommendedVideos();
  },
  methods: {
    onSlideStart(slide) {
      this.sliding = true
    },
    onSlideEnd(slide) {
      this.sliding = false
    },
    formatNumber(num) {
      if (num >= 10000) {
        return (num / 10000).toFixed(1) + '万';
      } else {
        return num;
      }
    },
    async fetchTrendingVideos() {
      try {
        const response = await getTrendingVideos();
        // 检查返回的状态码
        if (response.data.code === 200) {
          // 请求成功，将数据保存到组件的数据中
          this.trendingVideos = response.data.data; // 这里假设数据在 response.data 的 data 属性中
        } else {
          console.error(`Error fetching trending videos: ${response.statusText}`);
        }
      } catch (error) {
        // 请求失败，处理错误
        console.error('Error fetching trending videos:', error);
      }
    },
    async fetchLatestVideos() {
      try {
        const response = await getLatestVideos();
        // 检查返回的状态码
        if (response.data.code === 200) {
          // 请求成功，将数据保存到组件的数据中
          this.latestVideos = response.data.data;
        } else {
          console.error(`Error fetching latest videos: ${response.statusText}`);
        }
      } catch (error) {
        console.error('Error fetching latest videos:', error);
      }
    },
    async fetchRecommendedVideos() {
      try {
        const response = await getRecommendedVideos();
        // 检查返回的状态码
        if (response.data.code === 200) {
          // 请求成功，将数据保存到组件的数据中
          this.recommendedVideos = response.data.data;
        } else {
          // 请求失败，根据状态码进行相应处理
          alert(response.data.message);
          console.error(`${response.data.message}`);
        }
      } catch (error) {
        // 请求失败，处理错误
        console.error('Error fetching recommended videos:', error);
      }
    }
  }
};
</script>

<style scoped>
.carousel-container {
  position: relative;
  top: 80px; /* 调整轮播图与其他内容之间的垂直距离 */
}

.content {
  display: flex; /* 内部元素也使用 Flex 布局 */
  justify-content: space-between; /* 左右对齐 */
  width: 100%; /* 确保内容占满整个容器 */
}

.custom-col {
  display: grid;
  place-items: center; /* 垂直和水平居中 */
}
</style>

