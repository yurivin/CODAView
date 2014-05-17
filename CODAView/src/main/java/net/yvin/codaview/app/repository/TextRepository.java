package net.yvin.codaview.app.repository;

import android.content.Context;
import net.yvin.codaview.app.utils.AssetsTxtReader;

/**
 * Created by Юрий on 17.05.2014.
 */
public class TextRepository implements PropertiesRepository {
    /**
     * @param key
     * @param context
     * @return
     */
    @Override
    public String find(String key, Context context) {
        AssetsTxtReader txtReader = new AssetsTxtReader(context);
        return txtReader.getAssetsTxt(key + ".txt");
    }
}
