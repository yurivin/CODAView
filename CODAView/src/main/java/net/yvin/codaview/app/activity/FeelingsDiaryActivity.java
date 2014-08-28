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
import net.yvin.codaview.app.comparators.feelingsDiaryEntry.ByBeginningDate;
import net.yvin.codaview.app.comparators.feelingsDiaryEntry.ByEndDate;
import net.yvin.codaview.app.comparators.feelingsDiaryEntry.ByIntensity;
import net.yvin.codaview.app.comparators.feelingsDiaryEntry.ByLikeness;
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
                R.layout.simple_expandable_list_item_my,
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
        Resources res = getResources();
        final String[] sortTitles = res.getStringArray(R.array.sortTitles);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.select_sorting_type)
                .setCancelable(false)
                .setSingleChoiceItems(sortTitles, -1,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int item) {
                                understandSelection(item);
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
                        });
        builder.show();
    }

    private void understandSelection(int checked) {
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
        extractData();
        feelExpandableListView();
        adapter.notifyDataSetChanged();
    }
}
