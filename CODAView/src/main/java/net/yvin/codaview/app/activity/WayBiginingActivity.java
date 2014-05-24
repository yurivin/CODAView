package net.yvin.codaview.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.activity.utils.ActivityLuncher;
import net.yvin.codaview.app.utils.Constants;

/**
 * Created by Юрий on 24.05.2014.
 */
public class WayBiginingActivity extends MenuAbstractActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waybegining);
    }

    public void clickBtnWhatCodependency(View v) {
        Intent intent = new Intent(this, TextActivity.class);
        intent.putExtra(Constants.TITLE, getString(R.string.whatcodependency));
        intent.putExtra(Constants.FIELD, Constants.WAYBEGINING);
        intent.putExtra(Constants.TEXT, Constants.WHATCODEPENDENCY);
        new ActivityLuncher(intent, this);
    }

    public void clickBtnPatterns(View v) {
        Intent intent = new Intent(this, TextActivity.class);
        intent.putExtra(Constants.TITLE, getString(R.string.patterns));
        intent.putExtra(Constants.FIELD, Constants.WAYBEGINING);
        intent.putExtra(Constants.TEXT, Constants.PATTERNS);
        new ActivityLuncher(intent, this);
    }

    public void clickBtnWelcome(View v) {
        Intent intent = new Intent(this, TextActivity.class);
        intent.putExtra(Constants.TITLE, getString(R.string.welcome));
        intent.putExtra(Constants.FIELD, Constants.WAYBEGINING);
        intent.putExtra(Constants.TEXT, Constants.WELCOME);
        new ActivityLuncher(intent, this);
    }

}
