package repository;

import Utils.PropertiesFileReader;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.IOException;
import java.io.InputStream;
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
