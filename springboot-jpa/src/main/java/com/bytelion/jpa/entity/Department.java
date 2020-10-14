package com.bytelion.jpa.entity;

import com.bytelion.jpa.entity.base.AbstractAuditModel;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author 太傅
 * @version V1.0
 * @package com.bytelion.jpa.entity
 * @description
 * @date: Created in 2020/10/8 20:56
 * @copyright Copyright (c) 2020/10/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "byte_department")
@ToString(callSuper = true)
public class Department extends AbstractAuditModel {

    /**
     * 部门名字
     */
    @Column(name = "name" ,columnDefinition = "varchar(255) not null")
    private String name;
    /**
     * 上级部门id
     */
    @ManyToOne(cascade = {CascadeType.REFRESH},optional = true)
    @JoinColumn(name = "superior", referencedColumnName = "id")
    private Department superior;

    /**
     * 所属层级
     */
    @Column(name = "levels" ,columnDefinition = "int not null default 0")
    private Integer levels;

    /**
     * 排序
     */
    @Column(name = "order_nom" ,columnDefinition = "int not null default 0")
    private  Integer orderNom;

    /**
     * 子部门集合
     */
    @ManyToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.EAGER,mappedBy = "superior")
    private Collection<Department> children;

    /**
     * 部门下用户集合
     */
    @ManyToMany(mappedBy = "departmentList")
    private Collection<User> userList;
}
