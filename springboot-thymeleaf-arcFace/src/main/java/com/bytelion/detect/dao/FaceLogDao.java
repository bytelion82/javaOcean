package com.bytelion.detect.dao;

import com.bytelion.detect.entity.FaceLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 识别记录表(FaceLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-06 13:33:19
 */
@Mapper
public interface FaceLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FaceLog queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<FaceLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param faceLog 实例对象
     * @return 对象列表
     */
    List<FaceLog> queryAll(FaceLog faceLog);

    /**
     * 新增数据
     *
     * @param faceLog 实例对象
     * @return 影响行数
     */
    int insert(FaceLog faceLog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FaceLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<FaceLog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FaceLog> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<FaceLog> entities);

    /**
     * 修改数据
     *
     * @param faceLog 实例对象
     * @return 影响行数
     */
    int update(FaceLog faceLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    List<FaceLog> queryFaceLogByDay(String tenantId, LocalDateTime start ,LocalDateTime end);

}