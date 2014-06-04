package net.yvin.codaview.app.activity;

import android.content.Intent;
import android.text.format.Time;
import android.util.Log;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import android.os.Bundle;
import android.widget.TextView;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.repository.DiaryTitlesRepository;
import net.yvin.codaview.app.utils.AssetsTxtReader;
import net.yvin.codaview.app.utils.Constants;
import net.yvin.codaview.app.utils.DateUtils;


public class DailyActivity extends MenuAbstractActivity {

    TextView dayTitleTv, dateTv, quoteTv, contentTv, sumTv;
    DiaryTitlesRepository diaryTitlesRepo = new DiaryTitlesRepository();
    AssetsTxtReader txtReader = new AssetsTxtReader(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        String dailyId = DateUtils.nowToDailyId();
        if (getIntent() != null) {
            Intent intent = getIntent();
            if (intent.getExtras() != null) {
                if (intent.getExtras().containsKey(Constants.DAILYID)) {
                    dailyId = intent.getStringExtra(Constants.DAILYID);
                    Log.d(Constants.DAILYID, dailyId);
                }
            }
        }

        Time now = new Time();
        now.setToNow();

        dayTitleTv = (TextView) findViewById(R.id.dayTitleTv);
        dayTitleTv.setText(diaryTitlesRepo.find(dailyId, this));
        dateTv =(TextView) findViewById(R.id.dateTv);
        dateTv.setText(now.monthDay + "." + String.valueOf(now.month + 1) + "." + now.year);
        quoteTv = (TextView) findViewById(R.id.quoteTv);
        quoteTv.setText(txtReader.getAssetsTxt(Constants.DAILY + Constants.SLASH + Constants.QUOTE  + Constants.SLASH + dailyId + Constants.POINTTXT));
        contentTv = (TextView) findViewById(R.id.contentTv);
        contentTv.setText(txtReader.getAssetsTxt(Constants.DAILY + Constants.SLASH + Constants.MAIN + Constants.SLASH + dailyId + Constants.POINTTXT));
        sumTv = (TextView) findViewById(R.id.sumTv);
        sumTv.setText(txtReader.getAssetsTxt(Constants.DAILY + Constants.SLASH + Constants.SUM + Constants.SLASH + dailyId + Constants.POINTTXT));

    }
}
