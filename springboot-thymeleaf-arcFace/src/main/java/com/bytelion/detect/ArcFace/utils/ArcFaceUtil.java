package com.bytelion.detect.ArcFace.utils;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageInfo;
import com.bytelion.detect.entity.FaceLog;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

/**
 * @author -> 太傅
 * @date -> 2020/11/6 : 13:58
 * #description    ->
 */
@Slf4j
public class ArcFaceUtil {
//    private static FaceEngine faceEngine=null;
//    private static int errorCode=0;
//
//    static {
//        //从官网获取
////        String appId = "3Xwq9LPra5Fpozv8wbAgC2tr3MhG7PdruaEaxsEARSvh";
////        String sdkKey = "Heuqs2H1XSFwJ5e1g98HhheWm85ghJdQonL4SCeTqsSB";
//        String appId = "HsP7ZNwUWUyiW5s6QAAoCXnoysHNz9YBRGUEHJqKGqgC";
//        String sdkKey = "8W1mzLwyHvbALUXPaKFBFAJMKnDCqSrCbwGPQTQMuctb";
//        FaceEngine faceEngine = new FaceEngine("F:\\ArcSoft_ArcFace_Java_Windows_x64_V3.0\\libs\\WIN64");
////        FaceEngine faceEngine = new FaceEngine("d:/arcsoft_lib");
//        //激活引擎
//        int errorCode = faceEngine.activeOnline(appId, sdkKey);
//
//        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
//            System.out.println("引擎激活失败");
//        }
//
//
//        ActiveFileInfo activeFileInfo=new ActiveFileInfo();
//        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
//        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
//            System.out.println("获取激活文件信息失败");
//        }
//
//        //引擎配置
//        EngineConfiguration engineConfiguration = new EngineConfiguration();
//        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
//        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
//        engineConfiguration.setDetectFaceMaxNum(10);
//        engineConfiguration.setDetectFaceScaleVal(16);
//        //功能配置
//        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
//        functionConfiguration.setSupportAge(true);
//        functionConfiguration.setSupportFace3dAngle(true);
//        functionConfiguration.setSupportFaceDetect(true);
//        functionConfiguration.setSupportFaceRecognition(true);
//        functionConfiguration.setSupportGender(true);
//        functionConfiguration.setSupportLiveness(true);
//        functionConfiguration.setSupportIRLiveness(true);
//        engineConfiguration.setFunctionConfiguration(functionConfiguration);
//
//
//        //初始化引擎
//        errorCode = faceEngine.init(engineConfiguration);
//
//        if (errorCode != ErrorInfo.MOK.getValue()) {
//            System.out.println("初始化引擎失败");
//        }
//    }

    public static FaceLog extractFaceFeature(FaceLog faceLog){

        FaceLog download = download(faceLog);
        faceLog=download;
        //从官网获取
//        String appId = "3Xwq9LPra5Fpozv8wbAgC2tr3MhG7PdruaEaxsEARSvh";
//        String sdkKey = "Heuqs2H1XSFwJ5e1g98HhheWm85ghJdQonL4SCeTqsSB";
        String appId = "HsP7ZNwUWUyiW5s6QAAoCXnoysHNz9YBRGUEHJqKGqgC";
        String sdkKey = "8W1mzLwyHvbALUXPaKFBFAJMKnDCqSrCbwGPQTQMuctb";
        FaceEngine faceEngine = new FaceEngine("F:\\ArcSoft_ArcFace_Java_Windows_x64_V3.0\\libs\\WIN64");
//        FaceEngine faceEngine = new FaceEngine("d:/arcsoft_lib");
        //激活引擎
        int errorCode = faceEngine.activeOnline(appId, sdkKey);

        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }


        ActiveFileInfo activeFileInfo=new ActiveFileInfo();
        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("获取激活文件信息失败");
        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
        engineConfiguration.setDetectFaceMaxNum(10);
        engineConfiguration.setDetectFaceScaleVal(16);
        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);


        //初始化引擎
        errorCode = faceEngine.init(engineConfiguration);

        if (errorCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }
//        String picture="D:\\Users\\Faceimage\\cap"+faceLog.getUsername()+".jpg";
//        String photo="D:\\Users\\Faceimage\\base"+faceLog.getUsername()+".jpg";
        String picture="D:\\Users\\Faceimage\\155.jpg";
        String photo="D:\\Users\\Faceimage\\155.jpg";
//        String picture="./faceImgs/"+faceLog.getUsername()+".jpg";
//        String photo="./faceImgs/"+faceLog.getUsername()+".jpg";

        HashMap<String, Object> result = new HashMap<>();
        //人脸检测

        ImageInfo imageInfo = getRGBData(new File(picture));
