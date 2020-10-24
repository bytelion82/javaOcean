package com.bytelion.cache.service.impl;

import com.bytelion.cache.dao.DeviceInfoDao;
import com.bytelion.cache.entity.DeviceInfo;
import com.bytelion.cache.service.DeviceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DeviceInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-10-21 08:47:07
 */
@Service("deviceInfoService")
public class DeviceInfoServiceImpl implements DeviceInfoService {
    @Resource
    private DeviceInfoDao deviceInfoDao;

    /**
     * 通过name 名字查询单条数据
     *
     * @param  name 名字
     * @return 实例对象
     */
    @Override
    public DeviceInfo queryByName(String name) {
        System.out.println("通过name 名字单条数据");
        return this.deviceInfoDao.queryByName(name);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<DeviceInfo> queryAllByLimit(int offset, int limit) {
        System.out.println("查询多条数据");
        return this.deviceInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param deviceInfo 实例对象
     * @return 实例对象
     */
    @Override
    public DeviceInfo insert(DeviceInfo deviceInfo) {
        System.out.println("新增数据");

        this.deviceInfoDao.insert(deviceInfo);
        return deviceInfo;
    }

    /**
     * 修改数据
     *
     * @param deviceInfo 实例对象
     * @return 实例对象
     */
    @Override
    public DeviceInfo update(DeviceInfo deviceInfo) {
        System.out.println("更新数据");

        this.deviceInfoDao.update(deviceInfo);
        return this.queryByName(deviceInfo.getName());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.deviceInfoDao.deleteById(id) > 0;
    }
}