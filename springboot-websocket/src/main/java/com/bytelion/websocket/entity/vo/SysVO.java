package com.bytelion.websocket.entity.vo;

import com.bytelion.websocket.entity.Sys;
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
public class SysVO {
    List<KV> data = Lists.newArrayList();

    public static SysVO create(Sys sys) {
        SysVO vo = new SysVO();
        vo.data.add(new KV("服务器名称", sys.getComputerName()));
        vo.data.add(new KV("服务器Ip", sys.getComputerIp()));
        vo.data.add(new KV("项目路径", sys.getUserDir()));
        vo.data.add(new KV("操作系统", sys.getOsName()));
        vo.data.add(new KV("系统架构", sys.getOsArch()));
        return vo;
    }
}
