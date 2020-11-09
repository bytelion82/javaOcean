package com.bytelion.detect.ArcFace.service.impl;


import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.toolkit.ImageInfo;
import com.bytelion.detect.ArcFace.factory.FaceEngineFactory;
import com.bytelion.detect.ArcFace.service.FaceEngineService;
import com.bytelion.detect.entity.FaceLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

/**
 * @author -> 太傅
 * @date -> 2020/11/7 : 17:39
 * #description    ->
 */
@Slf4j
@Service
public class FaceEngineServiceImpl implements FaceEngineService {
    @Value("${Arc.face.appId}")
    private String appId;

    @Value("${Arc.face.sdkKey}")
    private String sdkKey;
    @Value("${Arc.face.libPath}")
    private String libPath;

    @Value("${Arc.face.basePath}")
    private String basePath;



    private GenericObjectPool<FaceEngine> pool;


    @PostConstruct
    public void init(){

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        // 整个池最大值
        config.setMaxTotal(50);
        // 最大空闲
        config.setMaxIdle(8);
        // 最小空闲
        config.setMinIdle(0);
        // 最大等待时间，-1表示一直等
        config.setMaxWaitMillis(2000);

        // 当对象池没有空闲对象时，新的获取对象的请求是否阻塞。true阻塞。默认值是true
        config.setBlockWhenExhausted(true);
        // 在从对象池获取对象时是否检测对象有效，true是；默认值是false
        config.setTestOnBorrow(false);
        // 在向对象池中归还对象时是否检测对象有效，true是，默认值是false
        config.setTestOnReturn(false);
        // 在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性。true是，默认值是false
        config.setTestWhileIdle(false);
        // 可发呆的时间,10mins
        config.setMinEvictableIdleTimeMillis(10 * 60000L);
        // 发呆过长移除的时候是否test一下先
        config.setTestWhileIdle(false);
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

         pool= new GenericObjectPool(new FaceEngineFactory(appId,sdkKey,libPath,engineConfiguration), config);
    }




    @Override
    public ArrayList<FaceLog> detectFaces(List<FaceLog> logList) {
        FaceEngine faceEngine=null;
        int errorCode;

//        String picture="D:\\Users\\Faceimage\\155.jpg";
//        String photo="D:\\Users\\Faceimage\\155.jpg";

        ArrayList<FaceLog> result = new ArrayList<>();
        try {
            faceEngine= pool.borrowObject();
            log.info("===============*******===================借出对象========={}================***********==============",logList.size());

            for (FaceLog faceLog : logList) {
//                String picture="D:\\Users\\Faceimage\\cap"+faceLog.getUsername()+".jpg";
//                String photo="D:\\Users\\Faceimage\\base"+faceLog.getUsername()+".jpg";
                String picture=basePath+ "\\cap"+faceLog.getUsername()+".jpg";
                String photo=basePath+ "\\base"+faceLog.getUsername()+".jpg";
                download(faceLog,picture,photo);
                try {
                    //人脸检测
                    ImageInfo imageInfo = getRGBData(new File(picture));
                    List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
                    faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
                    //特征提取
                    FaceFeature faceFeature = new FaceFeature();
                    faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);

                    log.info("抓拍-----人脸检测：{}   ======   特征值大小：{}",faceInfoList,faceFeature.getFeatureData().length);

                    faceLog.setGrabFatureData(faceInfoList.toString());

                    // 人脸检测2
                    ImageInfo imageInfo2 = getRGBData(new File(photo));

                    List<FaceInfo> faceInfoList2 = new ArrayList<FaceInfo>();
                    faceEngine.detectFaces(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(),imageInfo2.getImageFormat(), faceInfoList2);

                    // 特征提取2
                    FaceFeature faceFeature2 = new FaceFeature();
                    faceEngine.extractFaceFeature(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(), imageInfo2.getImageFormat(), faceInfoList2.get(0), faceFeature2);

                    log.info("底片-----人脸检测：{}   ======   特征值大小：{}",faceInfoList2,faceFeature2.getFeatureData().length);
                    faceLog.setBaseFeatureData(faceInfoList2.toString());

                    //特征比对
                    FaceFeature targetFaceFeature = new FaceFeature();
                    targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
                    FaceFeature sourceFaceFeature = new FaceFeature();
                    sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
                    FaceSimilar faceSimilar = new FaceSimilar();
                    errorCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);

                    log.info("图片一 和  图片二的相似度：{}",faceSimilar.getScore());

                    log.debug("=========================开始活体检测=====================================");
                    faceLog.setScore(String.valueOf(faceSimilar.getScore()));


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

                    faceLog.setGender(String.valueOf(genderInfoList.get(0).getGender()));
                    //年龄检测
                    List<AgeInfo> ageInfoList = new ArrayList<AgeInfo>();
                    errorCode = faceEngine.getAge(ageInfoList);
                    log.info("图一  年龄：" + ageInfoList.get(0).getAge());

                    faceLog.setAge(String.valueOf(ageInfoList.get(0).getAge()));
                    //3D信息检测
                    List<Face3DAngle> face3DAngleList = new ArrayList<Face3DAngle>();
                    errorCode = faceEngine.getFace3DAngle(face3DAngleList);
                    log.info("图一  3D角度：" + face3DAngleList.get(0).getPitch() + "," + face3DAngleList.get(0).getRoll() + "," + face3DAngleList.get(0).getYaw());

                    faceLog.setAngle3D(face3DAngleList.get(0).getPitch() + "," + face3DAngleList.get(0).getRoll() + "," + face3DAngleList.get(0).getYaw());
                    //活体检测
                    List<LivenessInfo> livenessInfoList = new ArrayList<LivenessInfo>();
                    errorCode = faceEngine.getLiveness(livenessInfoList);
                    log.info("图一  活体：" + livenessInfoList.get(0).getLiveness());
//                    result.add(faceLog);
                } catch (Exception e) {
                    log.error("发生错误的人：{}，原因：{}",faceLog,e.getMessage());
                }finally {
                    result.add(faceLog);
                }

            }

        } catch (Exception e) {
            // e.printStackTrace();
            log.error("发生错误的原因：{}",e.getMessage());
        } finally {
            log.info("===============*******===================归还对象=============={}===========***********==============",result.size());
            pool.returnObject(faceEngine);
        }
        return result;
    }

    private FaceLog download(FaceLog faceLog,String picture,String photo){
        BufferedImage image = null;
        BufferedImage image2 = null;

        try {
            URL url = new URL(faceLog.getPicture());
            URL url2 = new URL(faceLog.getPhoto());
            image = ImageIO.read(url);

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
