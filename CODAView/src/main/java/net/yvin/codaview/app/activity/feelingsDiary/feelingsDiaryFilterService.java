package net.yvin.codaview.app.activity.feelingsDiary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;
import net.yvin.codaview.app.filters.FilterByIntensity;
import net.yvin.codaview.app.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yuriy.Vinogradov on 28.08.2014.
 * TODO refactor code of Filters for FeelingsDiaryActivity to be in this class
 */
public class FeelingsDiaryFilterService {

    List<FeelingsDiaryEntry> entries;
    Context context;
    FeelingsDiaryFilterService(List<FeelingsDiaryEntry> entries, Context context) {
        this.entries = entries;
        this.context = context;
    }

    void understandSelectedFilters(List<Integer> filterNumbers) {
        for (int integer : filterNumbers) {
            switch (integer) {
                case 0:
                    showFilterByIntensityDialog();
                    break;
            }
        }
    }

    void showFilterByIntensityDialog() {
        final boolean[] mCheckedItems = new boolean[6];
        final String[] checkRatingTitles = {"0", "1", "2", "3", "4", "5"};
        final List<Integer> intensityNumbers = new ArrayList<>();
        new AlertDialog.Builder(context).setTitle(R.string.select_intensity)
                .setMultiChoiceItems(checkRatingTitles, mCheckedItems,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                ActivityUtils.handleMultiChoiceItemClick(which, isChecked, intensityNumbers);
                            }
                        })
                .setPositiveButton(R.string.ready,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                processFilterByIntensity(intensityNumbers);
                            }
                        })
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();

                            }
                        }).show();
    }


    private void processFilterByIntensity(List<Integer> intensityNumbers) {
        List<FeelingsDiaryEntry> tempAll = new ArrayList<>();
        List<FeelingsDiaryEntry> tempFiltered = new ArrayList<>();
        for (int intensity : intensityNumbers) {
            tempAll.addAll(entries);
            new FilterByIntensity(intensity).filter(tempAll);
            tempFiltered.addAll(tempAll);
        }
        ((FeelingsDiaryActivity)context).showData(tempFiltered);
    }
}
