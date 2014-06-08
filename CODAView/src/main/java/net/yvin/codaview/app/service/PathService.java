package net.yvin.codaview.app.service;

import android.util.Log;
import net.yvin.codaview.app.context.LanguageContext;

/**
 * Created by Юрий on 08.06.2014.
 */
public class PathService {

    private static String DIARYTITLES = "diarytitles";
    private static String MINUS = "-";
    private static String POINT_PROPERTIES = ".properties";


   public static String getDiaryTitlePath() {
       String path = DIARYTITLES + MINUS + LanguageContext.getlanguage().toLowerCase() + POINT_PROPERTIES;
       Log.d("Path to daily title",path);
       return path;
   }
}
