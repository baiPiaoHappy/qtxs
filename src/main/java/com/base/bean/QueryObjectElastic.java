package com.base.bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import org.elasticsearch.common.unit.TimeValue;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: 小猴子
 * @date: 2020-01-09 16:50
 */
@Data
public class QueryObjectElastic {

    private String index;

    private String type;

    private String id;

    private String jsonString;

    private JSON jsonKeys;

    private String key;

    private Long count;

    private int from = 0;

    private int size = 10;

    private TimeValue timeOut = new  TimeValue(60,TimeUnit.SECONDS);

    private Map<String,Object> mapKeys;

}
