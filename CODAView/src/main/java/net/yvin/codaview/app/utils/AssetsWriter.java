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

    public void feelingsDiary(String yearfrom, String monthFrom, String dayFrom, String hourFrom, String minutefrom,
                              String yearTo, String monthTo, String dayTo, String hourTo, String minuteTo,
                              String feelingRating, String selectedFeelings, String comment) {
        Log.d("comment from AssetsWriter", comment);
        File folder = (Environment.getExternalStorageDirectory());
        String fileName = folder.toString() + "/" + "feelingsdiary.csv";

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            CSVWriter writer = new CSVWriter(fileWriter, '\t');
            String[] entries = new String[] {yearfrom, monthFrom, dayFrom, hourFrom, minutefrom,
            yearTo, monthTo, dayTo, hourTo, minuteTo, feelingRating, selectedFeelings, comment};
            Log.d("Entries: ", entries[12]);
            writer.writeNext(entries);
            writer.close();
            CSVReader reader = new CSVReader(new FileReader(fileName), '\t');
            List<String[]> entriesList = reader.readAll();
            StringBuilder builder = new StringBuilder();
            for(String[] entry : entriesList) {
                for(String part : entry) {
                    builder.append(part);
                    builder.append(",");
                }
            }
            Log.d("CSV entries", builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
