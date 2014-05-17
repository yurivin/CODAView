package net.yvin.codaview.app.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.MenuActivity;

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

    public void select() {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_menu:
                intent = new Intent(activity, MenuActivity.class);
                activity.startActivity(intent);
                activity.finish();
                break;
        }
    }

}
