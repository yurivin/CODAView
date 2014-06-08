package net.yvin.codaview.app.repository;

import android.content.Context;
import android.util.Log;
import net.yvin.codaview.app.utils.AssetsTxtReader;

/**
 * Created by Юрий on 17.05.2014.
 */
public class TextRepository {
    /**
     * @param key
     * @param context
     * @return
     */
    public String find(String key, Context context) {
        AssetsTxtReader txtReader = new AssetsTxtReader(context);
        return txtReader.getAssetsTxt(key + ".txt");
    }
}
