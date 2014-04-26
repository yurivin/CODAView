package net.yvin.codaview.app.activity;

import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import android.os.Bundle;
import android.widget.TextView;
import net.yvin.codaview.app.R;
import repository.DiaryTitlesRepository;


public class DailyActivity extends MenuAbstractActivity {

    TextView dayTitle;
    DiaryTitlesRepository diaryTitlesRepo = new DiaryTitlesRepository();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        dayTitle = (TextView)findViewById(R.id.dayTitle);
        dayTitle.setText(diaryTitlesRepo.find("0212", this));
    }
}
