<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-2 bg-gradient-success border-0">
    </base-header>

    <b-row>
      <b-col cols="4">
        <b-card :class="[cardClass]" style="height: 700px;">
          <b-card-body :class="[cardBodyClass]">
            <b-row class="mb-5">
              <router-link v-if="watchLaterVideos.length > 0"
                           :to="`/video/${watchLaterVideos[0].id}/${watchLaterVideos[0].levelName}`"
                           class="page-link" :class="[cardClass]">
                <img :src="watchLaterVideos[0].thumbnailUrl" :alt="watchLaterVideos[0].alt"
                     style="max-width: 100%; max-height: 100%;" :class="[cardClass]">
              </router-link>
              <div v-else>
                <!-- 显示占位符或空白 -->
                <img src="https://lingotube.oss-cn-beijing.aliyuncs.com/video_thumbnail_20240419144755772.png"
                     alt="Placeholder Image" style="max-width: 100%; max-height: 100%;" :class="[cardClass]">
              </div>
            </b-row>
            <b-row class="ml-1 mb-2">
              <h1 class="text-white">稍后再看</h1>
            </b-row>
            <b-row class="ml-1 mb-5 d-flex align-items-center">
              <base-button :outline="false" type="white" class="mr-2">
                <i class="ni ni-button-play mr-2"></i>
                <span class="align-middle">播放全部</span>
              </base-button>
              <base-button :outline="false" type="primary" @click="deleteWatched">
                <i class="ni ni-basket mr-2"></i>
                <span class="align-middle">移除已观看视频</span>
              </base-button>
            </b-row>
          </b-card-body>
        </b-card>
      </b-col>
      <b-col>
        <b-card :class="[rightCardClass]">
          <b-card-header :class="[rightCardClass]">
            <b-row>
              <base-dropdown menu-on-left
                             tag="li"
                             title-tag="a"
                             title-classes="nav-link pr-0 text-white">
                <template>
                  <b-dropdown-item @click="reFetchWatchLaterList(1)">按最近添加</b-dropdown-item>
                  <b-dropdown-item @click="reFetchWatchLaterList(2)">按最早添加</b-dropdown-item>
                  <b-dropdown-item @click="reFetchWatchLaterList(3)">按热度排序</b-dropdown-item>
                  <b-dropdown-item @click="reFetchWatchLaterList(4)">按最近上传</b-dropdown-item>
                  <b-dropdown-item @click="reFetchWatchLaterList(5)">按最早上传</b-dropdown-item>
                </template>
              </base-dropdown>
              <h2 class="ml-2 text-white">排序</h2>
            </b-row>
          </b-card-header>
          <b-card-body style="overflow-y: auto; max-height: 572px;">
            <div v-for="(video, index) in watchLaterVideos" :key="index" class="mb-3">
              <b-card class="p-4">
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
                              <span class="mb-0 text-sm font-weight-bold">
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
import {deleteWatched, deleteWatchLater, getWatchLaterList} from "@/api/video";
import {WATCH_LATER_ACTION} from "@/api/constant";

export default {
  name: "WatchLater",
  data() {
    return {
      cardClass: 'bg-gradient-primary border-0',
      cardBodyClass: 'bg-gradient-primary border-0 mb-5',
      rightCardClass: 'bg-gradient-dark border-0',
      innerCardClass: 'bg-gradient-dark border-0',
      watchLaterVideos: [],
      sort: 1,
      confirmDialogText: '',
      confirmDialogAction: '',
      deleteVideoId: 0,
    };
  },
  created() {
    this.fetchWatchLaterList();
  },
  methods: {
    openConfirmDialog(text, action) {
      this.confirmDialogText = text;
      this.confirmDialogAction = action;
      this.$refs.confirmDialog.show();
    },
    deleteWatched() {
      this.openConfirmDialog('确定要删除已观看的视频吗？', WATCH_LATER_ACTION.DELETE_WATCHED);
    },
    deleteOne(videoId) {
      this.deleteVideoId = videoId;
      this.openConfirmDialog('确定要删除该记录吗？', WATCH_LATER_ACTION.DELETE_ONE);
    },
    handleConfirmAction() {
      if (this.confirmDialogAction === WATCH_LATER_ACTION.DELETE_WATCHED) {
        this.handleDeleteWatched();
      } else if (this.confirmDialogAction === WATCH_LATER_ACTION.DELETE_ONE) {
        this.handleDeleteOne();
      }
    },
    handleCancelAction() {
      if (this.confirmDialogAction === WATCH_LATER_ACTION.DELETE_ONE) {
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
    reFetchWatchLaterList(sortValue) {
      this.sort = sortValue;
      this.fetchWatchLaterList();
    },
    async fetchWatchLaterList() {
      if (!getUser()) {
        return;
      }
      try {
        const response = await getWatchLaterList(getUser().id, this.sort);
        if (response.data.code === 200) {
          this.watchLaterVideos = response.data.data;
        } else {
          console.error('获取用户稍后再看记录失败:', response.data.message);
        }
      } catch (error) {
        console.error('获取用户稍后再看记录时出错:', error);
      }
    },
    async handleDeleteWatched() {
      if (!getUser()) {
        return;
      }
      try {
        const response = await deleteWatched(getUser().id);
        if (response.data.code === 200) {
          if (response.data.data > 0) { // 注意这里是 response.data.data
            this.$toast.success('删除成功');
          }
          // 无论删除成功与否，都重新获取用户稍后再看记录
          await this.fetchWatchLaterList();
        } else {
          this.$toast.error('记录删除失败');
        }
      } catch (error) {
        console.log(error);
        this.$toast.error('未知错误');
      }
    },
    async handleDeleteOne() {
      if (!getUser()) {
        return;
      }
      try {
        const response = await deleteWatchLater(getUser().id, this.deleteVideoId);
        if (response.data.code === 200) {
          this.$toast.success('删除成功');
          await this.fetchWatchLaterList();
        } else {
          this.$toast.error('删除失败');
        }
      } catch (error) {
        this.$toast.error('删除失败');
      }
    },
  }
};
</script>

<style scoped>
/* 移除卡片的边界线 */
.border-0 {
  border: none !important;
}
</style>

