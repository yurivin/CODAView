package net.yvin.codaview.app.activity.base;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.FaveDailyActivity;
import net.yvin.codaview.app.activity.MenuActivity;
import net.yvin.codaview.app.context.LanguageContext;

/**
 * Created by Юрий on 12.05.2014.
 */
public class MenuListAbstractActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if(this instanceof FaveDailyActivity) {
            menu.findItem(R.id.action_delete).setVisible(true);
            menu.findItem(R.id.action_star).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        new MenuSelectorImpl(item, this).select();
        return super.onOptionsItemSelected(item);
    }
}
