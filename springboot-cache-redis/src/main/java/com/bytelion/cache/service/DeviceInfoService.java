package com.bytelion.cache.service;

import com.bytelion.cache.entity.DeviceInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * (DeviceInfo)表服务接口
 *
 * @author makejava
 * @since 2020-10-21 08:47:07
 */
public interface DeviceInfoService {

    /**
     * 通过name查询单条数据
     *
     * @param name 名字
     * @return 实例对象
     */
    @Cacheable(value = "device" ,key = "#name")
    DeviceInfo queryByName(String name);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Cacheable(value = "device",key = "#limit")
    List<DeviceInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param deviceInfo 实例对象
     * @return 实例对象
     */
    @CachePut(value = "device",key = "#deviceInfo.name")
    DeviceInfo insert(DeviceInfo deviceInfo);

    /**
     * 修改数据
     *
     * @param deviceInfo 实例对象
     * @return 实例对象
     */
    @CacheEvict(value = "device",allEntries = true,beforeInvocation = true)
    DeviceInfo update(DeviceInfo deviceInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}