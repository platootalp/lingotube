import request from "@/api/request";

/**
 * 发送验证码
 * @param {string} email - 要发送验证码的邮箱地址
 * @returns {Promise<object>} - 发送验证码后后端返回的 Promise
 */
export const sendVerifyCode = async (email) => {
  try {
    const response = await request.post('/api/user/verifyCode?email=' + email);
    console.log(response);
    return response;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
/**
 * 重置密码
 * @param model
 * @returns {Promise<AxiosResponse<any>>}
 */
export const resetPassword = async (model) => {
  try {
    return await request.put('/api/user/password/reset', model);
  } catch (error) {
    throw error;
  }
};
/**
 * 注册
 * @param model
 * @returns {Promise<AxiosResponse<any>>}
 */
export const register = async (model) => {
  try {
    return await request.post('/api/user/register', model);
  } catch (error) {
    throw error;
  }
};
/**
 * 登陆
 * @param model
 * @returns {Promise<AxiosResponse<any>>}
 */
export const login = async (model) => {
  try {
    return await request.post('/api/user/login', model);
  } catch (error) {
    throw error;
  }
};
/**
 * 登出
 * @returns {Promise<void>}
 */
export const logout = async () => {

};
/**
 * 获取用户信息
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getUserInfo = async () => {
  try {
    return await request.get('/api/user/info');
  } catch (error) {
    throw error;
  }
};
/**
 * 更新用户信息
 * @param model
 * @returns {Promise<AxiosResponse<any>>}
 */
export const updateUserInfo = async (model) => {
  try {
    return await request.put('/api/user/info', model);
  } catch (error) {
    throw error;
  }
};
/**
 * 获取当前用户头像
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getAvatar = async () => {
  try {
    return await request.get('/api/user/avatar');
  } catch (error) {
    throw error;
  }
};
/**
 * 修改用户头像
 * @param file
 * @returns {Promise<AxiosResponse<any>>}
 */
export const updateAvatar = async (file) => {
  try {
    return await request.put('/api/user/avatar', file, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
  } catch (error) {
    throw error;
  }
};




