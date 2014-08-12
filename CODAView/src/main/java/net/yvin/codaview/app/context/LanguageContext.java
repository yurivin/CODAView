package net.yvin.codaview.app.context;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.*;

/**
 * Created by Юрий on 04.06.2014.
 */
public class LanguageContext {

    private static final String[] supportedLanguages = {"ru", "en"};

    public static String getLanguage() {
        String language = Locale.getDefault().getLanguage();
        Log.d("language ", language);
        if (!isSupportedlanguage(language)) {
            return "en";
        }
        return language;
    }

    public static boolean isSupportedlanguage(String language) {
        for(int i=0; i < supportedLanguages.length; i++) {
            if(language.equals(supportedLanguages[i])) return true;
        }
        return false;
    }
}
