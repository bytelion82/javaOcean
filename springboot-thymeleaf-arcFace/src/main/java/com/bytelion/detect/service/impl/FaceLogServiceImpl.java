package com.bytelion.detect.service.impl;

import com.bytelion.detect.dao.FaceLogDao;
import com.bytelion.detect.entity.FaceLog;
import com.bytelion.detect.service.FaceLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 识别记录表(FaceLog)表服务实现类
 *
 * @author makejava
 * @since 2020-11-06 13:33:19
 */
@Service("faceLogService")
public class FaceLogServiceImpl implements FaceLogService {
    @Resource
    private FaceLogDao faceLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FaceLog queryById(Integer id) {
        return this.faceLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<FaceLog> queryAllByLimit(int offset, int limit) {
        return this.faceLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param faceLog 实例对象
     * @return 实例对象
     */
    @Override
    public FaceLog insert(FaceLog faceLog) {
        this.faceLogDao.insert(faceLog);
        return faceLog;
    }

    /**
     * 修改数据
     *
     * @param faceLog 实例对象
     * @return 实例对象
     */
    @Override
    public FaceLog update(FaceLog faceLog) {
        this.faceLogDao.update(faceLog);
        return this.queryById(faceLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.faceLogDao.deleteById(id) > 0;
    }

    @Override
    public List<FaceLog> queryFaceLogByDay(String tenantId, LocalDateTime start , LocalDateTime end) {
        return faceLogDao.queryFaceLogByDay(tenantId,start,end);
    }
}