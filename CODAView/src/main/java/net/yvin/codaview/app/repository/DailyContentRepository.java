package net.yvin.codaview.app.repository;

import android.content.Context;
import net.yvin.codaview.app.utils.PropertiesFileReader;

import java.util.Properties;

/**
 * Created by Юрий on 27.04.2014.
 */
public class DailyContentRepository implements PropertiesRepository {
    @Override
    public String find(String key, Context context) {
        String[] result = key.split("-");
        PropertiesFileReader reader = new PropertiesFileReader(context);
        Properties properties = reader.getAssetsProperties(result[0]);
        return (String)properties.get(result[1]);
    }
}
