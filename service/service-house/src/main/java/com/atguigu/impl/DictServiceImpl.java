package com.atguigu.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseDao;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.dao.DictDao;
import com.atguigu.entity.Dict;
import com.atguigu.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.impl
 * @className: DictServiceImpl
 * @author: kong
 * @description: TODO
 * @date: 2023/5/23 11:09
 * @version: 1.0
 */
@Service(interfaceClass = DictService.class)
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {


    @Autowired
    private DictDao dictDao;
    @Override
    protected BaseDao getEntityDao() {
        return dictDao;
    }


    @Override
    public List<Map<String, Object>> findZnodes(Long id) {

        //根据父亲的id查出点击节点的子节点
        List<Dict> list = dictDao.findListByParentId(id);
        //创建一个list用来存放查找出来的map集合(map集合用来存储ztree需要的属性用来返回给前端)
        ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
        for (Dict dict : list) {
            //调用dict  dao层的一个根据父亲id计算的计数器来统计该子节点是否有孩子节点
            Integer countIsParent = dictDao.countIsParent(dict.getId());
            //new一个用来存name  id  isParent的map集合
            Map<String, Object> map = new HashMap<>();
            //存入数据
            map.put("name",dict.getName());
            map.put("id",dict.getId());
            map.put("isParent",countIsParent>0? true :false);
            //将map添加进入list
            arrayList.add(map);
        }



        return arrayList;
    }



    @Override //通过编码获取子节点数据 当城市不同时获取对应区域的房源
    public List<Dict> findListByDictCode(String dictCode) {
        Dict dict = dictDao.getByDictCode(dictCode);
        if(null == dict) return null;
        List<Dict> list = dictDao.findListByParentId(dict.getId());
        return list;

    }

    @Override//查找所有该id的孩子
    public List<Dict> findListByParentId(Long id) {
        return dictDao.findListByParentId(id);
    }

    @Override
    public String getNameById(Long id) {
        return dictDao.getNameById(id);
    }
}

