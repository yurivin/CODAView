package net.yvin.codaview.app.activity.base;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.context.LanguageContext;

/**
 * Created by Yuriy.Vinogradov on 08.08.2014.
 */
public class MenuExpandableListAbstractActivity extends ExpandableListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageContext.setLanguage(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        new MenuSelectorImpl(item, this).select();
        return super.onOptionsItemSelected(item);
    }
}
