package net.yvin.codaview.app.service;

import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;
import net.yvin.codaview.app.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Yuriy.Vinogradov on 14.08.2014.
 */
public class DiaryService {

    public static String[] getTitles(Set<FeelingsDiaryEntry> diaryEntries) {
        String[] titles = new String[diaryEntries.size()];
        List<FeelingsDiaryEntry> list = new ArrayList<>(diaryEntries);
        for (int i = 0; i < diaryEntries.size(); i++) {
            FeelingsDiaryEntry entry = list.get(i);
            titles[i] = entry.yearFrom + Constants.DEFICE + entry.monthFrom + Constants.DEFICE + entry.dayFrom + Constants.SPACE +
                    entry.hourFrom + Constants.DOUBLE_POINT + entry.minuteFrom;
        }
        return titles;
    }

    public static String[] getContent(Set<FeelingsDiaryEntry> diaryEntries) {
        String[] content = new String[diaryEntries.size()];
        return content;
    }
}
