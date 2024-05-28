<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-2 bg-gradient-success border-0">
    </base-header>

    <b-row>
      <b-col cols="4">
        <b-card :class="[cardClass]" style="height: 700px;">
          <b-card-body :class="[cardBodyClass]">
            <b-row class="mb-5">
              <router-link v-if="likedVideos.length > 0"
                           :to="`/video/${likedVideos[0].id}/${likedVideos[0].levelName}`"
                           class="page-link" :class="[cardClass]">
                <img :src="likedVideos[0].thumbnailUrl" :alt="likedVideos[0].alt"
                     style="max-width: 100%; max-height: 100%;" :class="[cardClass]">
              </router-link>
              <div v-else>
                <img src="https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240419144755772.png"
                     alt="Placeholder Image" style="max-width: 100%; max-height: 100%;" :class="[cardClass]">
              </div>
            </b-row>
            <b-row class="ml-1 mb-2">
              <h1 class="text-white">赞过的视频</h1>
            </b-row>
            <b-row class="ml-1 mb-5 d-flex align-items-center">
              <base-button :outline="false" type="white" class="mr-2">
                <i class="ni ni-button-play mr-2"></i>
                <span class="align-middle">播放全部</span>
              </base-button>
              <base-button :outline="false" type="primary" @click="clear">
                <i class="ni ni-basket mr-2"></i>
                <span class="align-middle">清空视频</span>
              </base-button>
            </b-row>
          </b-card-body>
        </b-card>
      </b-col>
      <b-col>
        <b-card :class="[rightCardClass]">
          <b-card-header :class="[rightCardClass]">
            <b-row>
              <b-col>
                <b-form class="navbar-search form-inline mr-sm-3">
                  <b-form-group class="mb-0">
                    <b-input-group class="input-group-alternative input-group-merge text-white">
                      <b-form-input v-model="titleKeyWord" @keyup.enter="search"
                                    placeholder="搜索点赞记录" type="text"></b-form-input>
                      <div class="input-group-append">
                        <span class="input-group-text" @click="search"><i class="fas fa-search"></i></span>
                      </div>
                    </b-input-group>
                  </b-form-group>
                </b-form>
              </b-col>
            </b-row>
          </b-card-header>
          <b-card-body style="overflow-y: auto; max-height: 572px;">
            <div v-for="(video, index) in likedVideos" :key="index" class="mb-3">
              <b-card class="p-4" :class="[innerCardClass]">
                <b-row>
                  <!-- 左侧视频封面 -->
                  <b-col>
                    <router-link :to="`/video/${video.id}/${video.levelName}`"
                                 class="page-link" :class="[innerCardClass]">
                      <b-card no-body :img-src="video.thumbnailUrl" :img-alt="video.alt"
                              :class="[innerCardClass]"></b-card>
                    </router-link>
                  </b-col>
                  <!-- 右侧视频信息 -->
                  <b-col>
                    <b-row class="mb-5">
                      <b-col>
                        <h5>{{ video.title }}</h5>
                      </b-col>
                      <b-col>
                        <base-button type="black" class="ni ni-basket ml-auto"
                                     v-b-tooltip.hover.focus title="删除"
                                     @click="deleteOne(video.id)">
                        </base-button>
                      </b-col>
                    </b-row>
                    <b-row class="mb-3">
                      <b-col>
                        <i class="ni ni-glasses-2 mr-2"></i>
                        <span>{{ formatNumber(video.views) }}</span>
                      </b-col>
                      <b-col>
                        <span class="level align-self-center">{{ video.levelName }}</span>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col>
                        <a href="#" @click.prevent>
                          <b-media no-body class="align-items-center h-100">
                                <span class="avatar avatar-sm rounded-circle">
                                  <img :alt="video.uploaderAvatarAlt" :src="video.uploaderAvatar">
                                </span>
                            <b-media-body class="ml-2 d-none d-lg-block">
                              <span class="mb-0 text-sm text-white font-weight-bold">
                                {{ video.uploaderNickname }}</span>
                            </b-media-body>
                          </b-media>
                        </a>
                      </b-col>
                    </b-row>
                  </b-col>
                </b-row>
              </b-card>
            </div>
          </b-card-body>
        </b-card>
      </b-col>
      <!-- 确认对话框 -->
      <b-modal ref="confirmDialog" ok-title="确定" cancel-title="取消"
               @ok="handleConfirmAction" @cancel="handleCancelAction">
        {{ confirmDialogText }}
      </b-modal>
    </b-row>
  </div>
