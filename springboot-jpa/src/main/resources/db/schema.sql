drop table if exists `byte_user`;
create table `byte_user` (
    `id` INT(11) NOT NULL  AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `name` VARCHAR(32) NOT NULL UNIQUE COMMENT '用户名',
    `password` VARCHAR(20) NOT NULL COMMENT '密码',
    `salt` VARCHAR(32) NOT NULL COMMENT '加密使用的盐',
    `email` VARCHAR(32) NOT NULL COMMENT '邮箱',
    `phone_number` VARCHAR(32) NOT NULL COMMENT '电话号码',
    `status` varchar(2) not null default 1 COMMENT '状态(-1 ：逻辑删除,0:禁用,1:启用)',
    `create_time` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_login_time` DATETIME DEFAULT NULL  COMMENT '上次登录时间',
    `last_update_time` DATETIME NOT NULL DEFAULT NOW() COMMENT '上次更新时间'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='springboot-jpa 用户表';


DROP TABLE IF EXISTS `byte_department`;
CREATE TABLE `byte_department` (
    `id` INT(11) NOT NULL  AUTO_INCREMENT PRIMARY KEY COMMENT'主键',
    `name` VARCHAR(32) NOT NULL COMMENT'部门名称',
    `superior` INT(11) COMMENT '上级id',
    `levels` INT(11) NOT NULL COMMENT'层级',
    `order_nom` INT(11) NOT NULL DEFAULT 0 COMMENT' 排序',
    `create_time` DATETIME DEFAULT NOW() COMMENT'创建时间',
    `last_update_time` DATETIME NOT NULL  DEFAULT NOW() COMMENT'上次更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Spring Boot Demo JPA 部门表';

DROP TABLE IF EXISTS `byte_user_dept`;
CREATE TABLE `byte_user_dept` (
    `id` INT(11) NOT NULL  AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `user_id` INT(11) NOT NULL COMMENT'用户id',
    `dept_id` INT(11)  NOT NULL COMMENT'部门id',
    `create_time` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `last_update_time` DATETIME NOT NULL DEFAULT NOW() COMMENT'上次更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='spring boot jpa 用户-部门关系表';