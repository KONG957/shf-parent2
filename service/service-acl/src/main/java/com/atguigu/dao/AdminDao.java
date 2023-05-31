package com.atguigu.dao;

import com.atguigu.base.BaseDao;
import com.atguigu.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.dao
 * @className: AdminDao
 * @author: kong
 * @description: TODO
 * @date: 2023/5/18 16:53
 * @version: 1.0
 */
@Repository
public interface AdminDao extends BaseDao {

    List<Admin> findAll();

    Admin getByUsername(String username);
}
