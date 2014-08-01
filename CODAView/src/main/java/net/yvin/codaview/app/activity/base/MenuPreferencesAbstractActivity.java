package net.yvin.codaview.app.activity.base;

import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.context.LanguageContext;

/**
 * Created by Yuriy.Vinogradov on 31.07.2014.
 */
public class MenuPreferencesAbstractActivity extends PreferenceActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        LanguageContext.setLanguage(this);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        new MenuSelectorImpl(item, this).select();
        return super.onOptionsItemSelected(item);
    }
}
