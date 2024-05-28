import request from "@/api/request";

/**
 * 上传视频
 * @param file
 * @returns {Promise<AxiosResponse<any>>}
 */
export const uploadVideo = async (file, onUploadProgress) => {
  try {
    const formData = new FormData();
    formData.append('file', file);

    const response = await request.post('/api/admin/video/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      },
      onUploadProgress, // 添加上传进度回调
      timeout: 60000,
    });

    return response;
  } catch (error) {
    throw error;
  }
};
/**
 * 上传缩略图
 * @param file
 * @returns {Promise<AxiosResponse<any>>}
 */
export const uploadVideoThumbnail = async (file) => {
  try {
    const formData = new FormData();
    formData.append('file', file);
    return await request.post('/api/admin/video/thumbnail/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data' // 设置请求头，确保后端正确处理表单数据
      }
    });
  } catch (error) {
    throw error;
  }
};
/**
 * 保存视频信息
 * @param model
 * @returns {Promise<AxiosResponse<any>>}
 */
export const saveVideo = async (model) => {
  try {
    return await request.post('/api/admin/video/save', model);
  } catch (error) {
    throw error;
  }
};
