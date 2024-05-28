<template>
  <div class="wrapper">
    <notifications></notifications>

    <side-bar>
      <template slot="links">
        <sidebar-item
          :link="{
              name: '首页',
              path: '/home',
              icon: 'ni ni-tv-2 text-primary',
            }"
        >
        </sidebar-item>
        <!-- 分类菜单 -->
        <sidebar-item
          :menu="true"
          :link="{
                name: '频道',
                icon: 'ni ni-collection text-pink',
                children: categories
              }"
        >
          <!-- 子种类 -->
          <sidebar-item
            v-for="category in categories"
            :key="category.name"
            :link="{
                    name: category.name,
                    path: '/category/'+ category.id,
                  }"
          ></sidebar-item>
        </sidebar-item>
        <!-- 等级菜单 -->
        <sidebar-item
          :menu="true"
          :link="{
                name: '等级',
                icon: 'ni ni-chart-bar-32 text-info',
                children: levels
              }"
        >
          <!-- 子种类 -->
          <sidebar-item
            v-for="level in levels"
            :key="level.name"
            :link="{
                    name: level.name,
                    path: '/level/'+ level.id,
                  }"
          ></sidebar-item>
        </sidebar-item>
        <div class="border-bottom"></div>
        <sidebar-item
          :link="{
                  name: '我的账号',
                  path: '/profile',
                  icon: 'ni ni-single-02 text-success'
                  }">
        </sidebar-item>
        <sidebar-item
          :link="{
                  name: '观看历史',
                  path: '/history',
                  icon: 'ni ni-watch-time '
                  }">
        </sidebar-item>
        <sidebar-item
          :link="{
                  name: '赞过的视频',
                  path: '/videoLike',
                  icon: 'ni ni-like-2 text-red'
                  }">
        </sidebar-item>
        <sidebar-item
          :link="{
                  name: '我的收藏',
                  path: '/favorite',
                  icon: 'ni ni-favourite-28 text-orange'
                  }">
        </sidebar-item>
        <sidebar-item
          :link="{
                  name: '稍后再看',
                  path: '/watchLater',
                  icon: 'ni ni-time-alarm text-blue'
                  }">
        </sidebar-item>
        <sidebar-item
          :link="{
                  name: '上传视频',
                  path: '/upload',
                  icon: 'ni ni-cloud-upload-96 text-yellow'
                  }">
        </sidebar-item>
<!--        <div class="border-bottom"></div>-->
<!--        <sidebar-item-->
<!--          :link="{-->
<!--                name: 'Icons',-->
<!--                path: '/icons',-->
<!--                icon: 'ni ni-planet text-blue'-->
<!--                }"-->
<!--        >-->
<!--        </sidebar-item>-->

      </template>
    </side-bar>

    <div class="main-content">

      <dashboard-navbar :type="$route.meta.navbarType"></dashboard-navbar>

      <div @click="$sidebar.displaySidebar(false)">
        <fade-transition :duration="200" origin="center top" mode="out-in">
          <!-- your content here -->
          <router-view></router-view>
        </fade-transition>
      </div>

      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>

  </div>
</template>
<script>
/* eslint-disable no-new */
import PerfectScrollbar from 'perfect-scrollbar';
import 'perfect-scrollbar/css/perfect-scrollbar.css';

function hasElement(className) {
  return document.getElementsByClassName(className).length > 0;
}

function initScrollbar(className) {
  if (hasElement(className)) {
    new PerfectScrollbar(`.${className}`);
  } else {
    // try to init it later in case this component is loaded async
    setTimeout(() => {
      initScrollbar(className);
    }, 100);
  }
}

import DashboardNavbar from './DashboardNavbar.vue';
import ContentFooter from './ContentFooter.vue';
import DashboardContent from './Content.vue';
import {FadeTransition} from 'vue2-transitions';
import {getCategories, getLevels} from "@/api/video.js";

export default {
  components: {
    DashboardNavbar,
    ContentFooter,
    DashboardContent,
    FadeTransition
  },
  data() {
    return {
      categories: [],
      levels: [],
    }
  },
  created() {
    this.fetchCategories();
    this.fetchLevels();
  },
  mounted() {
    this.initScrollbar()
  },
  methods: {
    initScrollbar() {
      let isWindows = navigator.platform.startsWith('Win');
      if (isWindows) {
        initScrollbar('sidenav');
      }
    },
    async fetchCategories() {
      try {
        const response = await getCategories();
        // 检查返回的状态码
        if (response.data.code === 200) {
          this.categories = response.data.data;
        } else {
          console.error(`获取失败: ${response.data.message}`);
        }
      } catch (error) {
        console.error('未知错误:', error);
      }
    },
    async fetchLevels() {
      try {
        const response = await getLevels();
        // 检查返回的状态码
        if (response.data.code === 200) {
          this.levels = response.data.data;
        } else {
          console.error(`获取失败: ${response.data.message}`);
        }
      } catch (error) {
        console.error('未知错误:', error);
      }
    }
  },
};
</script>
<style lang="scss">
</style>
