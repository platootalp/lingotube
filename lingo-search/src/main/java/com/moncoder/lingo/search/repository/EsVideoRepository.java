package com.moncoder.lingo.search.repository;

import com.moncoder.lingo.search.domain.EsVideo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lenovo
 * @version 1.0
 * @description 搜索商品ES操作类
 * @date 2024/5/13 15:55
 */
public interface EsVideoRepository extends ElasticsearchRepository<EsVideo,Integer> {

}
