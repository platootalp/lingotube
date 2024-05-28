import Vue from 'vue';
import VueRouter from 'vue-router';
import routes from './routes';
import {checkAuthStatus} from "@/api/store";

Vue.use(VueRouter);

// configure router
const router = new VueRouter({
  routes,
  linkActiveClass: 'active',
  scrollBehavior: (to, from ,savedPosition) => {
    if (savedPosition) {
      return savedPosition;
    }
    if (to.hash) {
      return { selector: to.hash };
    }
    return { x: 0, y: 0 };
  }
});

// 全局前置守卫
router.beforeEach(async (to, from, next) => {
  // 检查是否需要验证用户身份
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // 检查用户是否已经登录
    const isAuthenticated = checkAuthStatus(); // 检查用户登录状态的方法，根据你的实际情况实现
    if (!isAuthenticated) {
      Vue.$toast.warning('请先登录！'); // 显示提示消息
      // 如果用户未登录，则重定向到登录页面
      next({path: '/login'});
    } else {
      next();
    }
  } else {
    // 不需要验证用户身份，直接允许访问
    next();
  }
});
export default router;
