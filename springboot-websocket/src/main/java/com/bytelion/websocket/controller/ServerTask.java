package com.bytelion.websocket.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import com.bytelion.websocket.common.WebSocketConsts;
import com.bytelion.websocket.entity.Server;
import com.bytelion.websocket.playload.ServerVO;
import com.bytelion.websocket.utils.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : 太傅
 * 2020/9/28
 * @Func        定时任务
 */
@Slf4j
@Component
public class ServerTask {

    @Autowired
    private SimpMessagingTemplate wsTemplate;

    /**
     * 按照标准时间来算，每隔 2s 执行一次
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void websocket() throws Exception {
        log.info("【推送消息】开始执行：{}", DateUtil.formatDateTime(new Date()));
        // 查询服务器状态
        Server server = new Server();
        server.copyTo();
        ServerVO serverVO = ServerUtil.wrapServerVO(server);
        Dict dict = ServerUtil.wrapServerDict(serverVO);
        System.out.println(dict.toString());
        System.out.println(WebSocketConsts.PUSH_SERVER);
        String s = JSONUtil.toJsonStr(dict);
        wsTemplate.convertAndSend(WebSocketConsts.PUSH_SERVER,s);
        log.info("【推送消息】执行结束：{}", DateUtil.formatDateTime(new Date()));
    }
}
