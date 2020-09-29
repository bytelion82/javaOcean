package com.bytelion.websocket.entity.vo;

import com.bytelion.websocket.entity.Cpu;
import com.bytelion.websocket.playload.KV;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author : 太傅
 * 2020/9/28
 * @Func
 */
@Data
public class CpuVO {
    List<KV> data = Lists.newArrayList();

    public static CpuVO create(Cpu cpu) {
        CpuVO vo = new CpuVO();
        vo.data.add(new KV("核心数", cpu.getCpuNum()));
        vo.data.add(new KV("CPU总的使用率", cpu.getTotal()));
        vo.data.add(new KV("CPU系统使用率", cpu.getSys() + "%"));
        vo.data.add(new KV("CPU用户使用率", cpu.getUsed() + "%"));
        vo.data.add(new KV("CPU当前等待率", cpu.getWait() + "%"));
        vo.data.add(new KV("CPU当前空闲率", cpu.getFree() + "%"));
        return vo;
    }
}
