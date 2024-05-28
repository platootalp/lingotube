<template>
  <div>
    <base-header class="pb-2 pb-6 pt-2 pt-md-6 bg-gradient-success">
    </base-header>

    <b-container fluid class="p-4 bg-white bg-gradient-dark">
      <b-row>
        <b-col cols="12">
          <card class="p-4 bg-dark mb-4">
            <b-card-header>
              <b-row>
                <b-col>
                  <h2>{{ video.title }}</h2>
                </b-col>
              </b-row>
              <b-row>
                <b-col cols="2">
                  <i class="ni ni-glasses-2 mr-2"></i>
                  <span>{{ formatNumber(video.views) }}</span>
                </b-col>
                <b-col>
                  <span>{{ formattedDateTime }}</span>
                </b-col>
                <b-col cols="1">
                  <span>{{ video.levelName }}</span>
                </b-col>
              </b-row>
            </b-card-header>

            <b-card-text>
              <div class="player-container">
                <vue-core-video-player :src="video.videoUrl" :cover="video.thumbnailUrl"
                                       :title="video.title"
                                       @play="handlePlay" @pause="handlePause" @ended="handleEnded"/>
              </div>
            </b-card-text>

            <b-card-footer>
              <b-row>
                <b-col cols="6">
                  <a href="#" class="nav-link pr-0" @click.prevent slot="title-container">
                    <b-media no-body class="align-items-center">
                              <span class="avatar avatar-sm rounded-circle">
                                <img :alt="video.uploaderAvatarAlt" :src="video.uploaderAvatar">
                              </span>
                      <b-media-body class="ml-2 d-none d-lg-block">
                        <span class="mb-0 text-sm  font-weight-bold">{{ video.uploaderNickname }}</span>
                      </b-media-body>
                    </b-media>
                  </a>
                </b-col>

                <b-col>
                  <!-- 点赞按钮和次数 -->
                  <b-btn variant="white"
                         class="ni ni-like-2 btn-sm"
                         :class="{ 'liked': isLiked }"
                         v-b-tooltip.hover.focus title="点赞"
                         @click="handleVideoLike">
                    {{ formatNumber(video.likes) }}
                  </b-btn>
                </b-col>
                <b-col>
                  <!-- 收藏按钮和次数 -->
                  <b-btn variant="white" class="ni ni-favourite-28 btn-sm"
                         v-b-tooltip.hover.focus title="收藏">
                    {{ formatNumber(video.favorites) }}
                  </b-btn>
                </b-col>
                <b-col>
                  <!-- 分享按钮和次数 -->
                  <b-btn variant="white" class="ni ni-curved-next btn-sm"
                         v-b-tooltip.hover.focus title="分享">
                    {{ formatNumber(video.shares) }}
                  </b-btn>
                </b-col>
                <b-col>
                  <!-- “稍后再看”按钮 -->
                  <b-btn variant="white" class="ni ni-time-alarm btn-sm"
                         :class="{ 'watchLater': isInWatchLater }"
                         v-b-tooltip.hover.focus title="稍后再看"
                         @click="handleWatchLater">
                  </b-btn>
                </b-col>
              </b-row>

              <hr class="my-2">
              <b-row>
                <b-col>
                  <p>{{ video.description }}</p>
                </b-col>
              </b-row>
            </b-card-footer>
          </card>
        </b-col>

        <!--        <b-col cols="4">-->
        <!--          <card>-->
        <!--            <b-card-header>-->
        <!--              <h3>字幕</h3>-->
        <!--            </b-card-header>-->
        <!--          </card>-->
        <!--        </b-col>-->
      </b-row>
    </b-container>

    <b-container fluid class="p-4 bg-white bg-gradient-white">
      <b-row>
        <b-col cols="8">
          <card>
            <b-card-header>
              <h3>评论 <span>{{ video.comments }}</span></h3>
            </b-card-header>
          </card>
        </b-col>

        <b-col cols="4">
          <card>
            <b-card-header>
              <h3>相关视频</h3>
            </b-card-header>
            <b-card-body style="overflow-y: auto; max-height: 2000px;">
              <b-container class="p-4 bg-white">
                <!-- 使用 v-for 遍历相关视频 -->
                <div v-for="(video, index) in relatedVideos" :key="index" class="mb-3">
                  <router-link :to="`/video/${video.id}/${video.levelName}`"
                               class="page-link bg-dark" style="border: none;">
                    <b-card :img-src="video.thumbnailUrl" :img-alt="video.alt" img-top>
                      <b-card-text>{{ video.title }}</b-card-text>
                      <b-card-footer>
                        <div class="content">
                          <i class="ni ni-glasses-2"><span>{{ video.views }}</span></i>
                          <span class="level">{{ video.levelName }}</span>
                        </div>
                      </b-card-footer>
                    </b-card>
                  </router-link>
                </div>
              </b-container>
            </b-card-body>
          </card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import {
  addVideoViews,
  deleteWatchLater,
  getRelatedVideoList,
  getVideo,
  getVideoLikes, isExistLiked,
  isExistWatchLater,
  likeVideo,
  saveHistory,
  saveWatchLater
} from "@/api/video";
import {getUser} from "@/api/store";

