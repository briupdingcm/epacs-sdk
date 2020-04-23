package com.epacs.sdk.common;

import java.util.HashMap;
import java.util.Map;

public enum TaskStatus {
    READY,
    RUNNING,
    SUCCESS,
    FAILURE;

    // Implementing a fromString method on an enum type
    private static final Map<String, TaskStatus> stringToEnum = new HashMap<String, TaskStatus>();
    static {
        // Initialize map from constant name to enum constant
        for(TaskStatus blah : values()) {
            stringToEnum.put(blah.toString(), blah);
        }
    }

    // Returns Blah for string, or null if string is invalid
    public static TaskStatus fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

    public boolean equals(String status){
        return this.name().equalsIgnoreCase(status);
    }

    public static void main(String... args){
        System.out.print("SUCCESS".equalsIgnoreCase(TaskStatus.SUCCESS.name()));
    }
}
