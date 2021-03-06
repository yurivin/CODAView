package net.yvin.codaview.app.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuListAbstractActivity;
import net.yvin.codaview.app.activity.utils.ActivityLuncher;
import net.yvin.codaview.app.repository.DiaryTitlesRepository;
import net.yvin.codaview.app.repository.FaveDailyStorage;
import net.yvin.codaview.app.service.PathService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yuriy.Vinogradov on 03.10.2014.
 */
public class FaveDailyActivity extends MenuListAbstractActivity {

    DiaryTitlesRepository diaryTitlesRepo = new DiaryTitlesRepository();
    Map<String, String> entryMap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> dailyIds = FaveDailyStorage.readAll();
        entryMap = new HashMap<>();
        for (String id : dailyIds)
            entryMap.put(id, diaryTitlesRepo.find(id, PathService.diaryTitle(), this));
/*        for(Map.Entry entry : entryMap.entrySet()) {
            Log.d("Entry: ", entry.getKey() + " / "+entry.getValue());
        } */
        String[] valueArray = new String[entryMap.values().size()];
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.text_list_item, entryMap.values().toArray(valueArray));
        setListAdapter(adapter);
        if(dailyIds.size() == 0) {
            showAlertNoData();
        }
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        if(FaveDailyStorage.isDelete()){
            FaveDailyStorage.setDelete(false);
            FaveDailyStorage.delete(new ArrayList<>(entryMap.keySet()).remove(position));
            recreate();
        } else {
            Intent intent = new Intent(getApplicationContext(), DailyActivity.class);
            intent.putExtra("dailyId", new ArrayList<>(entryMap.keySet()).remove(position));
            new ActivityLuncher(intent, this);
        }
    }

    private void showAlertNoData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert)
                .setMessage(R.string.no_fave_exists)
                .setNegativeButton("ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
