package net.yvin.codaview.app.utils;

import android.content.Context;

import java.io.*;

/**
 * Created by Yuriy on 30.04.2014.
 */
public class AssetsTxtReader {

    Context context;

    public AssetsTxtReader(Context context) {
        this.context = context;
    }

    public String getAssetsTxt(String path) {
        String txt = null;
        try {
            InputStream is = context.getResources().getAssets().open(path);
            txt = convertStreamToString(is);
        } catch (IOException ioe) {
        }
        return txt;
    }

    private static String convertStreamToString(InputStream is) throws IOException {
        Writer writer = new StringWriter();

        char[] buffer = new char[2048];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is,
                    "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }
        String text = writer.toString();
        return text;
    }
}
