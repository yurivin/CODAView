package net.yvin.codaview.app.repository;

import android.content.Context;
import net.yvin.codaview.app.utils.AssetsTxtReader;
import net.yvin.codaview.app.utils.PropertiesFileReader;

import java.util.Properties;

/**
 * Created by Юрий on 27.04.2014.
 */
public class DailyContentRepository implements PropertiesRepository {
    @Override
    public String find(String key, Context context) {
        String[] result = key.split("-");
        AssetsTxtReader txtReader = new AssetsTxtReader(context);
        return txtReader.getAssetsTxt("Daily/" + result[1] + "/" + result[0] +".txt");
    }


}