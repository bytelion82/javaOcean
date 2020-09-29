package com.bytelion.websocket.entity.vo;

import com.bytelion.websocket.entity.Mem;
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
public class MemVO {
    List<KV> data = Lists.newArrayList();

    public static MemVO create(Mem mem) {
        MemVO vo = new MemVO();
        vo.data.add(new KV("内存总量", mem.getTotal() + "G"));
        vo.data.add(new KV("已用内存", mem.getUsed() + "G"));
        vo.data.add(new KV("剩余内存", mem.getFree() + "G"));
        vo.data.add(new KV("使用率", mem.getUsage() + "%"));
        return vo;
    }
}
