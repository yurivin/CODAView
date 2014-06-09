package net.yvin.codaview.app.context;

import java.util.Locale;

/**
 * Created by Юрий on 04.06.2014.
 */
public class LanguageContext {

    public static String getLanguage(){
        String language = Locale.getDefault().getLanguage();
        if(!"ru".equals(language) || !"en".equals(language)) {
            return "en";
        }
        return language;
    }
}
