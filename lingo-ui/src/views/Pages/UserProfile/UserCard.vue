<template>
  <b-card no-body class="card-profile" alt="Image placeholder" img-top>
    <b-row class="justify-content-center">
      <b-col lg="3" class="order-lg-2">
        <div class="card-profile-image">
          <a href="#">
            <b-img :src="userInfo.avatar" rounded="circle"/>
          </a>
        </div>
      </b-col>
    </b-row>

    <b-card-header class="text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
      <div class="d-flex justify-content-between">
        <input type="file" @change="handleFileChange" style="display: none" ref="fileInput" accept="image/*">
        <button class="btn btn-sm btn-info mr-4" @click="chooseFile">修改头像</button>
        <a href="#" class="btn btn-sm btn-default float-right">修改信息</a>
      </div>
    </b-card-header>

    <b-card-body class="pt-0">
      <b-row>
        <b-col>
          <div class="card-profile-stats d-flex justify-content-center mt-md-5">
            <div>
              <span class="heading">22</span>
              <span class="description">注册天数</span>
            </div>
            <div>
              <span class="heading">18</span>
              <span class="description">登录天数</span>
            </div>
            <div>
              <span class="heading">62</span>
              <span class="description">观看视频</span>
            </div>
          </div>
        </b-col>
      </b-row>
      <div class="text-center">
        <h5 class="h3">
          {{ userInfo.nickname }}, {{ age }}
        </h5>
        <div class="h5 font-weight-300">
          <i class="ni location_pin mr-2"></i>{{ genderText }}
        </div>
        <div>
          <i class="ni education_hat mr-2"></i>{{ userInfo.address }}
        </div>
        <hr class="my-4">
        <p>{{ userInfo.introduce }}</p>
        <a href="#">Show more</a>
      </div>
    </b-card-body>
  </b-card>
</template>
<script>
import {getUser, removeUser, setUser} from "@/api/store";
import {getAvatar, getUserInfo, updateAvatar} from "@/api/user";

export default {
  data() {
    return {
      userInfo: {
        avatar: '',
        nickname: '',
        gender: '',
        birthday: '',
        address: '',
        introduce: '',
        email: '',
        phone: '',
      },
    }
  },
  computed: {
    age() {
      // 检查用户是否提供了生日信息
      if (!this.userInfo.birthday) {
        return '未知';
      }
      // 解析用户生日
      const birthday = new Date(this.userInfo.birthday);
      const today = new Date();
      // 计算年龄
      let age = today.getFullYear() - birthday.getFullYear();
      const monthDiff = today.getMonth() - birthday.getMonth();
      // 如果今年的生日还没到，年龄减一
      if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthday.getDate())) {
        age--;
      }
      return age;
    },
    location() {
      if (!this.userInfo.address) {
        return '未知';
      }
      return this.userInfo.address.substring(0, 3);
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
    chooseFile() {
      this.$refs.fileInput.click();
    },
    updateNavbarAvatar() {
      // 假设导航栏中的头像显示在一个 id 为 'navbar-avatar' 的元素中
      const navbarAvatar = document.getElementById('dashboard-navbar');
      navbarAvatar.showUserInfo();
    },
    async handleFileChange(event) {
      const file = event.target.files[0];
      const formData = new FormData();
      formData.append('file', file);
      try {
        // 1.向后端提交更新后的用户信息
        const response = await updateAvatar(formData);
        if (response.data.code === 200) {
          // 2.删除缓存
          removeUser();
          this.showUserInfo();
          this.updateNavbarAvatar();
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
