package net.yvin.codaview.app.utils;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yuriy on 24.04.2014.
 */
public class DateUtils {

    /**
     * Get from date id for a daily.
     * Id for a daily is MMDD format.
     * @return
     */
    public static String nowToDailyId() {
        Date date = new Date();
        StringBuilder builder = new StringBuilder();
        String str = String.valueOf(date.getMonth() + 1);
        builder.append(str.length() == 2 ? str : "0" + str);
        str = String.valueOf(date.getDate());
        builder.append(str.length() == 2 ? str : "0" + str);
        Log.d("daily id: ", builder.toString());
        return builder.toString();
    }
}
