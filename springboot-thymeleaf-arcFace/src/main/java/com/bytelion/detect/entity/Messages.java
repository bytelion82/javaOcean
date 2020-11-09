package com.bytelion.detect.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@Accessors(chain = true)
public class Messages {

    private String name;
    private Integer money;
    private List<Object> goods;
}
