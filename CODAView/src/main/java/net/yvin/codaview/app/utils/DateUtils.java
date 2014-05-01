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
        return CalendarToDailyId(date.getMonth(), date.getDate());
    }

    public static String CalendarToDailyId(int month, int dayOfMonth) {
        StringBuilder dailyId = new StringBuilder();
        String str = String.valueOf(month + 1);
        dailyId.append(str.length() == 2 ? str : "0" + str);
        str = String.valueOf(dayOfMonth);
        dailyId.append(str.length() == 2 ? str : "0" + str);
        return dailyId.toString();
    }
}
