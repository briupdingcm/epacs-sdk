package com.epacs.sdk.common;

import java.util.HashMap;
import java.util.Map;

public enum Region {
    NOZZLE,
    AIRINELET,
    CLINDER,
    CROWN,
    INLETVALVE;

    // Implementing a fromString method on an enum type
    private static final Map<String, Region> stringToEnum = new HashMap<String, Region>();
    static {
        // Initialize map from constant name to enum constant
        for(Region blah : values()) {
            stringToEnum.put(blah.toString(), blah);
        }
    }

    // Returns Blah for string, or null if string is invalid
    public static Region fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

    public boolean equals(String position){
        return this.name().equalsIgnoreCase(position);
    }

}
