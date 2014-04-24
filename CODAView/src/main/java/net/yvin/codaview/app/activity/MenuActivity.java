package net.yvin.codaview.app.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;

/**
 * Created by Yuriy on 24.04.2014.
 */
public class MenuActivity extends MenuAbstractActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void clickBtnCalendar(View v) {
                this.startActivity(new Intent(this, CalendarActivity.class));
                this.finish();
    }
}
