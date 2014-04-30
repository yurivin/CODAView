package net.yvin.codaview.app.activity;

import android.content.Intent;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import android.os.Bundle;
import android.widget.TextView;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.repository.DailyContentRepository;
import net.yvin.codaview.app.repository.DiaryTitlesRepository;
import net.yvin.codaview.app.utils.AssetsTxtReader;
import net.yvin.codaview.app.utils.DateUtils;
import net.yvin.codaview.app.utils.PropertiesFileReader;


public class DailyActivity extends MenuAbstractActivity {

    TextView dayTitleTv, quoteTv, contentTv, sumTv;
    DiaryTitlesRepository diaryTitlesRepo = new DiaryTitlesRepository();
    DailyContentRepository dailyContentRepo = new DailyContentRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        String dailyId = DateUtils.nowToDailyId();
        if (getIntent() != null) {
            Intent intent = getIntent();
            if (intent.getExtras() != null) {
                if (intent.getExtras().containsKey("dailyId")) {
                    dailyId = intent.getStringExtra("dailyId");
                }
            }
        }
        dayTitleTv = (TextView) findViewById(R.id.dayTitleTv);
        dayTitleTv.setText(diaryTitlesRepo.find(dailyId, this));
        quoteTv = (TextView) findViewById(R.id.quoteTv);
        quoteTv.setText(dailyContentRepo.find(dailyId + "-" + "quote", this));
        contentTv = (TextView) findViewById(R.id.contentTv);
        contentTv.setText(dailyContentRepo.find(dailyId + "-" + "main", this));
        sumTv = (TextView) findViewById(R.id.sumTv);
        sumTv.setText(dailyContentRepo.find(dailyId + "-" + "sum", this));
    }
}
