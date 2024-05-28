import DashboardLayout from '@/views/Layout/DashboardLayout.vue';
import AuthLayout from '@/views/Pages/AuthLayout.vue';

const routes = [
  {
    path: '/',
    redirect: 'home',
    component: DashboardLayout,
    children: [
      {
        path: '/home',
        name: 'home',
        component: () => import('../views/Home.vue')
      },
      {
        path: '/video/:id/:levelName',
        name: 'videoPlayer',
        component: () => import('../views/VideoPlayer.vue'),
        props: true // 将路由参数作为组件的props传递
      },
      {
        path: '/search',
        name: 'search',
        component: () => import('../views/Search'),
        props: true // 将路由参数作为组件的props传递
      },
      {
        path: '/category/:id',
        name: 'category',
        component: () => import('../views/Category.vue'),
        props: true
      },
      {
        path: '/level/:id',
        name: 'level',
        component: () => import('../views/Level.vue'),
        props: true
      },
      {
        path: '/profile',
        name: 'profile',
        component: () => import('../views/Pages/UserProfile.vue'),
        meta: {requiresAuth: true} // 添加一个 meta 字段，用于标记需要验证用户身份的路由
      },
      {
        path: '/favorite',
        name: 'favorite',
        component: () => import( '../views/Favorite.vue'),
        meta: {requiresAuth: true}
      },
      {
        path: '/history',
        name: 'history',
        component: () => import('../views/History.vue'),
        meta: {requiresAuth: true}
      },
      {
        path: '/watchLater',
        name: 'watchLater',
        component: () => import('../views/WatchLater.vue'),
        meta: {requiresAuth: true}
      },
      {
        path: '/videoLike',
        name: 'videoLike',
        component: () => import('../views/VideoLike.vue'),
        meta: {requiresAuth: true}
      },
      {
        path: '/upload',
        name: 'upload',
        component: () => import('../views/Upload'),
        meta: {requiresAuth: true}
      },
      // {
      //   path: '/icons',
      //   name: 'icons',
      //   component: () => import(/* webpackChunkName: "demo" */ '../views/Icons.vue')
      // },
    ]
  },
  {
    path: '/',
    redirect: 'login',
    component: AuthLayout,
    children: [
      {
        path: '/login',
        name: 'login',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/Login.vue')
      },
      {
        path: '/register',
        name: 'register',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/Register.vue')
      },
      {
        path: '/reset-password',
        name: 'reset-password',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/ResetPassword.vue')
      },
      {
        path: '*',
        component: () => import(/* webpackChunkName: "demo" */ '../views/NotFoundPage')
      }
    ]
  }
];

export default routes;
