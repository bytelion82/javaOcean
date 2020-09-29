package com.bytelion.websocket.entity.vo;

import com.bytelion.websocket.entity.Jvm;
import com.bytelion.websocket.playload.KV;
import com.google.common.collect.Lists;
import lombok.Data;

import javax.lang.model.element.VariableElement;
import java.util.List;

/**
 * @author : 太傅
 * 2020/9/28
 * @Func
 */
@Data
public class JvmVO {
    List<KV> data=Lists.newArrayList();

    public static JvmVO create(Jvm jvm){
        JvmVO vo = new JvmVO();
        vo.data.add(new KV("当前jvm占用的内存总数(M)",jvm.getTotal()+"M"));
        vo.data.add(new KV("JVM最大可用内存总数(M)", jvm.getMax() + "M"));
        vo.data.add(new KV("JVM空闲内存(M)", jvm.getFree() + "M"));
        vo.data.add(new KV("JVM使用率", jvm.getUsage() + "%"));
        vo.data.add(new KV("JDK版本", jvm.getVersion()));
        vo.data.add(new KV("JDK路径", jvm.getHome()));
        vo.data.add(new KV("JDK启动时间", jvm.getStartTime()));
        vo.data.add(new KV("JDK运行时间", jvm.getRunTime()));
        return vo;
    }
}
