package net.yvin.codaview.app.activity;

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


        dayTitleTv = (TextView)findViewById(R.id.dayTitleTv);
        dayTitleTv.setText(diaryTitlesRepo.find(DateUtils.nowToDailyId(), this));
        quoteTv = (TextView)findViewById(R.id.quoteTv);
        quoteTv.setText(dailyContentRepo.find("0112" + "-" + "quote", this));
        contentTv = (TextView)findViewById(R.id.contentTv);
        contentTv.setText(dailyContentRepo.find("0112" + "-" + "main", this));
        sumTv = (TextView)findViewById(R.id.sumTv);
        sumTv.setText(dailyContentRepo.find("0112" + "-" + "sum", this));
    }
}
