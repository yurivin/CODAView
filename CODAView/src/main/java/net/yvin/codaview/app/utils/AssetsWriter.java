package net.yvin.codaview.app.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import net.yvin.codaview.app.service.PathService;

import java.io.*;
import java.util.List;

/**
 * Created by Yuriy.Vinogradov on 11.08.2014.
 */
public class AssetsWriter {

    Context context;

    public AssetsWriter(Context context) {
        this.context = context;
    }

    public void feelingsDiary(String yearFrom, String monthFrom, String dayFrom, String hourFrom, String minuteFrom,
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

    public void favoritizeDaily(String dailyId) {
        File folder = (Environment.getExternalStorageDirectory());
        String fileName = folder.toString() + "/CODAapp/" + "favoriteDaily.csv";
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            CSVWriter writer = new CSVWriter(fileWriter, '\t');
            String[] entries = new String[] {dailyId};
            Log.d("Entries: ", entries[0]);
            writer.writeNext(entries);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
