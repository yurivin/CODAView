package net.yvin.codaview.app.service;

import android.util.Log;
import net.yvin.codaview.app.context.LanguageContext;
import net.yvin.codaview.app.utils.Constants;

/**
 * Created by Юрий on 08.06.2014.
 */
public class PathService {

    private static String DIARYTITLES = "diarytitles";
    private static String MINUS = "-";
    private static String POINT_PROPERTIES = ".properties";
    public static final String SLASH = "/";
    public static final String POINTTXT = ".txt";


   public static String diaryTitle() {
       String path = DIARYTITLES + MINUS + LanguageContext.getlanguage() + POINT_PROPERTIES;
       Log.d("Path to daily title",path);
       return path;
   }

   public static String stepMain(int stepNumber) {
       return LanguageContext.getlanguage() + SLASH + Constants.STEPS
               + SLASH + stepNumber + Constants.MAIN + POINTTXT;
   }

    public static String stepExtention(int stepNumber) {
        return LanguageContext.getlanguage() + SLASH + Constants.STEPS
                + SLASH + stepNumber + Constants.EXTENSION + POINTTXT;
    }

    public static String dailyQuote(String dailyId) {
        return LanguageContext.getlanguage() + SLASH + Constants.DAILY
                + SLASH + Constants.QUOTE + SLASH + dailyId + POINTTXT;
    }

    public static String dailyMain(String dailyId) {
        return LanguageContext.getlanguage() + SLASH + Constants.DAILY
                + SLASH + Constants.MAIN + SLASH + dailyId + POINTTXT;
    }

    public static String dailySum(String dailyId) {
        return LanguageContext.getlanguage().toLowerCase() + SLASH
                + Constants.DAILY + SLASH + Constants.SUM + SLASH + dailyId + POINTTXT;
    }

    public static String text(String field, String text) {
        return LanguageContext.getlanguage() + SLASH + field + SLASH + text + POINTTXT;
    }
}
