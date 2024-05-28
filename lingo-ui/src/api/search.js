import request from "@/api/request";

/**
 * 搜索视频
 * @param params
 * @returns {Promise<*>}
 */
export const search = async (params) => {
  try {
    const response = await request.get('/api/search/video', { params });
    return response;
  } catch (error) {
    throw error;
  }
};
