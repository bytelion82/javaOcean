package com.bytelion.detect.service;

import com.bytelion.detect.entity.FaceLog;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 识别记录表(FaceLog)表服务接口
 *
 * @author makejava
 * @since 2020-11-06 13:33:19
 */
public interface FaceLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FaceLog queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<FaceLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param faceLog 实例对象
     * @return 实例对象
     */
    FaceLog insert(FaceLog faceLog);

    /**
     * 修改数据
     *
     * @param faceLog 实例对象
     * @return 实例对象
     */
    FaceLog update(FaceLog faceLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<FaceLog> queryFaceLogByDay(String tenantId, LocalDateTime start , LocalDateTime end);

}