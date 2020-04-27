package com.epacs.sdk.model;

import com.epacs.sdk.utils.StringUtils;
import java.io.Serializable;

/**
 * @program: jieyi_product_1
 * @package: jieyi.product.api.model
 * @filename: Result.java
 * @create: 2020.04.11 16:50
 * @author: Kevin
 * @description: .实体
 **/
public class Result implements Serializable{
    // 分类名称
    private String name;
    // 置信度
    private Integer score;

    public Result(){
    }

    public Result(String name,Integer score){
        this.name=name;
        this.score=score;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public Integer getScore(){
        return score;
    }

    public void setScore(Integer score){
        this.score=score;
    }

    @Override
    public String toString(){
        return StringUtils.getInstance().toString(this);
    }
}
