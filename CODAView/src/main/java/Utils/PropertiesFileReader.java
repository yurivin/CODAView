package Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Юрий on 20.04.2014.
 */
public class PropertiesFileReader {
    Context context;
    public PropertiesFileReader(Context context) {
        this.context = context;
    }

    public Properties getAssetsProperties(String path) {
        Resources resources = context.getResources();
        AssetManager assetManager = resources.getAssets();
        Properties properties = new Properties();
        try {
            InputStream inputStream = assetManager.open(path);
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.println("Failed to open assets property file");
            e.printStackTrace();
        }
        return properties;
    }
}
