package net.yvin.codaview.app.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.SimpleExpandableListAdapter;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuExpandableListAbstractActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuriy.Vinogradov on 08.08.2014.
 */
public class PrayerActivity extends MenuExpandableListAbstractActivity {
    ArrayList<Map<String, String>> groupData;
    ArrayList<Map<String, String>> childDataItem;
    ArrayList<ArrayList<Map<String, String>>> childData;
    Map<String, String> m;
    String[] prayTitles;
    String[] prayContent;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        prayTitles = res.getStringArray(R.array.prayertitles);
        prayContent = res.getStringArray(R.array.prayerContent);
        String[] groupFrom = new String[]{"title"};
        int[] groupTo = new int[]{android.R.id.text1};
        String[] childFrom = new String[]{"content"};
        int[] childTo = new int[]{android.R.id.text1};

        groupData = new ArrayList<>();
        for (String group : prayTitles) {
            m = new HashMap<>();
            m.put("title", group);
            groupData.add(m);
        }
        childData = new ArrayList<>();
        for (String content : prayContent) {
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
        setListAdapter(adapter);
    }
}
