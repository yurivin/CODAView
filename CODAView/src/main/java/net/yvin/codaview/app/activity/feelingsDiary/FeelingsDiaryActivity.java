package net.yvin.codaview.app.activity.feelingsDiary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;
import net.yvin.codaview.app.filters.FilterByIntensity;
import net.yvin.codaview.app.service.DiaryService;
import net.yvin.codaview.app.utils.ActivityUtils;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings_diary);
        expListView = (ExpandableListView) findViewById(R.id.entries);
        diaryService = new DiaryService(this);
        getData();
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

    void showData(List<FeelingsDiaryEntry> resultList) {
        diaryEntries = resultList;
        extractData();
        feelExpandableListView();
        adapter.notifyDataSetChanged();
    }

    private void getData() {
        diaryEntries = FeelingDiaryReader.readAll();
        extractData();
    }

    private void showFiltersDialog() {
        final Context context = this;
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
                                ActivityUtils.handleMultiChoiceItemClick(which, isChecked, filterNumbers);
                            }
                        })
                .setPositiveButton(R.string.ready,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int id) {
                               new FeelingsDiaryFilterService(diaryEntries, context).understandSelectedFilters(filterNumbers);
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
        final Context context = this;
        Resources res = getResources();
        final String[] sortTitles = res.getStringArray(R.array.sortTitles);
        new AlertDialog.Builder(this).setTitle(R.string.select_sorting_type)
                .setCancelable(false)
                .setSingleChoiceItems(sortTitles, -1,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int item) {
                                new FeelingsDiarySortService(diaryService, diaryEntries, context).understandComparatorSelection(item);
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
}
