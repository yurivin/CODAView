package net.yvin.codaview.app.service;

import android.content.Context;
import android.util.Log;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;
import net.yvin.codaview.app.filters.FeelingsDiaryFilter;
import net.yvin.codaview.app.utils.Constants;

import java.util.*;

/**
 * Created by Yuriy.Vinogradov on 14.08.2014.
 */
public class DiaryService {

    Context context;

    public DiaryService(Context context){
        this.context = context;
    }

    public void sortByAndFilter(Set<FeelingsDiaryEntry> diaryEntries, Comparator comparator, FeelingsDiaryFilter filter) {

    }

    public String[] getTitles(List<FeelingsDiaryEntry> list) {
        String[] titles = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            FeelingsDiaryEntry entry = list.get(i);
            titles[i] = entry.yearFrom + Constants.DEFICE + entry.monthFrom + Constants.DEFICE + entry.dayFrom + Constants.SPACE +
                    entry.hourFrom + Constants.DOUBLE_POINT + entry.minuteFrom;
        }
        for(String string : titles) {
            Log.d("title: ", string);
        }
        return titles;
    }

    public String[] getContent(List<FeelingsDiaryEntry> diaryEntries) {
        String[] content = new String[diaryEntries.size()];
        List<FeelingsDiaryEntry> list = new ArrayList<>(diaryEntries);
        for (int i = 0; i < diaryEntries.size(); i++) {
            FeelingsDiaryEntry entry = list.get(i);
            String feelings = new String();
            for(int y = 0; y < entry.selectedFeelings.length; y++) {
                feelings = feelings + entry.selectedFeelings[y];
                if(y < entry.selectedFeelings.length - 1 ) feelings = feelings + Constants.COMMA_SPACE;
                        else feelings = feelings + ".";
            }

            content[i] = context.getString(R.string.until) + Constants.DOUBLE_POINT + Constants.SPACE + entry.yearTo +
                    Constants.DEFICE + entry.monthTo + Constants.DEFICE + entry.dayTo + Constants.SPACE +
                    entry.hourTo + Constants.DOUBLE_POINT + entry.minuteTo +
            Constants.NEW_LINE + Constants.NEW_LINE +
                    context.getString(R.string.intensity) + Constants.DOUBLE_POINT + Constants.SPACE +  entry.feelingsIntensity +
            Constants.NEW_LINE + Constants.NEW_LINE +
                    context.getString(R.string.rating) + Constants.DOUBLE_POINT + Constants.SPACE +  entry.feelingRating +
            Constants.NEW_LINE + Constants.NEW_LINE +
                    context.getString(R.string.feelings) + Constants.DOUBLE_POINT + Constants.SPACE + feelings +
            Constants.NEW_LINE + Constants.NEW_LINE +
                    context.getString(R.string.comment) + Constants.DOUBLE_POINT + Constants.SPACE + entry.comment;
        }
        for(String string : content) {
            Log.d("Content: ", string);
        }
        return content;
    }
}
