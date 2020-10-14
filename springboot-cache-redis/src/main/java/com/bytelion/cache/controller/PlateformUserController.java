package com.bytelion.cache.controller;

import com.bytelion.cache.entity.PlateformUser;
import com.bytelion.cache.service.PlateformUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 用户表(PlateformUser)表控制层
 *
 * @author makejava
 * @since 2020-10-14 17:07:39
 */
@RestController
@RequestMapping("/plateformUser")
public class PlateformUserController {
    /**
     * 服务对象
     */
    @Resource
    private PlateformUserService plateformUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public PlateformUser selectOne(Integer id) {
        System.out.println("get--1");
        return this.plateformUserService.queryById(id);
    }

    @GetMapping("/del")
    public boolean delete(Integer id){
        System.out.println("del--1");
        return this.plateformUserService.deleteById(id);
    }

    @PostMapping("/add")
    public HashMap<String, Object> add(PlateformUser plateformUser){
        HashMap<String, Object> result = new HashMap<>();
        System.out.println(plateformUser);
        PlateformUser update = this.plateformUserService.insert(plateformUser);
        if (null==update){
            result.put("status",false);
        }else {
            result.put("status",true);
        }
        result.put("entity",update);
        System.out.println("add--1");
        return result;
    }

}