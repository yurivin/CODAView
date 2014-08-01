package net.yvin.codaview.app.activity.base;

import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.MenuActivity;
import net.yvin.codaview.app.context.LanguageContext;

/**
 * Created by Юрий on 12.05.2014.
 */
public class MenuListAbstractActivity extends ListActivity {

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
