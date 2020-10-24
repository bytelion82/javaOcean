package com.bytelion.cache.controller;

import com.bytelion.cache.entity.DeviceInfo;
import com.bytelion.cache.service.DeviceInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DeviceInfo)表控制层
 *
 * @author makejava
 * @since 2020-10-21 08:47:08
 */
@RestController
@RequestMapping("/deviceInfo")
public class DeviceInfoController {
    /**
     * 服务对象
     */
    @Resource
    private DeviceInfoService deviceInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param name 名字
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public DeviceInfo selectOne(String name) {
        return this.deviceInfoService.queryByName(name);
    }


    @GetMapping("/inset")
    public DeviceInfo insert(DeviceInfo deviceInfo){
        DeviceInfo insert = deviceInfoService.insert(deviceInfo);
        return insert;
    }

    @GetMapping("/all")
    public List<DeviceInfo> queryList(){
        List<DeviceInfo> all = deviceInfoService.queryAllByLimit(0, 10);
        return all;
    }

    @GetMapping("/update")
    public DeviceInfo update(DeviceInfo deviceInfo){
        DeviceInfo update = deviceInfoService.update(deviceInfo);
        return update;
    }

}