package com.bytelion.springbootlogback.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 太傅
 * @version V1.0
 * @package com.bytelion.springbootlogback.entity
 * @description
 * @date: Created in 2020/9/29 15:20
 * @copyright Copyright (c) 2020/9/29
 */

@Data
public class SysLog implements Serializable {
    private static final long serialVersionUID = 5836642108971137873L;

    private int id;
    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private LocalDateTime createTime;
}
