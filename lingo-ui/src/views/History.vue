<template>
  <div>
    <!-- 头部 -->
    <base-header class="pb-6 pb-8 pt-5 pt-md-2 bg-gradient-success border-0">
    </base-header>

    <!-- 主要内容 -->
    <b-container fluid class="p-4 bg-white">
      <card class="p-4 bg-gradient-dark border-0">
        <b-card-header class="p-4 bg-gradient-dark border-0">
          <!-- 标题和搜索框 -->
          <b-row class="align-items-center">
            <b-col cols="5">
              <h1 class="text-white">历史记录</h1>
            </b-col>
            <b-col class="content">
              <b-form class="navbar-search form-inline mr-sm-3 text-white">
                <b-form-group class="mb-0">
                  <b-input-group class="input-group-alternative input-group-merge">
                    <b-form-input v-model="titleKeyWord" @keyup.enter="search"
                                  placeholder="搜索历史记录" type="text"></b-form-input>
                    <div class="input-group-append">
                      <span class="input-group-text" @click="search"><i class="fas fa-search"></i></span>
                    </div>
                  </b-input-group>
                </b-form-group>
              </b-form>
              <!-- 按钮 -->
              <div class="ml-2">
                <base-button type="white" @click="clearHistory">
                  <span>清空历史</span>
                </base-button>
              </div>
              <div class="ml-2">
                <base-button type="white">
                  <span>暂停记录历史</span>
                </base-button>
              </div>
            </b-col>
          </b-row>
        </b-card-header>
        <!-- 视频播放历史 -->
        <b-card-body class="p-4 bg-gradient-dark border-0">
          <!-- 循环遍历视频播放历史 -->
          <div v-for="(group, groupIndex) in groupedVideoHistories" :key="groupIndex">
            <card class="p-4 bg-gradient-dark border-0">
              <b-card-header class="p-4 bg-gradient-dark border-0">
                <h3 class="text-white">{{ group.date }}</h3>
              </b-card-header>
              <b-card-body style="overflow-y: auto; max-height: 2000px;">
                <div v-for="(video, index) in group.videos" :key="index" class="mb-3">
                  <b-card class="p-4 bg-gradient-secondary border-0">
                    <b-row>
                      <!-- 左侧视频封面 -->
                      <b-col cols="4">
                        <router-link :to="`/video/${video.id}/${video.levelName}`" class="page-link bg-white"
                                     style="border: none;">
                          <b-card :img-src="video.thumbnailUrl" :img-alt="video.alt"></b-card>
                        </router-link>
                      </b-col>
                      <!-- 右侧视频信息 -->
                      <b-col>
                        <b-row class="mb-5">
                          <b-col>
                            <h2>{{ video.title }}</h2>
                          </b-col>
                          <b-col class="d-flex">
                            <base-button type="dark" class="ni ni-basket ml-auto"
                                         v-b-tooltip.hover.focus title="删除"
                                         @click="deleteHistory(video.id)">
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
                                  <span class="mb-0 text-sm font-weight-bold">{{ video.uploaderNickname }}</span>
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
            </card>
          </div>
        </b-card-body>
        <!-- 确认对话框 -->
        <b-modal ref="confirmDialog" ok-title="确定" cancel-title="取消"
                 @ok="handleConfirmAction" @cancel="handleCancelAction">
          {{ confirmDialogText }}
        </b-modal>
      </card>
    </b-container>
  </div>
</template>
<script>
import {clearHistory, deleteHistory, getHistoryList} from "@/api/video";
import {getUser} from "@/api/store";
import {HISTORY_ACTION} from "@/api/constant";

