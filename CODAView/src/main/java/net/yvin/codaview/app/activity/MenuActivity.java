package net.yvin.codaview.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
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
        lunchActivity(new Intent(this, CalendarActivity.class));
    }

    public void clickBtnSteps(View v) {
        Intent intent = new Intent(this, TwelveActivity.class);
        intent.putExtra("twelve", STEPS);
        lunchActivity(intent);
    }

    public void clickBtnTraditions(View v) {
        Intent intent = new Intent(this, TwelveActivity.class);
        intent.putExtra("twelve", TRADITIONS);
        lunchActivity(intent);
    }

    public void clickBtnPromises(View v) {
        Intent intent = new Intent(this, TwelveActivity.class);
        intent.putExtra("twelve", PROMISES);
        lunchActivity(intent);
    }

    private void lunchActivity(Intent intent) {
        this.startActivity(intent);
        this.finish();
    }
}
