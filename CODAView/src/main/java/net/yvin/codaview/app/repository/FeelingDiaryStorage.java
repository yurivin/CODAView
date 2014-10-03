package net.yvin.codaview.app.repository;

import android.os.Environment;
import android.util.Log;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy.Vinogradov on 13.08.2014.
 */
public class FeelingDiaryStorage {

    public static List<FeelingsDiaryEntry> readAll() {
        List<FeelingsDiaryEntry> entryList = new ArrayList<>();
        File folder = (Environment.getExternalStorageDirectory());
        String fileName = folder.toString() + "/" + "feelingsdiary.csv";
        try {
            CSVReader reader = new CSVReader(new FileReader(fileName), '\t');
            List<String[]> entries = reader.readAll();
            for(String[] entry : entries) {
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

    public static void feelingsDiary(String yearFrom, String monthFrom, String dayFrom, String hourFrom, String minuteFrom,
                              String yearTo, String monthTo, String dayTo, String hourTo, String minuteTo, String feelingsIntensity,
                              String feelingRating, String selectedFeelings, String comment) {
        Log.d("comment from AssetsWriter", comment);
        File folder = (Environment.getExternalStorageDirectory());
        String fileName = folder.toString() + "/" + "feelingsdiary.csv";

        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            CSVWriter writer = new CSVWriter(fileWriter, '\t');
            String[] entries = new String[] {yearFrom, monthFrom, dayFrom, hourFrom, minuteFrom,
                    yearTo, monthTo, dayTo, hourTo, minuteTo, feelingsIntensity, feelingRating, selectedFeelings, comment};
//            Log.d("Entries: ", entries[13]);
            writer.writeNext(entries);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