export default {
  name: "History",
  data() {
    return {
      videoHistories: [],
      titleKeyWord: '',// 搜索关键字
      confirmDialogText: '', // 用于存储确认对话框的文本内容
      confirmDialogAction: '',// 确认后的行为
      deleteVideoIds: [],
    }
  },
  computed: {
    groupedVideoHistories() {
      const groupedHistories = [];
      // 遍历历史记录数组
      this.videoHistories.forEach(history => {
          // 获取视频观看时间
          const date = new Date(history.watchTime).toLocaleDateString('zh-CN', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit'
          });
          // 检查该日期是否已经存在于分组中
          const existingGroup = groupedHistories.find(group => group.date === date);
          if (existingGroup) {
            // 如果日期已存在，则将该历史记录添加到对应分组中
            existingGroup.videos.push(history);
          } else {
            // 如果日期不存在，则创建新的分组并添加到分组数组中
            groupedHistories.push({
              date: date,
              videos: [history]
            });
          }
        }
      )
      ;
      return groupedHistories;
    }
  },
  created() {
    this.fetchWatchHistoryList();
  },
  methods: {
    formatNumber(num) {
      if (num >= 10000) {
        return (num / 10000).toFixed(1) + '万';
      } else {
        return num;
      }
    },
    search() {
      this.fetchWatchHistoryList();
    },
    // 弹出确认对话框并设置文本内容
    openConfirmDialog(text, action) {
      this.confirmDialogText = text;
      this.confirmDialogAction = action;
      this.$refs.confirmDialog.show();
    },
    clearHistory() {
      this.openConfirmDialog('确定要清空历史记录吗？', HISTORY_ACTION.CLEAR);
    },
    deleteHistory(videoId) {
      this.deleteVideoIds.push(videoId);
      this.openConfirmDialog('确定要删除该历史记录吗？', HISTORY_ACTION.CLEAR);
    },
    // 处理确认对话框点击确认按钮的逻辑
    handleConfirmAction() {
      // 在这里根据确认对话框的文本内容执行不同的操作
      if (this.confirmDialogAction === HISTORY_ACTION.CLEAR) {
        this.handleClearHistory();
      } else if (this.confirmDialogAction === HISTORY_ACTION.DELETE_HISTORY) {
        this.handleDeleteHistory();
      }
    },
    handleCancelAction() {
      // 在这里根据确认对话框的文本内容执行不同的操作
      if (this.confirmDialogAction === HISTORY_ACTION.DELETE_HISTORY) {
        this.deleteVideoIds.splice(0); // 移除全部元素
      }
    },
    async handleDeleteHistory() {
      if (!getUser()) {
        return;
      }
      try {
        console.log(this.deleteVideoIds);
        const response = await deleteHistory(getUser().id, this.deleteVideoIds);
        if (response.data.code === 200) {
          this.$toast.success('删除成功');
          // 刷新页面
          // location.reload();
          // 或者重新获取历史记录数据
          await this.fetchWatchHistoryList();
        } else {
          this.$toast.error('历史记录删除失败');
        }
      } catch (error) {
        this.$toast.error('历史记录删除失败');
      }
    },
    async handleClearHistory() {
      if (!getUser()) {
        return;
      }
      try {
        const response = await clearHistory(getUser().id);
        if (response.data.code === 200) {
          // 清空历史记录后刷新页面或重新获取历史记录数据
          this.$toast.success('历史记录已清空');
          // 刷新页面
          // location.reload();
          // 或者重新获取历史记录数据
          await this.fetchWatchHistoryList();
        } else {
          this.$toast.error('历史记录清空失败');
        }
      } catch (error) {
        this.$toast.error('清空历史记录失败');
      }
    },
    async fetchWatchHistoryList() {
      if (!getUser()) {
        return;
      }
      try {
        // 发送请求获取用户观看历史
        const response = await getHistoryList(getUser().id, this.titleKeyWord);
        if (response.data.code === 200) {
          // 如果请求成功，将观看历史保存到组件的 data 中
          this.videoHistories = response.data.data;
          console.log('成功获取用户观看历史:', this.videoHistories);
        } else {
          console.error('获取用户观看历史失败:', response.data.message);
        }
      } catch (error) {
        console.error('获取用户观看历史时出错:', error);
      }
    }
  }
}
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
