package com.bytelion.detect.controller;

import com.bytelion.detect.ArcFace.service.FaceEngineService;
import com.bytelion.detect.ArcFace.utils.ArcFaceUtil;
import com.bytelion.detect.entity.FaceLog;
import com.bytelion.detect.service.FaceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 识别记录表(FaceLog)表控制层
 *
 * @author makejava
 * @since 2020-11-06 13:33:19
 */
@Controller
@RequestMapping("faceLog")
public class FaceLogController {
    /**
     * 服务对象
     */
    @Resource
    private FaceLogService faceLogService;

    @Autowired
    private FaceEngineService faceEngineService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public FaceLog selectOne(Integer id) {
        return this.faceLogService.queryById(id);
    }


    @GetMapping("log")
    public List<FaceLog> getAll(){

        return faceLogService.queryFaceLogByDay("000000", LocalDateTime.now().minusDays(2).minusHours(12),LocalDateTime.now().minusDays(1));
    }

    @GetMapping("detect")
    public List<FaceLog> faceDetect(){

        List<FaceLog> logList = faceLogService.queryFaceLogByDay("000000", LocalDateTime.now().plusDays(1),LocalDateTime.now().plusDays(2));
        ArrayList<FaceLog> list = new ArrayList<>(logList.size());
        ArrayList<FaceLog> empty = new ArrayList<>(logList.size());

        int i=0;
        for (FaceLog faceLog : logList) {

            if (!faceLog.getUsername().equals("吴小芳")){
                continue;
            }
            i++;
            if (faceLog.getUsername()!=null&&faceLog.getPhoto()!=null&& faceLog.getPicture()!=null) {
                System.out.println(faceLog);
                FaceLog faceLog1 = ArcFaceUtil.extractFaceFeature(faceLog);
                list.add(faceLog1);
            }else {
                empty.add(faceLog);
            }

            if (i>1){
                break;
            }
        }
        System.out.println(empty);


        return list;
    }

    @GetMapping("/arc")
    public String all(Model model,String tenantId,Integer start,Integer end){
        List<FaceLog> logList = faceLogService.queryFaceLogByDay(tenantId, LocalDateTime.now().minusDays(start).minusHours(9),LocalDateTime.now().minusDays(end));
        ArrayList<FaceLog> faceLogs = faceEngineService.detectFaces(logList);

        model.addAttribute("faceLogList",faceLogs);
        return "message/list";
    }


}