package net.yvin.codaview.app.utils;

import android.content.Context;
import android.util.Log;

import java.io.*;

/**
 * Created by Yuriy on 30.04.2014.
 */
public class AssetsReader {

    Context context;

    public AssetsReader(Context context) {
        this.context = context;
    }

    public String getAssets(String path) {
        String txt = null;
        try {
            Log.d("assets path: ", path);
            InputStream is = context.getResources().getAssets().open(path);
            txt = convertStreamToString(is);
        } catch (IOException ioe) {
            Log.d("Input Exception: ", ioe.toString());
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
