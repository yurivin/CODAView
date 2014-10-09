package net.yvin.codaview.app.repository;

import android.os.Environment;
import android.util.Log;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import net.yvin.codaview.app.utils.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy.Vinogradov on 11.08.2014.
 */
public class FaveDailyStorage {

    private static boolean delete;

    public static void faveDaily(String dailyId) {
        File extSoreFolder = (Environment.getExternalStorageDirectory());
        File folder = new File(extSoreFolder, Constants.CODAAPP);
        if (!folder.exists()) {
            folder.mkdir();
        }
        String fileName = folder.toString() + Constants.STARREDCSV;
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            CSVWriter writer = new CSVWriter(fileWriter, '\t');
            String[] entries = new String[]{dailyId};
//            Log.d("Entries: ", entries[0]);
            writer.writeNext(entries);
            writer.close();
        } catch (IOException e) {
            Log.e("IOException reading fave entries from file"," ", e);
        }
    }

    public static List<String> readAll() {
        List<String> entryList = new ArrayList<>();
        File extSoreFolder = (Environment.getExternalStorageDirectory());
        File folder = new File(extSoreFolder, Constants.CODAAPP);
        String fileName = folder.toString() + Constants.STARREDCSV;
        try {
            CSVReader reader = new CSVReader(new FileReader(fileName), '\t');
            List<String[]> entries = reader.readAll();
            for (String[] entry : entries) entryList.add(entry[0]);
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException reading fave entries from file"," ", e);
        } catch (IOException e) {
            Log.e("IOException reading fave entries from file"," ", e);
        }
        return entryList;
    }

    public static boolean delete(String dailyId) {
        File folder = new File(Environment.getExternalStorageDirectory(), Constants.CODAAPP);
        File inputFile = new File(folder.toString() + Constants.STARREDCSV);
        File tempFile = new File(folder.toString() + Constants.TEMPFILE);
        BufferedReader reader;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile.toString()));

            String lineToRemove = dailyId;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains(lineToRemove)) continue;
                writer.write(currentLine);
                writer.newLine();
            }
        } catch (Exception e) {
            Log.e("Deleting favorite exception", ": ", e);
            return false;
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                Log.e("Deleting favorite exception", "Exception during writer closing: ", e);
            }
        }
        return tempFile.renameTo(inputFile);
    }

    public static boolean isDelete() {
        return delete;
    }

    public static void setDelete(boolean delete) {
        FaveDailyStorage.delete = delete;
    }

}
