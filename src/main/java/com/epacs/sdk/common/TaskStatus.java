package com.epacs.sdk.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: epacs-sdk
 * @package: com.epacs.sdk.common
 * @filename: TaskStatus.java
 * @create: 2020.04.13 18:24
 * @author: Kevin
 * @description: . 任务状态
 **/
public enum TaskStatus {
    READY,
    RUNNING,
    SUCCESS,
    FAILURE;

    // Implementing a fromString method on an enum type
    private static final Map<String, TaskStatus> stringToEnum = new HashMap<String, TaskStatus>();
    static {
        // Initialize map from constant name to enum constant
        for(TaskStatus status : values()) {
            stringToEnum.put(status.toString(), status);
        }
    }

    // Returns TaskStatus for string, or null if string is invalid
    public static TaskStatus fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

    public boolean equals(String status){
        return this.name().equalsIgnoreCase(status);
    }

}
