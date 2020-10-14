package com.bytelion.cache.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(PlateformUser)实体类
 *
 * @author makejava
 * @since 2020-10-14 17:07:32
 */
public class PlateformUser implements Serializable {
    private static final long serialVersionUID = -41334142860706675L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 名字
     */
    private String userName;
    /**
     * 是否已被删除
     */
    private Integer isDeleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

}