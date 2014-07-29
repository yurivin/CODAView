package net.yvin.codaview.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.activity.utils.ActivityLuncher;

/**
 * Created by Юрий on 27.07.2014.
 */
public class FeelingsDiaryActivity extends MenuAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings);
    }

    public void clickBtnNewEntry(View v) {
        Intent intent = new Intent(this, NewFeelingsDiaryEntryActivity.class);
        new ActivityLuncher(intent, this);
    }
}
