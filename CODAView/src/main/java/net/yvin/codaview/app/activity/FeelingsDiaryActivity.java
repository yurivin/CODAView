package net.yvin.codaview.app.activity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;
import net.yvin.codaview.app.comparators.FeelingsDiaryEntryByBeginningDate;
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
    Map<String, String> m;
    String[] feelingsTitles;
    String[] feelingsContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings);
        expListView = (ExpandableListView) findViewById(R.id.entries);
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
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
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
        DiaryService diaryService = new DiaryService(this);
        List<FeelingsDiaryEntry> diaryEntries = FeelingDiaryReader.readAll();
        Collections.sort(diaryEntries, new FeelingsDiaryEntryByBeginningDate());
        feelingsTitles = diaryService.getTitles(diaryEntries);
        feelingsContent = diaryService.getContent(diaryEntries);
    }
}
