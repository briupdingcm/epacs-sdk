package com.epacs.sdk.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 发动机部位
 * @create: 2020.04.13 18:24
 * @author: Kevin
 */
public enum Region {
    NOZZLE, //喷油嘴
    AIRINELET, //进气道
    CLINDER, //缸壁
    CROWN,   //活塞顶
    INLETVALVE;  //进气阀

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

    /**
     * Returns the name of this enum constant, as contained in the
     * declaration.  This method may be overridden, though it typically
     * isn't necessary or desirable.  An enum type should override this
     * method when a more "programmer-friendly" string form exists.
     *
     * @return the name of this enum constant
     */
    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}
