package net.yvin.codaview.app.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.activity.base.MenuPreferencesAbstractActivity;

/**
 * Created by Юрий on 26.06.2014.
 */
public class SettingsActivity extends MenuPreferencesAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
}
