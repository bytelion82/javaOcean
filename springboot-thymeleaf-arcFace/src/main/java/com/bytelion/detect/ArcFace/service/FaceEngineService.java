package com.bytelion.detect.ArcFace.service;

import com.bytelion.detect.entity.FaceLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author -> 太傅
 * @date -> 2020/11/7 : 17:38
 * #description    ->
 */

public interface FaceEngineService {


    ArrayList<FaceLog> detectFaces(List<FaceLog> logList);



}
