package net.yvin.codaview.app.repository;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import net.yvin.codaview.app.service.PathService;
import net.yvin.codaview.app.utils.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy.Vinogradov on 11.08.2014.
 */
public class FaveDailyStorage {

    public static void faveDaily(String dailyId) {
        File extSoreFolder = (Environment.getExternalStorageDirectory());
        File folder = new File(extSoreFolder, Constants.CODAAPP);
        if(!folder.exists()) {
            folder.mkdir();
        }
        String fileName = folder.toString() + "starred.csv";
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

    public static List<String> readAll() {
        List<String> entryList = new ArrayList<>();
        File folder = (Environment.getExternalStorageDirectory());
        String fileName = folder.toString() + "/" + "feelingsdiary.csv";
        try {
            CSVReader reader = new CSVReader(new FileReader(fileName), '\t');
            List<String[]> entries = reader.readAll();
            for(String[] entry : entries) entryList.add(entry[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entryList;
    }
}
