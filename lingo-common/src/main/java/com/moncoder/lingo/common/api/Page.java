package com.moncoder.lingo.common.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Moncoder
 * @version 1.0
 * @description 通用分页数据封装类
 * @date 2024/3/28 15:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    /**
     * 当前页码
     */
    private Long pageNum;
    /**
     * 每页数量
     */
    private Long pageSize;
    /**
     * 总页数
     */
    private Long totalPage;
    /**
     * 总条数
     */
    private Long total;
    /**
     * 分页数据
     */
    private List<T> list;

    /**
     * spring-data分页数据重构
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> Page<T> restPage(org.springframework.data.domain.Page<T> page) {
        Page<T> result = new Page<>();
        result.setPageNum((long) page.getNumber());
        result.setPageSize((long) page.getSize());
        result.setTotalPage((long) page.getTotalPages());
        result.setTotal(page.getTotalElements());
        return result;
    }

    /**
     * mybatis-plus分页数据
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> Page<T> restPage(IPage<T> page) {
        Page<T> result = new Page<>();
        result.setPageNum(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotalPage(page.getPages());
        result.setTotal(page.getTotal());
        result.setList(page.getRecords());
        return result;
    }
}
