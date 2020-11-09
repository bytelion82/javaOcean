package com.bytelion.plus.models.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bytelion.plus.models.order.entity.OceanOrder;
import com.bytelion.plus.models.order.service.IOceanOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author 太傅
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/order/ocean-order")
public class OceanOrderController {

    private final IOceanOrderService orderService;

    public OceanOrderController(IOceanOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("one")
    public OceanOrder getOne(){
        OceanOrder one = orderService.getOne(new QueryWrapper<OceanOrder>().eq("name", "刘"));
        return one;
    }

}
