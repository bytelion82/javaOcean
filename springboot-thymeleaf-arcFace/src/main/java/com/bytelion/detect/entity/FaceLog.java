package com.bytelion.detect.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * 识别记录表(FaceLog)实体类
 *
 * @author makejava
 * @since 2020-11-06 13:33:19
 */
@ToString
@Data
public class FaceLog implements Serializable {
    private static final long serialVersionUID = -65959688903347379L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 设备ID
     */
    private Integer deviceId;
    /**
     * 识别图像
     */
    private String picture;
    /**
     * 人员ID
     */
    private Integer peopleId;
    /**
     * 记录时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") //格式化返回的时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //格式化传入的时间
    private Date logTime;
    /**
     * 创建时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 租户ID
     */
    private String tenantId;

    private String photo;


    private String capPicture;

    private String basePhoto;

    private HashMap<String,Object> result;
    private String grabFatureData;
    private String baseFeatureData;
    private String Similarity;

    private String score;
    private String gender;

    private String angle3D;
    private String age;
    private String liveness;


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getId2() {
        return id2;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String number;
    private Integer id2;
    private String username;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

}