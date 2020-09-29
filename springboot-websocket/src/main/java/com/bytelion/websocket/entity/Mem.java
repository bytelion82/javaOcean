package com.bytelion.websocket.entity;

import cn.hutool.core.util.NumberUtil;
import lombok.Data;

/**
 * @author : 太傅
 * 2020/9/28
 * @Func
 */

@Data
public class Mem {

    /**
     * 内存总量
     */
    private double total;

    /**
     * 已用内存
     */
    private double used;

    /**
     * 剩余内存
     */
    private double free;
    public double getUsage() {
        return NumberUtil.mul(NumberUtil.div(used, total, 4), 100);
    }
}
