<template>
  <div>
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      <div class="separator separator-bottom separator-skew zindex-100">

      </div>
      <!-- Page content -->
      <b-container class="mt--8 pb-5">
        <div>
          <h1 class="text-center">微信扫码注册</h1>
          <div id="wechat-login-qrcode" class="d-block mx-auto"></div>
<!--          <div v-else>
            <p class="text-center">加载中...</p>
          </div>-->
        </div>
      </b-container>
    </div>

  </div>
</template>

<script>

import {getQRCode} from "@/api/user";

export default {
  name: 'QrCodePage',
  components: {},
  data() {
    return {
      qrCodeUrl: '',
    };
  },
  created() {
    this.handleGetQRCode();
  },
  methods: {
    async handleGetQRCode() {
      try {
        const response = await getQRCode();
        // 检查返回的状态码
        if (response.data.code === 200) {
          // 请求成功，将数据保存到组件的数据中
          this.qrCodeUrl = response.data.data; // 这里假设数据在 response.data 的 data 属性中
        } else {
          console.error(`获取失败: ${response.statusText}`);
        }
      } catch (error) {
        console.error('未知错误:', error);
      }
    },
  }
};
</script>

<style scoped>
.header {
  height: 300px; /* 调整标题高度以适应需要 */
}
.b-container {
  min-height: calc(100vh - 300px); /* 减去 header 的高度 */
}
</style>


