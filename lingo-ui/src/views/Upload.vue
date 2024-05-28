<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- 使用 Bootstrap Vue 表单组件 -->
    </base-header>
    <b-container class="p-4 bg-secondary">
      <card class="p-4 bg-gradient-white mb-4">
        <b-card-body>
          <b-form>
            <!-- 上传视频文件和缩略图 -->
            <b-form-group label="上传视频文件" label-for="video">
              <b-row>
                <b-col>
                  <b-form-file v-model="videoFile" id="video" accept="video/*"></b-form-file>
                </b-col>
                <!--                <b-button variant="success" @click="uploadVideoFile">上传视频</b-button>-->
              </b-row>
            </b-form-group>
            <b-form-group label="上传缩略图" label-for="thumbnail">
              <b-row>
                <b-col>
                  <b-form-file v-model="thumbnailFile" id="thumbnail" accept="image/*"></b-form-file>
                </b-col>
                <!--                <b-button variant="info" @click="uploadThumbnailFile">上传缩略图</b-button>-->
              </b-row>
            </b-form-group>
            <!-- 添加视频信息的文本框 -->
            <b-form-group label="视频标题" label-for="title">
              <b-form-input v-model="videoInfo.title" id="title"
                            placeholder="请输入视频标题"></b-form-input>
            </b-form-group>
            <b-form-group label="视频描述" label-for="description">
              <b-form-textarea v-model="videoInfo.description" id="description"
                               placeholder="请输入视频描述"></b-form-textarea>
            </b-form-group>
            <!-- 添加视频时长的下拉选择框 -->
            <b-form-group label="视频时长" label-for="duration">
              <b-form-input v-model="videoInfo.duration" id="duration"
                            type="number" placeholder="请输入视频时长"></b-form-input>
            </b-form-group>
            <!-- 添加视频分类和难度等级的下拉选择框 -->
            <b-form-group label="视频分类" label-for="category">
              <b-form-select v-model="videoInfo.categoryId" :options="categoryOptions"
                             id="category" required></b-form-select>
            </b-form-group>
            <b-form-group label="难度等级" label-for="level">
              <b-form-select v-model="videoInfo.levelId" :options="levelOptions"
                             id="level" required></b-form-select>
            </b-form-group>
            <!-- 上传进度条 -->
            <b-progress v-if="uploadProgress > 0 && uploadProgress < 100" :value="uploadProgress" :max="100"
                        animated></b-progress>
            <div class="text-right">
              <b-button type="submit" variant="primary" @click="submitForm">提交</b-button>
            </div>
          </b-form>
        </b-card-body>
      </card>
    </b-container>
  </div>
</template>

<script>

import {saveVideo, uploadVideo, uploadVideoThumbnail} from "@/api/admin";
import {getUser} from "@/api/store";

export default {
  name: "upload",
  data() {
    return {
      categoryOptions: [
        {value: 1, text: '学习资源'},
        {value: 2, text: '音乐'},
        {value: 3, text: '动画'},
        {value: 4, text: '儿童'},
        {value: 5, text: '科学技术'},
        {value: 6, text: '经济金融'},
        {value: 7, text: '艺术娱乐'},
        {value: 8, text: '休闲旅行'},
        {value: 9, text: '健康福祉'},
        {value: 10, text: '新闻时事'},
      ],
      levelOptions: [
        {value: 1, text: 'A1'},
        {value: 2, text: 'A2'},
        {value: 3, text: 'B1'},
        {value: 4, text: 'B2'},
        {value: 5, text: 'C1'},
        {value: 6, text: 'C2'},
      ],
      videoFile: null,
      thumbnailFile: null,
      videoInfo: {
        title: "",
        description: "",
        videoUrl: "",
        thumbnailUrl: "",
        duration: 0,
        categoryId: 0,
        levelId: 0,
        uploaderId: 0,
      },
      uploadProgress: 0
    };
  },
  methods: {
    async uploadVideoFile() {
      if (!getUser()) {
        return;
      }
      try {
        const onUploadProgress = (progressEvent) => {
          const progress = Math.round((progressEvent.loaded / progressEvent.total) * 100);
          // 更新上传进度
          this.uploadProgress = progress;
        };

        const response = await uploadVideo(this.videoFile, onUploadProgress);

        if (response.data.code === 200) {
          this.videoInfo.videoUrl = response.data.data;
          this.$toast.success('上传视频成功！');
        } else {
          this.$toast.error('上传视频失败！');
          console.error('上传视频失败:', response.data.message);
        }
      } catch (error) {
        this.$toast.error('上传视频出错！');
        console.error('上传视频出错:', error);
      }
    },
    async uploadThumbnailFile() {
      if (!getUser()) {
        return;
      }
      try {
        const response = await uploadVideoThumbnail(this.thumbnailFile);
        if (response.data.code === 200) {
          this.videoInfo.thumbnailUrl = response.data.data;
          this.$toast.success("上传缩略图成功！");
        } else {
          this.$toast.error("上传缩略图失败！");
          console.error('上传缩略图失败:', response.data.message);
        }
      } catch (error) {
        this.$toast.error("上传缩略图出错！");
        console.error('上传缩略图出错:', error);
      }
    },
    async saveVideoInfo() {
      if (!getUser()) {
        return;
      }
      try {
        this.videoInfo.uploaderId = getUser().id;
        const response = await saveVideo(this.videoInfo);
        if (response.data.code === 200) {
          this.$toast.success("保存视频信息成功！");
          console.log('视频信息保存成功');
          // 清除残留数据
          this.videoFile = null;
          this.thumbnailFile = null;
          this.videoInfo.title = "";
          this.videoInfo.description = "";
          this.videoInfo.duration = 0;
          this.videoInfo.categoryId = 0;
          this.videoInfo.levelId = 0;
        } else {
          this.$toast.error("保存视频信息失败！");
          console.error('保存视频信息失败:', response.data.message);
        }
      } catch (error) {
        this.$toast.error("保存视频信息出错！");
        console.error('保存视频信息出错:', error);
      }
    },
    async submitForm() {
      await this.uploadVideoFile();
      await this.uploadThumbnailFile();
      await this.saveVideoInfo();
    }
  }

}
;
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



