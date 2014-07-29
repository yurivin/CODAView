package net.yvin.codaview.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.activity.utils.ActivityLuncher;
import net.yvin.codaview.app.utils.Constants;

import static net.yvin.codaview.app.utils.Constants.*;

/**
 * Created by Yuriy on 24.04.2014.
 */
public class MenuActivity extends MenuAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void clickBtnCalendar(View v) {
        new ActivityLuncher(new Intent(this, CalendarActivity.class), this);
    }

    public void clickBtnSteps(View v) {
        Intent intent = new Intent(this, TwelveActivity.class);
        intent.putExtra(Constants.TWELVE, STEPS);
        new ActivityLuncher(intent, this);
    }

    public void clickBtnTraditions(View v) {
        Intent intent = new Intent(this, TwelveActivity.class);
        intent.putExtra(Constants.TWELVE, TRADITIONS);
        new ActivityLuncher(intent, this);
    }

    public void clickBtnPromises(View v) {
        Intent intent = new Intent(this, TwelveActivity.class);
        intent.putExtra(Constants.TWELVE, PROMISES);
        new ActivityLuncher(intent, this);
    }

    public void clickBtnWayBeginning(View v) {
        Intent intent = new Intent(this, WayBiginingActivity.class);
        new ActivityLuncher(intent, this);
    }

    public void clickBtnFillingsDiary(View v) {
        Intent intent = new Intent(this, FeelingsDiaryActivity.class);
        new ActivityLuncher(intent, this);
    }


}
