package com.bytelion.websocket.playload;

import com.bytelion.websocket.entity.Server;
import com.bytelion.websocket.entity.vo.*;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author : 太傅
 * 2020/9/28
 * @Func
 */
@Data
public class ServerVO {
    List<CpuVO> cpu = Lists.newArrayList();
    List<JvmVO> jvm = Lists.newArrayList();
    List<MemVO> mem = Lists.newArrayList();
    List<SysFileVO> sysFile = Lists.newArrayList();
    List<SysVO> sys = Lists.newArrayList();

    public ServerVO create(Server server) {
        cpu.add(CpuVO.create(server.getCpu()));
        jvm.add(JvmVO.create(server.getJvm()));
        mem.add(MemVO.create(server.getMem()));
        sysFile.add(SysFileVO.create(server.getSysFiles()));
        sys.add(SysVO.create(server.getSys()));
        return null;
    }
}
