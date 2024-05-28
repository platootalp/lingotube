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
          <b-card no-body class="bg-secondary border-0 mb-0">
            <b-card-header class="bg-transparent pb-5">

            </b-card-header>
            <b-card-body class="px-lg-5 py-lg-5">
              <div class="text-center text-muted mb-4">
                <small>重置密码</small>
              </div>
              <validation-observer v-slot="{handleSubmit}" ref="formValidator">
                <b-form role="form">
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
                              name="verifyCode"
                              :rules="{required: true, regex: /^\d{6}$/}"
                              prepend-icon="ni ni-key-25"
                              type="text"
                              placeholder="验证码"
                              v-model="model.verifyCode">
                    <template v-slot:infoBlock>
                      <b-button variant="primary" @click="send"
                                :disabled="isVerifyingCode">发送验证码
                      </b-button>
                    </template>
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              name="newPassword"
                              :rules="{required: true, min: 6}"
                              prepend-icon="ni ni-lock-circle-open"
                              type="password"
                              placeholder="新密码"
                              v-model="model.newPassword">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              name="ConfirmPassword"
                              :rules="{required: true, min: 6}"
                              prepend-icon="ni ni-lock-circle-open"
                              type="password"
                              placeholder="确认密码"
                              v-model="confirmPassword"
                              @blur="validatePassword">
                  </base-input>

                  <span v-if="passwordMatchError" style="color: red;">两次输入的密码不匹配，请重新输入</span>

                  <div class="text-center">
                    <base-button type="primary" native-type="submit"
                                 class="my-4" @click="onSubmit">提交</base-button>
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
import {sendVerifyCode, resetPassword} from '@/api/user';

export default {
  data() {
    return {
      model: {
        email: '',
        verifyCode: '',
        newPassword: ''
      },
      confirmPassword: '',
      passwordMatchError: false,
      isVerifyingCode: false, // 用于控制是否正在发送验证码的标志
      isValidEmail: false
    };
  },
  methods: {
    validateEmail() {
      const emailRegex = /^[\w-]+(?:\.[\w-]+)*@(?:[\w-]+\.)+[a-zA-Z]{2,7}$/;
      this.isValidEmail = emailRegex.test(this.model.email);
    },
    validatePassword() {
      this.passwordMatchError = this.model.newPassword !== this.confirmPassword;
    },
    async send() {
      try {
        // 1.验证邮箱
        if (!this.model.email) {
          this.$toast.warning('请提供正确的邮箱地址');
          return;
        }
        // 2.发送验证码之前先禁用发送按钮，防止重复发送
        this.isVerifyingCode = true;
        // 发送请求给后端，请求发送验证码
        const response = await sendVerifyCode(this.model.email);
        if (response.data.code === 200) {
          this.$toast.info('验证码已发送，请查收！');
        } else {
          this.$toast.error('验证码发送失败，请稍后重试！');
        }
        // 3.设置定时器，一定时间后再次启用按钮
        setTimeout(() => {
          this.isVerifyingCode = false;
        }, 60000); // 60000 毫秒等于 60 秒
      } catch (error) {
        // 出现错误时也要确保按钮可以重新点击
        this.isVerifyingCode = false;
        this.$toast.error('发送验证码时出现错误，请稍后重试！');
        console.error(error);
      }
    },

    async onSubmit() {
      try {
        // 1.在提交表单之前先验证密码与验证码
        if(!this.model.verifyCode){
          this.$toast.warning('验证码不能为空！');
          return;
        }
        this.validatePassword();
        if (this.passwordMatchError) {
          this.$toast.warning('两次输入的密码不匹配！');
          return;
        }
        // 2.发送请求给后端，重置密码
        const response = await resetPassword(this.model);

        // 3.解析响应
        if (response.data.code === 200) {
          this.$toast.success('密码重置成功！');
          await this.$router.push('/login');
        } else if (response.data.code === 500) {
          this.$toast.warning('验证码错误！');
        } else {
          this.$toast.error('密码重置失败，请稍后重试！');
        }
      } catch (error) {
        this.$toast.error('密码重置时出现错误，请稍后重试！');
        console.error(error);
      }
    }
  }
};
</script>
