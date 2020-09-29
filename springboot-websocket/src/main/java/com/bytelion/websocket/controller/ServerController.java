package com.bytelion.websocket.controller;

import cn.hutool.core.lang.Dict;
import com.bytelion.websocket.entity.Server;
import com.bytelion.websocket.playload.ServerVO;
import com.bytelion.websocket.utils.ServerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 太傅
 * 2020/9/28
 * @Func
 */
@RestController
public class ServerController {

    @RequestMapping("/server")
    public Dict serverInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        ServerVO serverVO = ServerUtil.wrapServerVO(server);
        return ServerUtil.wrapServerDict(serverVO);
    }

}
