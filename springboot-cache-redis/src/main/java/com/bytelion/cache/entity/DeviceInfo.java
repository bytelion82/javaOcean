package com.bytelion.cache.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (DeviceInfo)实体类
 *
 * @author makejava
 * @since 2020-10-21 08:46:59
 */


public class DeviceInfo implements Serializable {
    private static final long serialVersionUID = 636226412959767432L;

    private Integer id;

    private String name;

    private Date updateTime;

    private String comment;

    private String area;

    private boolean isDeleted;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}