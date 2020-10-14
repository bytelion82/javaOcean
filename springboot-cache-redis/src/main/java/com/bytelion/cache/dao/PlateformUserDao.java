package com.bytelion.cache.dao;

import com.bytelion.cache.entity.PlateformUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表(PlateformUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-14 17:07:34
 */
@Mapper
public interface PlateformUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PlateformUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PlateformUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param plateformUser 实例对象
     * @return 对象列表
     */
    List<PlateformUser> queryAll(PlateformUser plateformUser);

    /**
     * 新增数据
     *
     * @param plateformUser 实例对象
     * @return 影响行数
     */
    int insert(PlateformUser plateformUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PlateformUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PlateformUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PlateformUser> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<PlateformUser> entities);

    /**
     * 修改数据
     *
     * @param plateformUser 实例对象
     * @return 影响行数
     */
    int update(PlateformUser plateformUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}