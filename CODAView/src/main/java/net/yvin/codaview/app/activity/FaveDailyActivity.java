package net.yvin.codaview.app.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuListAbstractActivity;
import net.yvin.codaview.app.repository.DiaryTitlesRepository;
import net.yvin.codaview.app.repository.FaveDailyStorage;
import net.yvin.codaview.app.service.PathService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yuriy.Vinogradov on 03.10.2014.
 */
public class FaveDailyActivity extends MenuListAbstractActivity {

    DiaryTitlesRepository diaryTitlesRepo = new DiaryTitlesRepository();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> dailyIds = FaveDailyStorage.readAll();
        Map<String, String> entryMap = new HashMap<>();
        for (String id : dailyIds)
            entryMap.put(id, diaryTitlesRepo.find(id, PathService.diaryTitle(), this));
        for(Map.Entry entry : entryMap.entrySet()) {
            Log.d("Entry: ", entry.getKey() + " / "+entry.getValue());
        }
        String[] valueArray = new String[entryMap.values().size()];
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.text_list_item, entryMap.values().toArray(valueArray));
        setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

    }
}
