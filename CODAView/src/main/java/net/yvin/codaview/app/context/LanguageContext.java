package net.yvin.codaview.app.context;

import android.util.Log;

import java.util.*;

/**
 * Created by Юрий on 04.06.2014.
 */
public class LanguageContext {

    private static final String[] supportedLanguages = {"ru", "en"};

    public static String getLanguage(){
        String language = Locale.getDefault().getLanguage();
        Log.d("language ",  language);
        if(!"en".equals(language)) {
            return "ru";
        }
        return language;
    }

    public static boolean isSupportedlanguage(String language) {
        Set<String> langList = new HashSet<String>(Arrays.asList(supportedLanguages));
        return langList.contains(language);
    }

    public static String getDefaultLanguage() {
        return Locale.getDefault().getLanguage();
    }
}
