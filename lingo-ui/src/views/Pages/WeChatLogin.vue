<template>
  <div>
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      <div class="separator separator-bottom separator-skew zindex-100"></div>
      <b-container class="mt--8 pb-5">
        <div>
          <h1 class="text-center">微信扫码注册</h1>
          <div id="wechat-login-qrcode" class="d-block mx-auto"></div>
        </div>
      </b-container>
    </div>
  </div>
</template>

<script>
export default {
  name: 'WeChatLogin',
  mounted() {
    this.initWeChatLogin();
  },
  methods: {
    initWeChatLogin() {
      new WxLogin({
        self_redirect: false,
        id: "wechat-login-qrcode",
        appid: "wx87654d8572c6c372", // 微信AppID
        scope: "snsapi_login",
        redirect_uri: encodeURIComponent("https://lingotube.mynatapp.cc"), // 回调URI
        state: "",
        style: "",
        href: ""
      });
    }
  }
};
</script>

<style scoped>
#wechat-login-qrcode {
  width: 300px;
  height: 300px;
}
</style>

