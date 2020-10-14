package com.bytelion.cache.service;

import com.bytelion.cache.entity.PlateformUser;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

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
     *redis数据库中key=value::key
     * @param id 主键
     * @return 实例对象
     * @desc @Cacheable 和CachePut 中的value、key 相同时，添加的数据，再查询时可以直接到redis中查到
     */
    @Cacheable(value = "plateformUser",key = "#id")
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
    @CachePut(value = "plateformUser",key = "#plateformUser.id")
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
     *CacheEvict 会清除redis数据库中key=value::key的值，同时也会删掉MySQL中的值
     * @param id 主键
     * @return 是否成功
     */
    @CacheEvict(value = "plateformUser",key = "#id")
    boolean deleteById(Integer id);

}