package net.yvin.codaview.app.activity;

import android.os.Bundle;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.repository.DiaryTitlesRepository;
import net.yvin.codaview.app.repository.FaveDailyStorage;
import net.yvin.codaview.app.service.PathService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yuriy.Vinogradov on 03.10.2014.
 */
public class FaveDailyActivity extends MenuAbstractActivity {

    DiaryTitlesRepository diaryTitlesRepo = new DiaryTitlesRepository();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favedaily);
        List<String> dailyIds = FaveDailyStorage.readAll();
        Map<String, String> entryMap = new HashMap<>();
        for (String id : dailyIds)
            entryMap.put(id, diaryTitlesRepo.find(id, PathService.diaryTitle(), this));

    }
}
