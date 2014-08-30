package net.yvin.codaview.app.activity.feelingsDiary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;
import net.yvin.codaview.app.filters.FilterByIntensity;
import net.yvin.codaview.app.filters.FilterByRate;
import net.yvin.codaview.app.activity.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy.Vinogradov on 28.08.2014.
 */
public class feelingsDiaryFilterService {

    List<FeelingsDiaryEntry> entries;
    Context context;
    List<Integer> filterNumbers;

    feelingsDiaryFilterService(List<FeelingsDiaryEntry> entries, Context context) {
        this.entries = entries;
        this.context = context;
    }

    void understandSelectedFilters(List<Integer> filterNumbers) {
        this.filterNumbers = filterNumbers;
        for (int integer : filterNumbers) {
            switch (integer) {
                case 0:
                    showFilterByIntensityDialog(context.getString(R.string.select_intensity), integer);
                    break;
                case 1:
                    showFilterByIntensityDialog(context.getString(R.string.select_rate), integer);
                    break;
            }
        }
    }

    void showFilterByIntensityDialog(String title, final int filterNumber) {
        final boolean[] mCheckedItems = new boolean[6];
        final String[] checkRatingTitles = {"0", "1", "2", "3", "4", "5"};
        final List<Integer> intensityNumbers = new ArrayList<>();
        new AlertDialog.Builder(context).setTitle(title)
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
                                processFilterByIntensity(intensityNumbers, filterNumber);
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


    private void processFilterByIntensity(List<Integer> numbers, int filterNumber) {
        List<FeelingsDiaryEntry> tempAll = new ArrayList<>();
        List<FeelingsDiaryEntry> tempFiltered = new ArrayList<>();
        for (int number : numbers) {
            tempAll.addAll(entries);
                switch (filterNumber) {
                    case 0:
                        new FilterByIntensity(number).filter(tempAll);
                    case 1:
                        new FilterByRate(number).filter(tempAll);
            }
            tempFiltered.addAll(tempAll);
        }
        ((FeelingsDiaryActivity) context).showData(tempFiltered);
    }
}
