/**
 * 从本地存储中获取令牌
 * @param token
 */
export function setToken(token) {
  localStorage.setItem('token', token);
}

/**
 * 从本地存储中获取令牌
 * @returns {string}
 */
export function getToken() {
  return localStorage.getItem('token');
}

/**
 * 从本地存储中移除令牌
 */
export function removeToken() {
  localStorage.removeItem('token');
}

/**
 * 检查用户的登录状态
 * @returns {boolean}
 */
export function checkAuthStatus() {
  const token = getToken();
  return !!token; // 如果有令牌，则认为用户已登录
}

/**
 * 保存用户信息
 * @param userInfo
 */
export function setUser(userInfo){
  localStorage.setItem('user', JSON.stringify(userInfo));
}

/**
 * 从localStorage中获取用户信息
 * @returns {any}
 */
export function getUser(){
  return JSON.parse(localStorage.getItem('user'));
}

/**
 * 从localStorage中移除用户信息
 */
export function removeUser(){
  return localStorage.removeItem('user')
}