export default {
  name: "VideoPlayer",
  props: ['id', 'levelName'], // 如果不使用props传参可以通过$route来获取
  data() {
    return {
      video: {
        title: '',
        description: '',
        videoUrl: 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_20240417222255528.mp4',
        thumbnailUrl: 'https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240419144755772.png',
        duration: 0,
        views: 0,
        likes: 0,
        dislikes: 0,
        favorites: 0,
        shares: 0,
        comments: 0,
        uploadTime: null,
        levelName: '',
        uploaderId: 0,
        uploaderNickname: '',
        uploaderAvatar: '',
        uploaderAvatarAlt: '无',
      },
      relatedVideos: [],
      viewDuration: 0, // 用户已观看的视频时长
      lastPlayTime: null, // 上次播放时间
      historySaved: false, // 添加一个标志表示观看历史是否已保存
      isLiked: false,// 视频是已经点赞
      isInWatchLater: false,// 视频是否加入到了稍后再看列表
      isWatched: 0,// 是否已经观看视频
    }
  },
  computed: {
    formattedDateTime() {
      const dateTime = this.video.uploadTime;
      const date = new Date(dateTime);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      const seconds = String(date.getSeconds()).padStart(2, '0');
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    }
  },
  created() {
    // 初始化时获取视频信息
    this.fetchVideo();
    this.fetchRelatedVideos();
    // 获取登陆后需要的信息
    if (getUser()) {
      this.checkLiked();
      this.checkWatchLaterList();
    }
    // 创建定时器，每隔一段时间设置一下viewDuration
    this.saveHistoryInterval = setInterval(() => {
      if (!this.historySaved) {
        this.handlePause();
        this.handlePlay();
      }
    }, 5000);
    // 监听路由变化，在路由变化时重新获取视频信息
    this.$watch('$route', () => {
      this.fetchVideo();
      this.fetchRelatedVideos();
    });
  },
  beforeDestroy() {
    if (!this.historySaved) {
      this.saveWatchHistory(); // 仅在观看历史未保存时保存观看历史
    }
    clearInterval(this.saveHistoryInterval); // 清除保存观看历史的定时器
  },
  methods: {
    formatNumber(num) {
      if (num >= 10000) {
        return (num / 10000).toFixed(1) + '万';
      } else {
        return num;
      }
    },
    // 播放事件处理函数
    handlePlay() {
      this.lastPlayTime = Date.now(); // 记录播放开始时间
    },
    // 暂停事件处理函数
    handlePause() {
      if (this.lastPlayTime) {
        const currentTime = Date.now();
        const deltaTime = (currentTime - this.lastPlayTime) / 1000; // 转换为秒
        this.viewDuration += deltaTime; // 更新观看时长
        this.lastPlayTime = null; // 重置播放开始时间
      }
    },
    // 结束事件处理函数
    handleEnded() {
      // 如果播放过视频，则更新观看时长
      if (this.lastPlayTime) {
        const currentTime = Date.now();
        const deltaTime = (currentTime - this.lastPlayTime) / 1000; // 转换为秒
        this.viewDuration += deltaTime; // 更新观看时长
      }
      this.historySaved = true; // 设置标志为已保存
      this.saveWatchHistory(); // 仅在观看历史未保存时保存观看历史
      this.addViews();
    },
    async fetchVideo() {
      try {
        // 发送 GET 请求获取视频信息
        const response = await getVideo(this.id);
        // 如果请求成功，将视频信息保存到 data 中的 video 对象中
        if (response.data.code === 200) {
          this.video = response.data.data
        } else {
          console.error('Failed to fetch video:', response.data.message);
        }
      } catch (error) {
        console.error('Error fetching video:', error);
      }
    },
    async fetchRelatedVideos() {
      try {
        // 发送 GET 请求获取视频信息
        const response = await getRelatedVideoList(this.id, this.levelName);
        // 如果请求成功，将视频信息保存到 data 中的 video 对象中
        if (response.data.code === 200) {
          this.relatedVideos = response.data.data
        } else {
          console.error('Failed to fetch video:', response.data.message);
        }
      } catch (error) {
        console.error('Error fetching video:', error);
      }
    },
    async fetchVideoLikes() {
      try {
        const response = await getVideoLikes(this.id);
        if (response.data.code === 200) {
          this.video.likes = response.data.data;
        } else {
          this.$toast.error("获取点赞数失败！");
        }
      } catch (error) {
        console.error('未知错误：', error);
      }
    },
    async checkWatchLaterList() {
      if (!getUser()) {
        this.$toast.warning('请先登录！');
        return;
      }
      try {
        const response = await isExistWatchLater(getUser().id, this.id);
        if (response.data.code === 200) {
          this.isInWatchLater = response.data.data;
        } else {
          this.$toast.error("请求失败！");
        }
      } catch (error) {
        console.error('未知错误：', error);
      }
    },
    async handleWatchLater() {
      if (!getUser()) {
        this.$toast.warning('请先登录！');
        return;
      }
      if (!this.isInWatchLater) {
        // 加入到稍后再看列表
        try {
          if (this.viewDuration > 0) {
            this.isWatched = 1;
          }
          const watchLater = {
            userId: getUser().id,
            videoId: this.id,
            isWatched: this.isWatched,
            viewDuration: this.viewDuration
          };
          const response = await saveWatchLater(watchLater);
          if (response.data.code === 200) {
            this.isInWatchLater = !this.isInWatchLater;
            this.$toast.success("已经加入到稍后再看")
          } else {
            this.$toast.error("加入到稍后再看列表失败");
          }
        } catch (error) {
          this.$toast.error("未知错误！");
        }
      } else {
        // 从稍后再看列表移除
        try {
          const response = await deleteWatchLater(getUser().id, this.id);
          if (response.data.code === 200) {
            this.isInWatchLater = !this.isInWatchLater;
            this.$toast.success("已从稍后再看列表移除")
          } else {
            this.$toast.error("移除失败");
          }
        } catch (error) {
          this.$toast.error("未知错误！");
        }
      }

    },
    async checkLiked() {
      if (!getUser()) {
        this.$toast.warning('请先登录！');
        return;
      }
      try {
        const response = await isExistLiked(getUser().id, this.id);
        if (response.data.code === 200) {
          this.isLiked = response.data.data; // 更新点赞状态
        } else {
          this.$toast.error("获取点赞状态失败！");
        }
      } catch (error) {
        console.error('未知错误：', error);
      }
    },
    async handleVideoLike() {
      if (!getUser()) {
        this.$toast.warning('请先登录！');
        return;
      }
      try {
        const response = await likeVideo(getUser().id, this.id);
        if (response.data.code === 200) {
          if (!this.isLiked) {
            this.isLiked = true;
            this.$toast.success("点赞成功！");
          } else {
            this.isLiked = false;
            this.$toast.success("取消点赞成功！");
          }
        } else {
          if (!this.isLiked) {
            this.$toast.error("点赞失败！");
          } else {
            this.$toast.error("取消点赞失败！");
          }
        }
        // 重新获取点赞数
        await this.fetchVideoLikes();
      } catch (error) {
        console.error('未知错误：', error);
      }
    },
    async saveWatchHistory() {
      if (!getUser()) {
        return; // 如果用户未登录，则不保存观看历史
      }
      try {
        const watchHistory = {
          userId: getUser().id,
          videoId: this.id,
          viewDuration: this.viewDuration,
        };
        // 发送请求保存观看历史
        const response = await saveHistory(watchHistory);
        if (response.data.code === 200) {
          console.log('观看历史已保存！');
        } else {
          console.error('保存观看历史失败：', response.data.message);
        }
      } catch (error) {
        console.error('保存观看历史时出错：', error);
      }
    },
    async addViews() {
      try {
        // 发送请求保存观看历史
        const response = await addVideoViews(this.id);
        if (response.data.code === 200) {
          console.log('播放量已增加！');
        } else {
          console.error('播放量增加失败：', response.data.message);
        }
      } catch (error) {
        console.error('播放量增加时出错：', error);
      }
    }
  }
}
</script>

<style scoped>
.liked {
  /* 定义点赞后的样式，可以是背景颜色、文本颜色等 */
  background-color: white;
  color: red;
  /* 其他样式 */
}
.watchLater {
  /* 定义点赞后的样式，可以是背景颜色、文本颜色等 */
  background-color: white;
  color: blue;
  /* 其他样式 */
}
.content {
  display: flex; /* 内部元素也使用 Flex 布局 */
  justify-content: space-between; /* 左右对齐 */
  width: 100%; /* 确保内容占满整个容器 */
}
</style>
