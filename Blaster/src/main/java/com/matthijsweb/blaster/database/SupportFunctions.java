package com.matthijsweb.blaster.database;

/**
 * Created by Matthijs on 8-12-13.
 */
public class SupportFunctions {

    /**
     * Get current timestamp
     * @return
     */
    public static String getCurrentTimestamp() {
        Long tsLong = System.currentTimeMillis()/1000;

        return tsLong.toString();
    }

}
