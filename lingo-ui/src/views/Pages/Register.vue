<template>
  <div>
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1"
             xmlns="http://www.w3.org/2000/svg">
          <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
        </svg>
      </div>
    </div>
    <!-- Page content -->
    <b-container class="mt--8 pb-5">
      <!-- Table -->
      <b-row class="justify-content-center">
        <b-col lg="6" md="8">
          <b-card no-body class="bg-secondary border-0">
            <b-card-header class="bg-transparent pb-5">
              <div class="text-muted text-center mt-2 mb-4"><small>通过第三方注册</small></div>
              <div class="text-center">
                <a class="btn btn-neutral btn-icon" @click="handleQrcode">
                  <span class="btn-inner--icon"><img src="img/icons/common/wechat.png"></span>
                  <span class="btn-inner--text">WeChat</span>
                </a>
                <a href="#" class="btn btn-neutral btn-icon mr-4">
                  <span class="btn-inner--icon"><img src="img/icons/common/github.svg"></span>
                  <span class="btn-inner--text">Github</span>
                </a>
                <a href="#" class="btn btn-neutral btn-icon">
                  <span class="btn-inner--icon"><img src="img/icons/common/google.svg"></span>
                  <span class="btn-inner--text">Google</span>
                </a>
              </div>
            </b-card-header>
            <b-card-body class="px-lg-5 py-lg-5">
              <div class="text-center text-muted mb-4">
                <small>邮箱注册</small>
              </div>
              <validation-observer v-slot="{ handleSubmit }" ref="formValidator">
                <b-form @submit.prevent="handleSubmit(onSubmit)">
                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-email-83"
                              placeholder="邮箱"
                              name="Email"
                              :rules="{ required: true, email: true }"
                              v-model="model.email">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              name="verifyCode"
                              :rules="{ required: true, regex: /^\d{6}$/ }"
                              prepend-icon="ni ni-key-25"
                              type="text"
                              placeholder="验证码"
                              v-model="model.verifyCode">
                    <template v-slot:infoBlock>
                      <b-button variant="primary" @click="send" :disabled="isVerifyingCode">发送验证码</b-button>
                    </template>
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-hat-3"
                              placeholder="用户名"
                              name="Username"
                              :rules="{ required: true }"
                              v-model="model.username">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-lock-circle-open"
                              placeholder="密码"
                              type="password"
                              name="Password"
                              :rules="{ required: true, min: 6 }"
                              v-model="model.password">
                  </base-input>
<!--                  <div class="text-muted font-italic">-->
<!--                    <small>密码强度:-->
<!--                      <span class="text-success font-weight-700">强</span>-->
<!--                    </small>-->
<!--                  </div>-->

                  <base-input alternative
                              class="mb-3"
                              name="ConfirmPassword"
                              :rules="{ required: true, min: 6 }"
                              prepend-icon="ni ni-lock-circle-open"
                              type="password"
                              placeholder="确认密码"
                              v-model="confirmPassword"
                              @blur="validatePassword">
                  </base-input>
                  <span v-if="!passwordMatch" style="color: red;">两次输入的密码不匹配，请重新输入</span>

                  <b-row class=" my-4">
                    <b-col cols="12">
                      <base-input :rules="{ required: { allowFalse: false } }" name="Privacy Policy">
                        <b-form-checkbox v-model="agree">
                          <span class="text-muted">登陆或注册完成即代表同意<a href="#!">隐私政策</a></span>
                        </b-form-checkbox>
                      </base-input>
                    </b-col>
                  </b-row>

                  <div class="text-center">
                    <b-button type="submit" variant="primary"
                              class="mt-4" :disabled="!isFormValid">创建账户</b-button>
                  </div>

                </b-form>
              </validation-observer>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import {register, sendVerifyCode} from '@/api/user';

export default {
  name: 'register',
  data() {
    return {
      model: {
        email: '',
        verifyCode: '',
        username: '',
        password: '',
      },
      agree: false,
      confirmPassword: '',
      passwordMatch: false,
      isVerifyingCode: false,
    };
  },
  computed: {
    isFormValid() {
      return (
        this.model.email &&
        this.model.verifyCode &&
        this.model.username &&
        this.model.password &&
        this.confirmPassword &&
        this.passwordMatch &&
        this.agree
      );
    },
  },
  methods: {
    validatePassword() {
      this.passwordMatch = this.model.password === this.confirmPassword;
    },
    async send() {
      try {
        if (!this.model.email) {
          this.$toast.warning('请提供正确的邮箱地址');
          return;
        }
        this.isVerifyingCode = true;
        const response = await sendVerifyCode(this.model.email);
        if (response.data.code === 200) {
          this.$toast.success('验证码已发送，请查收！');
        } else {
          this.$toast.error('验证码发送失败，请稍后重试！');
        }
        setTimeout(() => {
          this.isVerifyingCode = false;
        }, 60000);
      } catch (error) {
        this.isVerifyingCode = false;
        this.$toast.error('发送验证码时出现错误，请稍后重试！');
        console.error(error);
      }
    },
    async onSubmit() {
      try {
        if (!this.isFormValid) {
          this.$toast.success('请填写完整信息！');
          return;
        }
        console.log(this.model);
        console.log(this.confirmPassword);
        console.log(this.passwordMatch);
        console.log(this.agree);
        const response = await register(this.model);
        if (response.data.code === 200) {
          this.$toast.success('注册成功！');
          await this.$router.push('/login');
        } else if (response.data.code) {
          this.$toast.error(response.data.message);
        } else {
          this.$toast.error('未知错误！');
        }
      } catch (error) {
        this.$toast.error('注册时出现错误，请稍后重试！');
        console.error(error);
      }
    },
    async handleQrcode() {
      try {
        await this.$router.push('/wechat-login');
      } catch (error) {
        console.error('登录时发生错误：', error);
      }
    },
  },
};
</script>

<style></style>