//        log.info("picture:{}------------- photo:{}",picture,photo);
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);

        //特征提取
        FaceFeature faceFeature = new FaceFeature();
        errorCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);

        log.info("抓拍    人脸检测：{}   ======   特征值大小：{}",faceInfoList,faceFeature.getFeatureData().length);
        result.put("抓拍的人脸检测",faceInfoList);
        result.put("抓拍的人脸特征值大小",faceFeature.getFeatureData().length);
        // 人脸检测2
        ImageInfo imageInfo2 = getRGBData(new File(photo));
        List<FaceInfo> faceInfoList2 = new ArrayList<FaceInfo>();
        errorCode = faceEngine.detectFaces(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(),imageInfo2.getImageFormat(), faceInfoList2);

        // 特征提取2
        FaceFeature faceFeature2 = new FaceFeature();
        errorCode = faceEngine.extractFaceFeature(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(), imageInfo2.getImageFormat(), faceInfoList2.get(0), faceFeature2);
        result.put("底库的人脸检测",faceInfoList2);
        result.put("底库的人脸特征值大小",faceFeature2.getFeatureData().length);
        log.info("底片     人脸检测：{}   ======   特征值大小：{}",faceInfoList2,faceFeature2.getFeatureData().length);

        //特征比对
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
        FaceSimilar faceSimilar = new FaceSimilar();
        errorCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);

        log.info("图片一 和  图片二的相似度：{}",faceSimilar.getScore());
        result.put("相似度",faceSimilar.getScore());
        log.debug("=========================开始活体检测=====================================");


        //设置活体测试  设置RGB/IR活体阈值，
        errorCode = faceEngine.setLivenessParam(0.5f, 0.7f);
        //人脸属性检测
        FunctionConfiguration configuration = new FunctionConfiguration();
        configuration.setSupportAge(true);
        configuration.setSupportFace3dAngle(true);
        configuration.setSupportGender(true);
        configuration.setSupportLiveness(true);
        errorCode = faceEngine.process(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList, configuration);


        //性别检测
        List<GenderInfo> genderInfoList = new ArrayList<GenderInfo>();
        errorCode = faceEngine.getGender(genderInfoList);
        log.info("图一  性别：" + genderInfoList.get(0).getGender());
        result.put("活体检测的性别",genderInfoList.get(0).getGender());
        //年龄检测
        List<AgeInfo> ageInfoList = new ArrayList<AgeInfo>();
        errorCode = faceEngine.getAge(ageInfoList);
        log.info("图一  年龄：" + ageInfoList.get(0).getAge());
        result.put("活体检测的年龄",ageInfoList.get(0).getAge());
        //3D信息检测
        List<Face3DAngle> face3DAngleList = new ArrayList<Face3DAngle>();
        errorCode = faceEngine.getFace3DAngle(face3DAngleList);
        log.info("图一  3D角度：" + face3DAngleList.get(0).getPitch() + "," + face3DAngleList.get(0).getRoll() + "," + face3DAngleList.get(0).getYaw());
        result.put("活体检测的3D角度",face3DAngleList.get(0).getPitch() + "," + face3DAngleList.get(0).getRoll() + "," + face3DAngleList.get(0).getYaw());

        //活体检测
        List<LivenessInfo> livenessInfoList = new ArrayList<LivenessInfo>();
        errorCode = faceEngine.getLiveness(livenessInfoList);
        log.info("图一  活体：" + livenessInfoList.get(0).getLiveness());
        result.put("活体", livenessInfoList.get(0).getLiveness());
        faceLog.setResult(result);
        return faceLog;
    }
    public static FaceLog download(FaceLog faceLog){

        BufferedImage image = null;
        BufferedImage image2 = null;
        String picture=null;
        String photo=null;
        try {
            URL url = new URL(faceLog.getPicture());
            URL url2 = new URL(faceLog.getPhoto());
            image = ImageIO.read(url);
            picture="D:\\Users\\Faceimage\\cap"+faceLog.getUsername()+".jpg";
            photo="D:\\Users\\Faceimage\\base"+faceLog.getUsername()+".jpg";
            ImageIO.write(image, "jpg", new File(picture));
            image2 = ImageIO.read(url2);
            ImageIO.write(image2, "jpg", new File(photo));
            faceLog.setCapPicture(picture);
            faceLog.setBasePhoto(photo);

        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("picture：{}下载完成--photo {}",picture,photo);
        return faceLog;

    }

}
