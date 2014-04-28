package net.yvin.codaview.app.repository;

import net.yvin.codaview.app.utils.PropertiesFileReader;
import android.content.Context;

import java.util.Properties;

/**
 * Created by Юрий on 20.04.2014.
 */
public class DiaryTitlesRepository implements PropertiesRepository{

    public String find(String key, Context context) {
        PropertiesFileReader reader = new PropertiesFileReader(context);
        Properties properties = reader.getAssetsProperties("diarytitles");
        return (String)properties.get(key);
    }
}
