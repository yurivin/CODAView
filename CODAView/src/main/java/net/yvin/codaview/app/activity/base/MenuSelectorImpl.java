package net.yvin.codaview.app.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.DailyActivity;
import net.yvin.codaview.app.activity.MenuActivity;
import net.yvin.codaview.app.activity.SettingsActivity;
import net.yvin.codaview.app.activity.utils.ActivityLuncher;

/**
 * Created by Юрий on 17.05.2014.
 */
public class MenuSelectorImpl implements MenuSelector {
    MenuItem item;
    Activity activity;

    public MenuSelectorImpl(MenuItem item, Activity activity) {
        this.item = item;
        this.activity = activity;
    }

    @Override
    public void select() {
        switch (item.getItemId()) {
            case R.id.action_menu:
                new ActivityLuncher(new Intent(activity, MenuActivity.class), activity);
                break;
            case R.id.action_star:
                if(activity instanceof DailyActivity) {
                    Toast.makeText(activity, "Insertion in to starred", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(activity, "Open starred", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

}
