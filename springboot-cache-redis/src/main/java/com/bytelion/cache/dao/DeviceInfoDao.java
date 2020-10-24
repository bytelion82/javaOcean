package com.bytelion.cache.dao;

import com.bytelion.cache.entity.DeviceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (DeviceInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-21 08:47:05
 */
@Mapper
public interface DeviceInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param name 名字
     * @return 实例对象
     */
    DeviceInfo queryByName(String name);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DeviceInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param deviceInfo 实例对象
     * @return 对象列表
     */
    List<DeviceInfo> queryAll(DeviceInfo deviceInfo);

    /**
     * 新增数据
     *
     * @param deviceInfo 实例对象
     * @return 影响行数
     */
    int insert(DeviceInfo deviceInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DeviceInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DeviceInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DeviceInfo> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<DeviceInfo> entities);

    /**
     * 修改数据
     *
     * @param deviceInfo 实例对象
     * @return 影响行数
     */
    boolean update(DeviceInfo deviceInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}