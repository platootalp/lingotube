package com.moncoder.lingo.common.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
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
public class LPage<T> {
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
     * pagehelper分页
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> LPage<T> restPage(List<T> list) {
        LPage<T> result = new LPage<>();
        PageInfo<T> pageInfo = new PageInfo<>();
        result.setPageNum((long) pageInfo.getPageNum());
        result.setPageSize((long) pageInfo.getPageSize());
        result.setTotalPage((long) pageInfo.getPages());
        result.setTotal(pageInfo.getTotal());
        result.setList(list);
        return result;
    }


    /**
     * spring-data分页数据重构
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> LPage<T> restPage(org.springframework.data.domain.Page<T> page) {
        LPage<T> result = new LPage<>();
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
    public static <T> LPage<T> restPage(IPage<T> page) {
        LPage<T> result = new LPage<>();
        result.setPageNum(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotalPage(page.getPages());
        result.setTotal(page.getTotal());
        result.setList(page.getRecords());
        return result;
    }
}
