package com.bytelion.mysql.controller;

import com.bytelion.mysql.entity.Messages;
import com.bytelion.mysql.entity.PlateformUser;
import com.bytelion.mysql.service.PlateformUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 用户表(PlateformUser)表控制层
 *
 * @author makejava
 * @since 2020-10-14 17:07:39
 */
@Controller
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
    @ResponseBody
    public PlateformUser selectOne(Integer id) {
        System.out.println("success");
        return this.plateformUserService.queryById(id);
    }


    /**
     * thymeleaf 页面显示属性
     * @param model
     * @return
     */
    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("mes","太傅");
        return "message/list";
    }


    /**
     * thymeleaf 页面显示属性
     * @param model
     * @return
     */
    @GetMapping("/form")
    public String createForm(Model model) {
        Messages messages = new Messages();
        messages.setGoods(Arrays.asList("香蕉","苹果")).setName("太傅").setMoney(77);
        model.addAttribute("mes",messages);
        return "message/form";
    }
}