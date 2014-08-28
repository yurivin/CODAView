package net.yvin.codaview.app.activity.feelingsDiary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;
import net.yvin.codaview.app.service.DiaryService;
import net.yvin.codaview.app.utils.FeelingDiaryReader;

import java.util.*;

/**
 * Created by Юрий on 27.07.2014.
 */
public class FeelingsDiaryActivity extends MenuAbstractActivity {

    ExpandableListView expListView;
    ArrayList<Map<String, String>> groupData;
    ArrayList<Map<String, String>> childDataItem;
    ArrayList<ArrayList<Map<String, String>>> childData;
    DiaryService diaryService;
    Map<String, String> m;
    String[] feelingsTitles;
    String[] feelingsContent;
    List<FeelingsDiaryEntry> diaryEntries;
    SimpleExpandableListAdapter adapter;
    FeelingsDiarySortService sortService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings_diary);
        expListView = (ExpandableListView) findViewById(R.id.entries);
        diaryService = new DiaryService(this);
        getData();
        sortService = new FeelingsDiarySortService(diaryService, diaryEntries, this);
        feelExpandableListView();
    }

    public void feelExpandableListView() {
        String[] groupFrom = new String[]{"title"};
        int[] groupTo = new int[]{android.R.id.text1};
        String[] childFrom = new String[]{"content"};
        int[] childTo = new int[]{android.R.id.text1};
        groupData = new ArrayList<>();
        for (String group : feelingsTitles) {
            m = new HashMap<>();
            m.put("title", group);
            groupData.add(m);
        }
        childData = new ArrayList<>();
        for (String content : feelingsContent) {
            childDataItem = new ArrayList<>();
            m = new HashMap<>();
            m.put("content", content);
            childDataItem.add(m);
            childData.add(childDataItem);
        }
        adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                R.layout.simple_expandable_list_item_my,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);
        expListView.setAdapter(adapter);
    }

    public void clickBtnSort(View v) {
        showSortDialog();
    }

    public void clickBtnFilter(View v) {
        showFiltersDialog();
    }

    void extractData() {
        feelingsTitles = diaryService.getTitles(diaryEntries);
        feelingsContent = diaryService.getContent(diaryEntries);
    }

    private void getData() {
        diaryEntries = FeelingDiaryReader.readAll();
        extractData();
    }

    private void showFiltersDialog() {
        final boolean[] mCheckedItems = new boolean[1];
        Resources res = getResources();
        final String[] checkFilterTitles = res.getStringArray(R.array.filterTitles);
        final List<Integer> filterNumbers = new ArrayList<>();
        new AlertDialog.Builder(this).setTitle(R.string.filters)
                .setMultiChoiceItems(checkFilterTitles, mCheckedItems,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                handleMultyChoiceItemclick(which, isChecked, filterNumbers);
                            }
                        })
                .setPositiveButton(R.string.ready,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                understandSelectedFilters(filterNumbers);
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

    private void showSortDialog() {
        Resources res = getResources();
        final String[] sortTitles = res.getStringArray(R.array.sortTitles);
        new AlertDialog.Builder(this).setTitle(R.string.select_sorting_type)
                .setCancelable(false)
                .setSingleChoiceItems(sortTitles, -1,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int item) {
                                sortService.understandComparatorSelection(item);
                                dialog.cancel();
                            }
                        })
                .setNeutralButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        }).show();
    }

    private void understandSelectedFilters(List<Integer> filterNumbers) {
        for(int integer : filterNumbers) {
            switch(integer) {
                case 0 :
                    filterByIntensity();
                    break;
            }
        }
    }

    private void filterByIntensity() {
        final boolean[] mCheckedItems = new boolean[6];
        final String[] checkRatingTitles = { "0", "1", "2", "3", "4", "5" };
        final List<Integer> filterNumbers = new ArrayList<>();
        new AlertDialog.Builder(this).setTitle(R.string.select_ratings)
                .setMultiChoiceItems(checkRatingTitles, mCheckedItems,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                handleMultyChoiceItemclick(which, isChecked, filterNumbers);
                            }
                        })
                .setPositiveButton(R.string.ready,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int id) {

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

    private void handleMultyChoiceItemclick(int which, boolean isChecked, List<Integer> filterNumbers) {
        if (isChecked == true)
            filterNumbers.add(which);
        else
            for (int i = 0; i < filterNumbers.size(); i++)
                if (filterNumbers.get(i) == which)
                    filterNumbers.remove(i);
    }
}
