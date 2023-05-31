package com.atguigu.base;

import com.atguigu.util.CastUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.base
 * @className: BaseServiceImpl
 * @author: kong
 * @description: TODO
 * @date: 2023/5/18 15:26
 * @version: 1.0
 */
public abstract class BaseServiceImpl<T> implements BaseService<T>{

    protected abstract BaseDao getEntityDao();


    @Override
    public Integer insert(T t) {
        return  getEntityDao().insert(t);
    }

    @Override
    public void delete(Long id) {
        getEntityDao().delete(id);
    }

    @Override
    public Integer update(T t) {
      return getEntityDao().update(t);
    }

    @Override
    public T getById(Serializable id) {
       return (T) getEntityDao().getById(id);
    }

    @Override
    public PageInfo<T> findPage(Map<String, Object> filters) {
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 10);

        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<T>(getEntityDao().findPage(filters), 10);
    }
}