<template>
  <card>
    <b-row align-v="center" slot="header">
      <b-col>
        <h3 class="mb-0">编辑账户</h3>
      </b-col>
    </b-row>

    <!-- 基本信息 -->
    <b-form @submit.prevent="updateProfile">
      <h6 class="heading-small text-muted mb-4">基本信息</h6>
      <div class="pl-lg-4">
        <b-row>
          <b-col lg="6">
            <base-input
              type="text"
              label="用户名"
              placeholder="用户名"
              v-model="userInfo.username"
              :readonly="true"
              class="bold-text"
            >
            </base-input>
          </b-col>
          <b-col lg="6">
            <base-input
              type="text"
              label="昵称"
              placeholder="昵称"
              v-model="userInfo.nickname"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="6">
            <base-input
              type="text"
              label="性别"
              placeholder="性别"
              :value="genderText"
            >
            </base-input>
          </b-col>
          <b-col lg="6">
            <base-input
              type="date"
              label="出生日期"
              placeholder="出生日期"
              v-model="userInfo.birthday"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="12">
            <base-input
              type="text"
              label="家庭住址"
              placeholder="家庭住址"
              v-model="userInfo.address"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-form-group label="个人介绍"
                      label-class="form-control-label"
                      class="mb-0"
                      label-for="about-form-textaria"
        >
          <b-form-textarea rows="4"
                           id="about-form-textaria"
                           placeholder="简单介绍一下自己"
                           v-model="userInfo.introduce"
          >
          </b-form-textarea>
        </b-form-group>
      </div>
      <!-- 间隔 -->
      <div class="mb-4"></div>
      <!-- 提交按钮 -->
      <div class="text-right">
        <b-button type="submit" variant="primary">提交</b-button>
      </div>
    </b-form>

    <!-- 账号安全 -->
    <b-form>
      <hr class="my-4">
      <h6 class="heading-small text-muted mb-4">联系信息</h6>
      <div class="pl-lg-4">
        <b-row>
          <b-col md="12">
            <base-input
              type="email"
              label="邮箱"
              placeholder="example@email.com"
              v-model="maskedEmail"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="12">
            <base-input
              type="text"
              label="手机号"
              placeholder="手机号"
              v-model="maskedPhone"
            >
            </base-input>

          </b-col>
        </b-row>
      </div>
      <!-- 提交按钮 -->
      <div class="text-right">
        <b-button type="submit" variant="primary">提交</b-button>
      </div>
    </b-form>
  </card>
</template>
<script>
import {checkAuthStatus, getUser, removeUser, setUser} from "@/api/store";
import {getUserInfo, updateUserInfo} from "@/api/user";

export default {
  data() {
    return {
      userInfo: {
        username: '',
        nickname: '',
        gender: '',
        birthday: '',
        address: '',
        introduce: '',
        email: '',
        phone: '',
      }
    };
  },
  computed: {
    maskedEmail() {
      // 检查邮箱是否为空
      if (!this.userInfo.email) {
        return '';
      }
      // 将邮箱的第一个 @ 前的字符显示为明文，其余用 * 替代
      const atIndex = this.userInfo.email.indexOf('@');
      const prefix = this.userInfo.email.substring(0, atIndex);
      const maskedPrefix = prefix.substring(0, 1) + '*'.repeat(prefix.length - 1);
      return maskedPrefix + this.userInfo.email.substring(atIndex);
    },
    maskedPhone() {
      // 检查手机号是否为空
      if (!this.userInfo.phone) {
        return '';
      }
      // 将电话号码的后四位显示为明文，其余用 * 替代
      return '*'.repeat(this.userInfo.phone.length - 4)
        + this.userInfo.phone.substring(this.userInfo.phone.length - 4);
    },
    genderText() {
      return this.userInfo.gender === 0 ? '男' : '女';
    },
  },
  created() {
    this.showUserInfo();
  },
  methods: {
    showUserInfo() {
      const userInfo = getUser();
      if (userInfo) {
        this.userInfo = userInfo;
      } else {
        // 从后端获取用户信息
        getUserInfo().then(response => {
          if (response.data.code === 200) {
            this.userInfo = response.data.data;
            // 将用户信息保存到本地存储
            setUser(response.data.data);
          } else {
            console.error('Failed to fetch user info:', response.data.message);
          }
        }).catch(error => {
          console.error('Error fetching user info:', error);
        });
      }
    },
    async updateProfile() {
      // 创建一个新的对象，不包含 email 和 phone 字段
      const userInfoToSend = {...this.userInfo};
      delete userInfoToSend.email;
      delete userInfoToSend.phone;
      try {
        // 向后端提交更新后的用户信息
        const response = await updateUserInfo(userInfoToSend);
        if (response.data.code === 200) {
          // 删除缓存
          removeUser();
          this.showUserInfo();
          alert('用户信息已更新');
        } else {
          console.error('Failed to update user profile:', response.data.message);
        }
      } catch (error) {
        console.error('Error updating user profile:', error);
      }
    }
  }
};

</script>
<style></style>
