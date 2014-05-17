package net.yvin.codaview.app.repository;

import android.content.Context;
import net.yvin.codaview.app.utils.AssetsTxtReader;

/**
 * Created by Юрий on 17.05.2014.
 */
public class TwelveRepository implements PropertiesRepository {
    /**
     * result[0] type - steps, traditions, promises
     * result[1] number of entry in type and subpart
     * @param key
     * @param context
     * @return
     */
    @Override
    public String find(String key, Context context) {
        String[] result = key.split("-");
        AssetsTxtReader txtReader = new AssetsTxtReader(context);
        return txtReader.getAssetsTxt(result[0] + "/" + result[1] +".txt");
    }
}
