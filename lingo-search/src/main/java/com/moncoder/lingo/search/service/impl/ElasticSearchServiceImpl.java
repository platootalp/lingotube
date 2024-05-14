package com.moncoder.lingo.search.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.moncoder.lingo.search.service.IElasticSearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Moncoder
 * @version 1.0
 * @description ElasticSearch服务
 * @date 2024/5/14 11:27
 */
@Slf4j
@Service
public class ElasticSearchServiceImpl implements IElasticSearchService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public void create(String index, Integer id, Object source, Object target) {
        // 1.准备Request对象
        IndexRequest request = new IndexRequest(index).id(id.toString());
        // 2.准备Json文档
        BeanUtils.copyProperties(source, target);
        request.source(JSONUtil.toJsonStr(target), XContentType.JSON);
        // 3.发送请求
        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void delete(String index, Integer id) {
        // 1.准备Request对象
        DeleteRequest request = new DeleteRequest(index).id(id.toString());
        // 2.发送请求
        try {
            restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String index, Integer id, Map<String,Object> map) {
        // 1.准备Request对象
        UpdateRequest request = new UpdateRequest(index,id.toString());
        // 2.准备请求参数
        request.doc(map);
        // 3.发送请求
        try {
            restHighLevelClient.update(request, RequestOptions.DEFAULT);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void bulkCreate(String index, Map<Integer, Object> map, Class<?> targetClass) {
        BulkRequest request = new BulkRequest(index);
        for (Integer id : map.keySet()) {
            Object source = map.get(id);
            Object target = BeanUtil.copyProperties(source, targetClass);
            request.add(new IndexRequest(id.toString())
                    .source(JSONUtil.toJsonStr(target),XContentType.JSON));
        }
        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


}
