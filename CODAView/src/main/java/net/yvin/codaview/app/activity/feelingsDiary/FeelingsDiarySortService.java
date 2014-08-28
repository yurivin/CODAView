package net.yvin.codaview.app.activity.feelingsDiary;

import net.yvin.codaview.app.activity.feelingsDiary.FeelingsDiaryActivity;
import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;
import net.yvin.codaview.app.comparators.feelingsDiaryEntry.ByBeginningDate;
import net.yvin.codaview.app.comparators.feelingsDiaryEntry.ByEndDate;
import net.yvin.codaview.app.comparators.feelingsDiaryEntry.ByIntensity;
import net.yvin.codaview.app.comparators.feelingsDiaryEntry.ByLikeness;
import net.yvin.codaview.app.service.DiaryService;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Yuriy.Vinogradov on 28.08.2014.
 */
public class FeelingsDiarySortService {

    private DiaryService diaryService;
    private List<FeelingsDiaryEntry> diaryEntries;
    private FeelingsDiaryActivity feelingsdiaryActivity;

    FeelingsDiarySortService(DiaryService diaryService, List<FeelingsDiaryEntry> diaryEntries, FeelingsDiaryActivity feelingsdiaryActivity) {
        this.diaryService = diaryService;
        this.diaryEntries = diaryEntries;
        this.feelingsdiaryActivity = feelingsdiaryActivity;
    }

    void understandComparatorSelection(int checked) {
        Comparator<FeelingsDiaryEntry> comparator = null;
        switch (checked) {
            case 0:
                comparator = new ByBeginningDate();
                break;
            case 1:
                comparator = new ByEndDate();
                break;
            case 2:
                comparator = new ByIntensity();
                break;
            case 3:
                comparator = new ByLikeness();
                break;
        }
        sortBySelection(comparator);
    }

    private void sortBySelection(Comparator<FeelingsDiaryEntry> comparator) {
        diaryService.sort(diaryEntries, comparator);
        feelingsdiaryActivity.extractData();
        feelingsdiaryActivity.feelExpandableListView();
        feelingsdiaryActivity.adapter.notifyDataSetChanged();
    }
}
