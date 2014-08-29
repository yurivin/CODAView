package net.yvin.codaview.app.utils;

import android.os.Environment;
import android.util.Log;
import au.com.bytecode.opencsv.CSVReader;
import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy.Vinogradov on 13.08.2014.
 */
public class FeelingDiaryReader {

    public static List<FeelingsDiaryEntry> readAll() {
        List<FeelingsDiaryEntry> entryList = new ArrayList<>();
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
//                Log.d("CSV entry: ", builder.toString());
                FeelingsDiaryEntry feelingsDiaryEntryBean = new FeelingsDiaryEntry(entry[0], entry[1], entry[2], entry[3],
                        entry[4], entry[5], entry[6], entry[7], entry[8], entry[9], entry[10], entry[11], entry[12], entry[13]);
                entryList.add(feelingsDiaryEntryBean);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entryList;
    }
}
