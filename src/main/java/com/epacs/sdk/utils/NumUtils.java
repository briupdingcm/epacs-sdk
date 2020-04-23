package com.epacs.sdk.utils;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * @program: jieyi_product_1
 * @package: jieyi.product.api.utils
 * @filename: NumUtils.java
 * @create: 2020.04.11 17:27
 * @author: Kevin
 * @description: .
 **/
public class NumUtils{
    public static Integer newRandomInt(){
        return Math.abs(Objects.hash(UUID.randomUUID().toString(),new Random().nextInt(1000000000)));
    }
}
