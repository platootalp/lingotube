package com.moncoder.lingo.search.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.moncoder.lingo.common.constant.ElasticSearchConstant;
import com.moncoder.lingo.entity.VmsVideo;
import com.moncoder.lingo.mapper.VmsVideoMapper;
import com.moncoder.lingo.search.dao.VmsVideoDao;
import com.moncoder.lingo.search.domain.EsVideo;
import com.moncoder.lingo.search.repository.EsVideoRepository;
import com.moncoder.lingo.search.service.IElasticSearchService;
import com.moncoder.lingo.search.service.IEsVideoService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO
 * @date 2024/5/13 14:30
 */
@Service
public class EsVideoServiceImpl implements IEsVideoService {

    @Autowired
    private VmsVideoDao videoDao;

    @Autowired
    private VmsVideoMapper videoMapper;

    @Autowired
    private EsVideoRepository videoRepository;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private IElasticSearchService searchService;

    @Override
    public EsVideo create(Integer id) {
        // 转换成JSON字符串
        VmsVideo vmsVideo = videoMapper.selectById(id);
        EsVideo esVideo = new EsVideo();
        searchService.create(ElasticSearchConstant.VIDEO_INDEX, id, vmsVideo, esVideo);
        return esVideo;
    }

    @Override
    public int importAll() {
        // 从数据库中查询出数据
        List<EsVideo> esVideos = videoDao.selectAllVideos().stream().map(video -> {
            EsVideo esVideo = new EsVideo();
            BeanUtils.copyProperties(video, esVideo);
            return esVideo;
        }).collect(Collectors.toList());

        // 将数据保存到ES中
        BulkRequest bulkRequest = new BulkRequest("lingo_video");
        esVideos.forEach(esVideo -> bulkRequest.add(
                new IndexRequest().id(esVideo.getId().toString())
                        .source(JSONUtil.toJsonStr(esVideo), XContentType.JSON)));
        try {
            restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return esVideos.size();
    }

    @Override
    public void deleteAll() {
        List<Integer> ids = videoDao.selectAllVideos().stream().map(EsVideo::getId)
                .collect(Collectors.toList());
        deleteBatch(ids);
    }

    @Override
    public void deleteById(Integer id) {
        searchService.delete(ElasticSearchConstant.VIDEO_INDEX, id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        BulkRequest bulkRequest = new BulkRequest();
        ids.forEach(id -> bulkRequest.add(new DeleteRequest().index("lingo_video").id(id.toString())));
        try {
            restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
