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
      <b-row class="justify-content-center">
        <b-col lg="5" md="7">
          <!--          <template>-->
          <!--            <div>-->
          <!--              <base-alert type="success" dismissible :icon="'ni ni-check-bold'">-->
          <!--                登陆成功！欢迎回来！-->
          <!--              </base-alert>-->
          <!--            </div>-->
          <!--          </template>-->
          <b-card no-body class="bg-secondary border-0 mb-0">
            <b-card-header class="bg-transparent pb-5">
              <div class="text-muted text-center mt-2 mb-3"><small>第三方登录</small></div>
              <div class="btn-wrapper text-center">
                <a href="#" class="btn btn-neutral btn-icon">
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
                <small>邮箱登录</small>
              </div>
              <validation-observer v-slot="{handleSubmit}" ref="formValidator">
                <b-form role="form" @submit.prevent="handleSubmit(onSubmit)">
                  <base-input alternative
                              class="mb-3"
                              name="Email"
                              :rules="{required: true, email: true}"
                              prepend-icon="ni ni-email-83"
                              placeholder="邮箱"
                              v-model="model.email">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              name="Password"
                              :rules="{required: true, min: 6}"
                              prepend-icon="ni ni-lock-circle-open"
                              type="password"
                              placeholder="密码"
                              v-model="model.password">
                  </base-input>

                  <b-form-checkbox v-model="model.rememberMe">记住我</b-form-checkbox>
                  <div class="text-center">
                    <base-button type="primary" native-type="submit"
                                 class="my-4" :disabled="!isFormValid">登陆
                    </base-button>
                  </div>
                </b-form>
              </validation-observer>
            </b-card-body>
          </b-card>

          <b-row class="mt-3">
            <b-col cols="6">
              <router-link to="/reset-password" class="text-light"><small>忘记密码?</small></router-link>
            </b-col>
            <b-col cols="6" class="text-right">
              <router-link to="/register" class="text-light"><small>创建账户</small></router-link>
            </b-col>
          </b-row>

        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import {login} from "@/api/user";
import {setToken} from "@/api/store";

export default {
  data() {
    return {
      model: {
        email: '',
        password: '',
        rememberMe: false
      },
      showModal: false,
      message: '',
      visible: false
    };
  },
  computed: {
    isFormValid() {
      return (
        this.model.email &&
        this.model.password
      );
    },
  },
  methods: {
    openModal(message) {
      this.message = message;
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
    async onSubmit() {
      try {
        if (!this.isFormValid) {
          this.$toast.warning('请填写完整信息！');
          return;
        }
        const response = await login(this.model);
        if (response.data.code === 200) {
          this.$toast.success('登陆成功！');
          setToken(response.data.data);
          await this.$router.push('/home');
        } else if (response.data.code) {
          this.$toast.error(response.data.message);
        } else {
          this.$toast.error('未知错误！');
        }
      } catch (error) {
        this.$toast.error('登陆时出现错误，请稍后重试！');
        console.error(error);
      }
    },
  }
};
</script>
