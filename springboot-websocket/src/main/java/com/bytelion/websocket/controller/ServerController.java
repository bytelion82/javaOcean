package com.bytelion.websocket.controller;

import cn.hutool.core.lang.Dict;
import com.bytelion.websocket.entity.Server;
import com.bytelion.websocket.playload.ServerVO;
import com.bytelion.websocket.utils.ServerUtil;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/hh")
    public String hh(@RequestParam("id") String id, String value){
        System.out.println(id+value);
        return value;
    }

    @CrossOrigin
    @GetMapping("/hhh")
    public String hlh(){
        return "跨域";
    }

}
