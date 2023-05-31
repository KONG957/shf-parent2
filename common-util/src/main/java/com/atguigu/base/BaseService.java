package com.atguigu.base;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.base
 * @className: BaseService
 * @author: kong
 * @description: TODO
 * @date: 2023/5/18 15:24
 * @version: 1.0
 */
public interface BaseService<T>{
    Integer insert(T t);

    void delete(Long id);

    Integer update(T t);

    T getById(Serializable id);

    PageInfo<T> findPage(Map<String, Object> filters);
}
