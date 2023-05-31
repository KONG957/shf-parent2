package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.service
 * @className: DictService
 * @author: kong
 * @description: TODO
 * @date: 2023/5/23 11:11
 * @version: 1.0
 */
public interface DictService extends BaseService<Dict> {


    List<Map<String,Object>> findZnodes(Long id);

    List<Dict> findListByDictCode(String dictCode);

    List<Dict> findListByParentId(Long id);

    String getNameById(Long id);

}
