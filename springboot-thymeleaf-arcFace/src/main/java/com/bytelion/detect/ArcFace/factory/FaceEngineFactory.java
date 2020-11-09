package com.bytelion.detect.ArcFace.factory;

import com.arcsoft.face.ActiveFileInfo;
import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.enums.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;


/**
 * @author -> 太傅
 * @date -> 2020/11/7 : 17:57
 * #description    ->
 */
@Slf4j

public class FaceEngineFactory extends BasePooledObjectFactory<FaceEngine> {


    private String appId;
    private String sdkKey;
    private String libPath;
    private EngineConfiguration engineConfiguration;




    public FaceEngineFactory(String appId, String sdkKey, String libPath, EngineConfiguration engineConfiguration) {
        this.appId = appId;
        this.sdkKey = sdkKey;
        this.libPath = libPath;
        this.engineConfiguration = engineConfiguration;
    }

    /**
     * 创建对象
     * @return
     * @throws Exception
     */
    @Override
    public FaceEngine create() throws Exception {
        FaceEngine faceEngine = new FaceEngine(libPath);

        int errorCode = faceEngine.activeOnline(appId, sdkKey);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }
        ActiveFileInfo activeFileInfo=new ActiveFileInfo();
        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("获取激活文件信息失败");
        }
        //初始化引擎
        errorCode = faceEngine.init(engineConfiguration);

        if (errorCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }
        log.info("=========**===========人脸检测激活成功========**=========");
        return faceEngine;
    }

    /**
     *包装对象
     * @param faceEngine
     * @return
     */
    @Override
    public PooledObject<FaceEngine> wrap(FaceEngine faceEngine) {
        return new DefaultPooledObject<>(faceEngine);
    }

    /**
     *  在获取对象返回之前可以进行的操作
     * @param p
     * @throws Exception
     */
    @Override
    public void activateObject(PooledObject<FaceEngine> p) throws Exception {
        log.debug("返回对象前查看appid:{}",appId);
        super.activateObject(p);
    }
    /**
     *  在归还对象之前可以进行的操作
     * @param p
     * @throws Exception
     */
    @Override
    public void passivateObject(PooledObject<FaceEngine> p) throws Exception {
        super.passivateObject(p);
    }
}
