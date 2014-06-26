package net.yvin.codaview.app.activity;

import android.os.Bundle;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;

/**
 * Created by Юрий on 26.06.2014.
 */
public class SettingsActivity extends MenuAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
