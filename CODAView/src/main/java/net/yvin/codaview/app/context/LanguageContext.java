package net.yvin.codaview.app.context;

import java.util.Locale;

/**
 * Created by Юрий on 04.06.2014.
 */
public class LanguageContext {

    public static String getlanguage(){
        return Locale.getDefault().getLanguage();
    }
}
