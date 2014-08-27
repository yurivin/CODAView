package net.yvin.codaview.app.activity;

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
import net.yvin.codaview.app.comparators.FeelingsDiaryByBeginningDate;
import net.yvin.codaview.app.comparators.FeelingsDiaryByIntensity;
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
    List<Integer> checkedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings_diary);
        expListView = (ExpandableListView) findViewById(R.id.entries);
        diaryService = new DiaryService(this);
        getData();
        feelExpandableListView();
    }

    private void feelExpandableListView() {
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
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);
        expListView.setAdapter(adapter);
    }

    private void getData() {
        diaryEntries = FeelingDiaryReader.readAll();
//        diaryService.sort(diaryEntries, new FeelingsDiaryEntryByBeginningDate());
        extractData();
    }

    private void extractData() {
        feelingsTitles = diaryService.getTitles(diaryEntries);
        feelingsContent = diaryService.getContent(diaryEntries);
    }

    public void clickBtnSort(View v) {
        showSortDialog();
    }

    private void showSortDialog() {
        checkedList = new ArrayList<>();
        final boolean[] mCheckedItems = {true, false};
        //adding first automatically checked entry
        checkedList.add(0);
        Resources res = getResources();
        final String[] sortTitles = res.getStringArray(R.array.sortTitles);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.select_sorting_type)
                .setCancelable(false)
                .setMultiChoiceItems(sortTitles, mCheckedItems,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                if (isChecked == true)
                                    checkedList.add(which);
                                else
                                    for (int i = 0; i < checkedList.size(); i++) {
                                        if (checkedList.get(i) == which)
                                            checkedList.remove(i);
                                    }
                            }
                        })
                .setPositiveButton(R.string.ready,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                understandSelection(checkedList);
                            }
                        })
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();

                            }
                        });
        builder.show();
    }

    private void understandSelection(List<Integer> checkedList) {
        Comparator<FeelingsDiaryEntry> comparator = null;
        for (Integer checked : checkedList) {
            switch (checked) {
                case 0:
                    comparator = new FeelingsDiaryByBeginningDate();
                    break;
                case 1:
                    comparator = new FeelingsDiaryByIntensity();
            }
            sortBySelection(comparator);
        }

    }

    private void sortBySelection(Comparator<FeelingsDiaryEntry> comparator) {
        diaryService.sort(diaryEntries, comparator);
        extractData();
        feelExpandableListView();
        adapter.notifyDataSetChanged();
    }
}
