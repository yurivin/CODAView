package net.yvin.codaview.app.service;

import android.content.Context;
import android.util.Log;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;
import net.yvin.codaview.app.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Yuriy.Vinogradov on 14.08.2014.
 */
public class DiaryService {

    Context context;

    public DiaryService(Context context){
        this.context = context;
    }

    public String[] getTitles(Set<FeelingsDiaryEntry> diaryEntries) {
        String[] titles = new String[diaryEntries.size()];
        List<FeelingsDiaryEntry> list = new ArrayList<>(diaryEntries);
        for (int i = 0; i < diaryEntries.size(); i++) {
            FeelingsDiaryEntry entry = list.get(i);
            titles[i] = entry.yearFrom + Constants.DEFICE + entry.monthFrom + Constants.DEFICE + entry.dayFrom + Constants.SPACE +
                    entry.hourFrom + Constants.DOUBLE_POINT + entry.minuteFrom;
        }
        for(String string : titles) {
            Log.d("title: ", string);
        }
        return titles;
    }

    public String[] getContent(Set<FeelingsDiaryEntry> diaryEntries) {
        String[] content = new String[diaryEntries.size()];
        List<FeelingsDiaryEntry> list = new ArrayList<>(diaryEntries);
        for (int i = 0; i < diaryEntries.size(); i++) {
            FeelingsDiaryEntry entry = list.get(i);
            content[i] = context.getString(R.string.until) + Constants.DOUBLE_POINT + Constants.SPACE + entry.yearTo + Constants.DEFICE + entry.monthTo + Constants.DEFICE + entry.dayTo + Constants.SPACE +
                    entry.hourTo + Constants.DOUBLE_POINT + entry.minuteTo;
        }
        for(String string : content) {
            Log.d("Content: ", string);
        }
        return content;
    }
}
