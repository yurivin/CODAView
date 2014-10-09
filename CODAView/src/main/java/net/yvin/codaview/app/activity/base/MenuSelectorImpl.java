package net.yvin.codaview.app.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.DailyActivity;
import net.yvin.codaview.app.activity.FaveDailyActivity;
import net.yvin.codaview.app.activity.MenuActivity;
import net.yvin.codaview.app.activity.utils.ActivityLuncher;
import net.yvin.codaview.app.repository.FaveDailyStorage;

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
                    FaveDailyStorage.faveDaily(((DailyActivity) activity).getDailyId());
                    Toast toast = Toast.makeText(activity, activity.getString(R.string.favoritize), Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    new ActivityLuncher(new Intent(activity, FaveDailyActivity.class), activity);
                }
                break;
            case R.id.action_delete:
                if(activity instanceof FaveDailyActivity) {
                    Toast.makeText(activity, activity.getString(R.string.click_item_to_delete), Toast.LENGTH_LONG).show();
                    FaveDailyStorage.setDelete(true);
                }
        }
    }
}
