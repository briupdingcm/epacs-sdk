package com.epacs.sdk.common;

import java.util.HashMap;
import java.util.Map;

public enum Position {
    NOZZLE,
    AIRINELET,
    CLINDER,
    CROWN,
    INLETVALVE;

    // Implementing a fromString method on an enum type
    private static final Map<String, Position> stringToEnum = new HashMap<String, Position>();
    static {
        // Initialize map from constant name to enum constant
        for(Position blah : values()) {
            stringToEnum.put(blah.toString(), blah);
        }
    }

    // Returns Blah for string, or null if string is invalid
    public static Position fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

    public boolean equals(String position){
        return this.name().equalsIgnoreCase(position);
    }

}
