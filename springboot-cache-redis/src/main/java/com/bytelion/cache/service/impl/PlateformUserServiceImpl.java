package com.bytelion.cache.service.impl;

import com.bytelion.cache.dao.PlateformUserDao;
import com.bytelion.cache.entity.PlateformUser;
import com.bytelion.cache.service.PlateformUserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(PlateformUser)表服务实现类
 *
 * @author makejava
 * @since 2020-10-14 17:07:38
 */
@Service("plateformUserService")
public class PlateformUserServiceImpl implements PlateformUserService {
    @Resource
    private PlateformUserDao plateformUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
//    @Cacheable(value = "plateformUser",key = "#id")
    public PlateformUser queryById(Integer id) {
        System.out.println("get---2");
        return this.plateformUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<PlateformUser> queryAllByLimit(int offset, int limit) {
        return this.plateformUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param plateformUser 实例对象
     * @return 实例对象
     */
    @Override
//    @CachePut(value = "plateformUser",key = "#plateformUser.userName")
    public PlateformUser insert(PlateformUser plateformUser) {
        this.plateformUserDao.insert(plateformUser);
        System.out.println("add---2");
        return plateformUser;
    }

    /**
     * 修改数据
     *
     * @param plateformUser 实例对象
     * @return 实例对象
     */
    @Override
    public PlateformUser update(PlateformUser plateformUser) {
        this.plateformUserDao.update(plateformUser);
        return this.queryById(plateformUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
//    @CacheEvict(value = "plateformUser",key = "#id")
    public boolean deleteById(Integer id) {
        System.out.println("del---2");
        return this.plateformUserDao.deleteById(id) > 0;
    }
}