package com.atguigu.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseDao;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.dao.DictDao;
import com.atguigu.dao.HouseDao;
import com.atguigu.entity.House;
import com.atguigu.service.HouseService;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.impl
 * @className: HouseServiceImpl
 * @author: kong
 * @description: TODO
 * @date: 2023/5/24 9:01
 * @version: 1.0
 */
@Service(interfaceClass = HouseService.class)
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {

    @Autowired
    private HouseDao houseDao;

    @Autowired
    private DictDao dictDao;
    @Override
    protected BaseDao getEntityDao() {

        return houseDao;
    }


    @Override
    public void publish(Long id, Integer status) {
        House house = new House();
        house.setStatus(status);
        house.setId(id);
        houseDao.update(house);
    }

    @Override
    public PageInfo<HouseVo> findListPage(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo) {
            PageHelper.startPage(pageNum,pageSize);
            Page<HouseVo> page= houseDao.findListPage(houseQueryVo);
        List<HouseVo> result = page.getResult();
        for (HouseVo houseVo : result) {
            //户型：
            String houseTypeName = dictDao.getNameById(houseVo.getHouseTypeId());
            //楼层
            String floorName = dictDao.getNameById(houseVo.getFloorId());
            //朝向：
            String directionName = dictDao.getNameById(houseVo.getDirectionId());
            houseVo.setHouseTypeName(houseTypeName);
            houseVo.setFloorName(floorName);
            houseVo.setDirectionName(directionName);
        }
        return new PageInfo<HouseVo>(page, 2);
    }

    @Override
    public House getById(Serializable id) {
        House house = houseDao.getById(id);
        house.setHouseTypeName(dictDao.getNameById(house.getHouseTypeId()));
        house.setFloorName(dictDao.getNameById(house.getFloorId()));
        house.setBuildStructureName(dictDao.getNameById(house.getBuildStructureId()));
        house.setDecorationName(dictDao.getNameById(house.getDecorationId()));
        house.setDirectionName(dictDao.getNameById(house.getDirectionId()));
        house.setHouseUseName(dictDao.getNameById(house.getHouseUseId()));
        return house;
    }
}
