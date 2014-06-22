package net.yvin.codaview.app.context;

import android.util.Log;

import java.util.Locale;

/**
 * Created by Юрий on 04.06.2014.
 */
public class LanguageContext {

    public static String getLanguage(){
        String language = Locale.getDefault().getLanguage();
        Log.d("language ",  language);
        if(!"ru".equals(language)) {
            return "en";
        }
        return language;
    }
}
