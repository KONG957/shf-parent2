package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.dao
 * @className: DictDao
 * @author: kong
 * @description: TODO
 * @date: 2023/5/23 11:12
 * @version: 1.0
 */
public interface DictDao extends BaseDao<Dict> {
        //根据父id查找子节点
        public List<Dict> findListByParentId(Long parentId);

        //统计是否为叶子节点
        public Integer countIsParent(Long id);
        //通过编码获取子节点数据 当城市不同时获取对应区域的房源
        public Dict getByDictCode(String dictCode);
        //通过id获取名字(当表中有字典id是可以通过这个方法获取name)
        String getNameById(Long id);





}
