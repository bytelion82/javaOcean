package com.bytelion.mysql.service;


import com.bytelion.mysql.entity.PlateformUser;

import java.util.List;

/**
 * 用户表(PlateformUser)表服务接口
 *
 * @author makejava
 * @since 2020-10-14 17:07:37
 */
public interface PlateformUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PlateformUser queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PlateformUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param plateformUser 实例对象
     * @return 实例对象
     */
    PlateformUser insert(PlateformUser plateformUser);

    /**
     * 修改数据
     *
     * @param plateformUser 实例对象
     * @return 实例对象
     */
    PlateformUser update(PlateformUser plateformUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}