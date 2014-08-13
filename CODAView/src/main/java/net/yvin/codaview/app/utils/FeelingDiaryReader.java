package net.yvin.codaview.app.utils;

import android.os.Environment;
import android.util.Log;
import au.com.bytecode.opencsv.CSVReader;
import net.yvin.codaview.app.activity.SettingsActivity;
import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yuriy.Vinogradov on 13.08.2014.
 */
public class FeelingDiaryReader {

    public static Set<FeelingsDiaryEntry> readAll() {
        Set<FeelingsDiaryEntry> entryList = new HashSet<>();
        File folder = (Environment.getExternalStorageDirectory());
        String fileName = folder.toString() + "/" + "feelingsdiary.csv";
        try {
            CSVReader reader = new CSVReader(new FileReader(fileName), '\t');
            List<String[]> entries = reader.readAll();
            for(String[] entry : entries) {
                StringBuilder builder = new StringBuilder();
                for(String part : entry) {
                    builder.append(part);
                    builder.append(",");
                }
                FeelingsDiaryEntry feelingsDiaryEntryBean = new FeelingsDiaryEntry(entry[0], entry[1], entry[2], entry[3],
                        entry[4], entry[5], entry[6], entry[7], entry[8], entry[9], entry[10], entry[11], entry[12]);
                Log.d("CSV entry: ", builder.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entryList;
    }
}
