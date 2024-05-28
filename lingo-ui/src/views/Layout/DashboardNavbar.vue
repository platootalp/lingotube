<template>
  <base-nav
    container-classes="container-fluid"
    class="navbar-top navbar-expand"
    :class="{'navbar-dark': type === 'default'}"
  >
    <!-- 切换按钮 -->
    <template v-slot:toggle-button>
      <button
        class="navbar-toggler collapsed"
        type="button"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-bar navbar-kebab"></span>
        <span class="navbar-toggler-bar navbar-kebab"></span>
        <span class="navbar-toggler-bar navbar-kebab"></span>
      </button>
    </template>

    <a href="#" aria-current="page"
       class="h4 mb-0 text-white text-uppercase d-none d-lg-inline-block active router-link-active"></a>

    <!-- Navbar links -->
    <b-navbar-nav class="align-items-center ml-md-auto">
      <!-- This item dont have <b-nav-item> because item have data-action/data-target on tag <a>, which we cant add -->
      <li class="nav-item d-sm-none">
        <a class="nav-link" href="#" data-action="search-show" data-target="#navbar-search-main">
          <i class="ni ni-zoom-split-in"></i>
        </a>
      </li>
    </b-navbar-nav>

    <b-navbar-nav class="align-items-center ml-auto ml-md-0">

      <b-form class="navbar-search form-inline mr-sm-3"
              :class="{'navbar-search-dark': type === 'default', 'navbar-search-light': type === 'light'}"
              id="navbar-search-main"
              @submit.prevent="handleSearch">
        <b-form-group class="mb-0">
          <b-input-group class="input-group-alternative input-group-merge">
            <b-form-input v-model="searchText" placeholder="搜索" type="text"
                          @keyup.enter="handleSearch"></b-form-input>
            <div class="input-group-append">
              <span class="input-group-text"><i class="fas fa-search" @click="handleSearch"></i></span>
            </div>
          </b-input-group>
        </b-form-group>
      </b-form>


      <!-- 根据是否登录显示不同内容 -->
      <template v-if="isLoggedIn">
        <!-- 显示个人头像和相关操作 -->
        <a href="#" class="nav-link pr-0" @click.prevent slot="title-container">
          <b-media no-body class="align-items-center">
                              <span class="avatar avatar-sm rounded-circle">
                                <img :alt="userInfo.avatarAlt" :src="userInfo.avatar">
                              </span>
            <b-media-body class="ml-2 d-none d-lg-block">
              <span class="mb-0 text-sm  font-weight-bold">{{ userInfo.nickname }}</span>
            </b-media-body>
          </b-media>
        </a>

        <base-dropdown menu-on-right
                       class="nav-item"
                       tag="li"
                       title-tag="a"
                       title-classes="nav-link pr-0">
          <template>
            <b-dropdown-header class="noti-title">
              <h6 class="text-overflow m-0">欢迎!</h6>
            </b-dropdown-header>
            <b-dropdown-item @click="toProfile">
              <i class="ni ni-single-02"></i>
              <span>我的账号</span>
            </b-dropdown-item>
            <b-dropdown-item href="#!">
              <i class="ni ni-bell-55"></i>
              <span>消息</span>
            </b-dropdown-item>
            <div class="dropdown-divider"></div>
            <b-dropdown-item @click="handleLogout">
              <i class="ni ni-user-run"></i>
              <span>退出登录</span>
            </b-dropdown-item>
          </template>
        </base-dropdown>
      </template>

      <template v-else>
        <base-button type="success" class="ni ni-bold-right text-white"
                     v-b-tooltip.hover.top title="登陆/注册"
                     @click="handleLogin">
        </base-button>
      </template>

    </b-navbar-nav>

  </base-nav>
</template>
<script>
import {CollapseTransition} from 'vue2-transitions';
import {BaseNav, Modal} from '@/components';
import {checkAuthStatus, getUser, removeToken, removeUser, setUser} from "@/api/store";
import {getUserInfo, logout} from "@/api/user";

export default {
  components: {
    CollapseTransition,
    BaseNav,
    Modal
  },
  props: {
    type: {
      type: String,
      default:
        'default', // default|light
      description:
        'Look of the dashboard navbar. Default (Green) or light (gray)'
    }
  },
  computed: {
    routeName() {
      const {name} = this.$route;
      return this.capitalizeFirstLetter(name);
    }
  },
  data() {
    return {
      isLoggedIn: false, // 用户是否已登录
      activeNotifications: false,
      showMenu: false,
      searchModalVisible: false,
      searchQuery: '',
      searchText: '',// 初始化 searchText 数据属性为空字符串
      userInfo: {
        nickname: '',
        avatarAlt: '无',
        avatar: '',
      }
    };
  },
  created() {
    // 在这里检查用户是否已登录，并更新 isLoggedIn 变量
    this.isLoggedIn = checkAuthStatus();
    if (this.isLoggedIn) {
      this.showUserInfo();
    }
  },
  methods: {
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    },
    handleSearch() {
      if (this.searchText.trim() !== '') {
        // 构建目标路由对象
        const targetRoute = {
          path: '/search',
          query: {
            q: this.searchText, ...this.params
          }
        };
        // 检查当前路由是否与目标路由相同
        this.searchText = '';
        if (this.$route.path !== targetRoute.path ||
          JSON.stringify(this.$route.query) !== JSON.stringify(targetRoute.query)) {
          // 如果不相同，则导航到新的路由
          this.$router.push(targetRoute);
        } else {
          // 如果相同，则不执行导航
          console.warn('Avoided redundant navigation to current location');
        }
      }
    },
    showUserInfo() {
      // 1.先从浏览器缓存中获取
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
    async handleLogin() {
      try {
        await this.$router.push('/login');
      } catch (error) {
        console.error('登录时发生错误：', error);
      }
    },
    async handleLogout() {
      try {
        // 处理用户登出逻辑
        await logout();
        removeToken();
        removeUser();
        this.isLoggedIn = false;
        this.$toast.info("登出成功！");
        await this.$router.push('/home');
      } catch (error) {
        console.error('登出时发生错误：', error);
      }
    },
    async toProfile() {
      try {
        await this.$router.push('/profile');
      } catch (error) {
        console.error('发生错误：', error);
      }
    }
  }
};
</script>
