import axios from 'axios';

axios.defaults.withCredentials = true; // 允许发送跨域请求时携带 cookie
axios.defaults.mode = "cors"; // 设置跨域请求模式为 cors

// 创建一个 Axios 实例
const request = axios.create({
  // baseURL: 'https://api.example.com', // 设置基本的 API 地址
  timeout: 5000, // 请求超时时间
});

// 添加请求拦截器
request.interceptors.request.use(
  function (config) {
    // 在发送请求之前添加 token 到请求头中
    const token = localStorage.getItem('token'); // 从本地存储中获取 token
    if (token) {
      config.headers.Authorization = `Bearer ${token}`; // 添加 token 到请求头中
    }
    return config;
  },
  function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  }
);

export default request;