</template>

<script>
import {getUser} from "@/api/store";
import {clearLiked, deleteLiked, deleteOne, deleteWatched, getLikedVideoList, getWatchLaterList} from "@/api/video";
import {VIDEO_LIKE_ACTION, WATCH_LATER_ACTION} from "@/api/constant";

export default {
  name: "WatchLater",
  data() {
    return {
      cardClass: 'bg-gradient-red border-0',
      cardBodyClass: 'bg-gradient-red border-0 mb-5',
      rightCardClass: 'bg-gradient-dark border-0',
      innerCardClass: 'bg-gradient-white border-0',
      confirmDialogText: '',
      confirmDialogAction: '',
      likedVideos: [],
      titleKeyWord: '',// 搜索关键字
      deleteVideoId: 0,
    };
  },
  created() {
    this.fetchLikedVideoList();
  },
  methods: {
    openConfirmDialog(text, action) {
      this.confirmDialogText = text;
      this.confirmDialogAction = action;
      this.$refs.confirmDialog.show();
    },
    clear() {
      this.openConfirmDialog('确定要清空赞过的的视频吗？', VIDEO_LIKE_ACTION.CLEAR);
    },
    deleteOne(videoId) {
      this.deleteVideoId = videoId;
      this.openConfirmDialog('确定要删除该记录吗？', VIDEO_LIKE_ACTION.DELETE_ONE);
    },
    handleConfirmAction() {
      if (this.confirmDialogAction === VIDEO_LIKE_ACTION.CLEAR) {
        this.handleClear();
      } else if (this.confirmDialogAction === VIDEO_LIKE_ACTION.DELETE_ONE) {
        this.handleDeleteOne();
      }
    },
    handleCancelAction() {
      if (this.confirmDialogAction === VIDEO_LIKE_ACTION.DELETE_ONE) {
        this.deleteVideoId = 0;
      }
    },
    formatNumber(num) {
      if (num >= 10000) {
        return (num / 10000).toFixed(1) + '万';
      } else {
        return num;
      }
    },
    search() {
      this.fetchLikedVideoList();
    },
    async fetchLikedVideoList() {
      if (!getUser()) {
        return;
      }
      try {
        const response = await getLikedVideoList(getUser().id, this.titleKeyWord);
        if (response.data.code === 200) {
          this.likedVideos = response.data.data;
        } else {
          console.error('获取用户记录失败:', response.data.message);
        }
      } catch (error) {
        console.error('获取用户记录时出错:', error);
      }
    },
    async handleClear() {
      if (!getUser()) {
        return;
      }
      try {
        const response = await clearLiked(getUser().id);
        if (response.data.code === 200) {
          if (response.data.data > 0) {
            this.$toast.success('删除成功');
          }
          await this.fetchLikedVideoList();
        } else {
          this.$toast.error('记录删除失败');
        }
      } catch (error) {
        console.error('未知错误');
      }
    },
    async handleDeleteOne() {
      if (!getUser()) {
        return;
      }
      try {
        const response = await deleteLiked(getUser().id, this.deleteVideoId);
        if (response.data.code === 200) {
          this.$toast.success('删除成功');
          await this.fetchLikedVideoList();
        } else {
          this.$toast.error('删除失败');
        }
      } catch (error) {
        console.error('未知错误');
      }
    },
  }
};
</script>

<style scoped>
.content {
  display: flex; /* 内部元素也使用 Flex 布局 */
  justify-content: space-between; /* 左右对齐 */
  width: 100%; /* 确保内容占满整个容器 */
}

/* 移除卡片的边界线 */
.border-0 {
  border: none !important;
}
</style>

