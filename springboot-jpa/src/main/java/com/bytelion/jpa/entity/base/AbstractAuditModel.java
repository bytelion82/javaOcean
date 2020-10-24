package com.bytelion.jpa.entity.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 太傅
 * @version V1.0
 * @package com.bytelion.jpa.entity.base
 * @description
 * @date: Created in 2020/10/8 21:14
 * @copyright Copyright (c) 2020/10/8
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class AbstractAuditModel implements Serializable {
    private static final long serialVersionUID = 6310106244430554775L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, updatable = false)
    @CreatedDate
    private Date createTime;

    /**
     * 上次更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false)
    @LastModifiedDate
    private Date lastUpdateTime;
}
