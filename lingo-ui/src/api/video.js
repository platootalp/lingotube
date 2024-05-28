import request from "@/api/request";

/**
 * 获取最热视频
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getTrendingVideos = async () => {
  try {
    return await request.get('/api/home/video/trending/list');
  } catch (error) {
    throw error;
  }
};
/**
 * 获取最新视频
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getLatestVideos = async () => {
  try {
    return await request.get('/api/home/video/latest/list');
  } catch (error) {
    throw error;
  }
};
/**
 * 获取推荐视频
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getRecommendedVideos = async () => {
  try {
    return await request.get('/api/home/video/recommended/list');
  } catch (error) {
    throw error;
  }
};
/**
 * 根据id获取视频信息
 * @param id
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getVideo = async (id) => {
  try {
    return await request.get(`/api/video/${id}`);
  } catch (error) {
    throw error;
  }
};
/**
 * 获取视频点赞数
 * @param id
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getVideoLikes = async (id) => {
  try {
    return await request.get(`/api/video/likes/${id}`);
  } catch (error) {
    throw error;
  }
};
/**
 * 获取相关视频
 * @param id
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getRelatedVideoList = async (id, levelName) => {
  try {
    return await request.get(`/api/video/related/s?id=${id}&levelName=${levelName}`);
  } catch (error) {
    throw error;
  }
};
/**
 *
 * @param id
 * @param levelName
 * @returns {Promise<AxiosResponse<any>>}
 */
export const addVideoViews = async (id) => {
  try {
    return await request.put(`/api/video/views/add?id=${id}`);
  } catch (error) {
    throw error;
  }
};


/*********** 视频观看历史Api *********/

/**
 * 保存视频观看历史
 * @param model
 * @returns {Promise<AxiosResponse<any>>}
 */
export const saveHistory = async (model) => {
  try {
    return await request.post('/api/video/history/save', model);
  } catch (error) {
    throw error;
  }
};
/**
 * 获取用户全部视频观看历史
 * @param userId
 * @param titleKeyWord
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getHistoryList = async (userId, titleKeyWord) => {
  try {
    return await request.get(`/api/video/history/list?userId=${userId}&titleKeyWord=${titleKeyWord}`);
  } catch (error) {
    throw error;
  }
};
/**
 * 清空用户视频观看历史
 * @param userId
 * @returns {Promise<AxiosResponse<any>>}
 */
export const clearHistory = async (userId) => {
  try {
    return await request.delete(`/api/video/history/all?userId=${userId}`);
  } catch (error) {
    throw error;
  }
};
/**
 * 删除历史记录
 * @param userId
 * @param videoIds
 * @returns {Promise<AxiosResponse<any>>}
 */
export const deleteHistory = async (userId, videoIds) => {
  try {
    const idsString = videoIds.join(','); // 将视频 ID 数组转换为以逗号分隔的字符串
    return await request.delete(`/api/video/history/s?userId=${userId}&videoIds=${idsString}`);
  } catch (error) {
    throw error;
  }
};


/*********** 视频稍后再看Api *********/
/**
 *
 * @param userId
 * @param videoId
 * @returns {Promise<AxiosResponse<any>>}
 */
export const isExistWatchLater = async (userId, videoId) => {
  try {
    return await request.get(`/api/video/watchLater/exist?userId=${userId}&videoId=${videoId}`);
  } catch (error) {
    throw error;
  }
};
/**
 *
 * @param model
 * @returns {Promise<AxiosResponse<any>>}
 */
export const saveWatchLater = async (model) => {
  try {
    return await request.post('/api/video/watchLater/save', model);
  } catch (error) {
    throw error;
  }
};
/**
 *
 * @param userId
 * @param sort
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getWatchLaterList = async (userId, sort) => {
  try {
    return await request.get(`/api/video/watchLater/list?userId=${userId}&sort=${sort}`);
  } catch (error) {
    throw error;
  }
};
/**
 *
 * @param userId
 * @returns {Promise<AxiosResponse<any>>}
 */
export const deleteWatched = async (userId) => {
  try {
    return await request.delete(`/api/video/watchLater/watched?userId=${userId}`);
  } catch (error) {
    throw error;
  }
};
/**
 *
 * @param userId
 * @param videoId
 * @returns {Promise<AxiosResponse<any>>}
 */
export const deleteWatchLater = async (userId, videoId) => {
  try {
    return await request.delete(`/api/video/watchLater?userId=${userId}&videoId=${videoId}`);
  } catch (error) {
    throw error;
  }
};


/*********** 赞过的视频Api *********/
/**
 *
 * @param userId
 * @param videoId
 * @returns {Promise<AxiosResponse<any>>}
 */
export const isExistLiked = async (userId, videoId) => {
  try {
    return await request.get(`/api/video/like/exist?userId=${userId}&videoId=${videoId}`);
  } catch (error) {
    throw error;
  }
};
/**
 * 点赞视频
 * @param userId
 * @param videoId
 * @returns {Promise<AxiosResponse<any>>}
 */
export const likeVideo = async (userId, videoId) => {
  try {
    return await request.post(`/api/video/like/save?userId=${userId}&videoId=${videoId}`);
  } catch (error) {
    throw error;
  }
};
/**
 *
 * @param userId
 * @param sort
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getLikedVideoList = async (userId, titleKeyWord) => {
  try {
    return await request.get(`/api/video/like/list?userId=${userId}&titleKeyWord=${titleKeyWord}`);
  } catch (error) {
    throw error;
  }
};
/**
 *
 * @param userId
 * @param videoId
 * @returns {Promise<AxiosResponse<any>>}
 */
export const deleteLiked = async (userId, videoId) => {
  try {
    return await request.delete(`/api/video/like?userId=${userId}&videoId=${videoId}`);
  } catch (error) {
    throw error;
  }
};
/**
 *
 * @param userId
 * @returns {Promise<AxiosResponse<any>>}
 */
export const clearLiked = async (userId) => {
  try {
    return await request.delete(`/api/video/like/all?userId=${userId}`);
  } catch (error) {
    throw error;
  }
};


/*********** 视频分类Api *********/
/**
 * 获取全部视频分类
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getCategories = async () => {
  try {
    return await request.get('/api/video/category/all');
  } catch (error) {
    throw error;
  }
};
/**
 * 根据id获取分类信息
 * @param id
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getCategoryInfo = async (id) => {
  try {
    return await request.get(`/api/video/category/${id}`);
  } catch (error) {
    throw error;
  }
};
/**
 * 根据分类id获取分页视频
 * @param id
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getPageVideosByCategoryId = async (categoryId, curPage, pageSize, sort) => {
  try {
    return await request.get(`/api/video/category/page?categoryId=${categoryId}&curPage=${curPage}&pageSize=${pageSize}&sort=${sort}`);
  } catch (error) {
    throw error;
  }
};


/*********** 视频等级Api *********/
/**
 * 获取全部视频等级
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getLevels = async () => {
  try {
    return await request.get('/api/video/level/all');
  } catch (error) {
    throw error;
  }
};
/**
 * 根据id获取等级信息
 * @param id
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getLevelInfo = async (id) => {
  try {
    return await request.get(`/api/video/level/${id}`);
  } catch (error) {
    throw error;
  }
};
/**
 * 根据等级id获取分页视频
 * @param id
 * @returns {Promise<AxiosResponse<any>>}
 */
export const getPageVideosByLevelId = async (levelId, curPage, pageSize, sort) => {
  try {
    return await request.get(`/api/video/level/page?levelId=${levelId}&curPage=${curPage}&pageSize=${pageSize}&sort=${sort}`);
  } catch (error) {
    throw error;
  }
};
