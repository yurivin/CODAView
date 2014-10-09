package net.yvin.codaview.app.activity.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.DailyActivity;
import net.yvin.codaview.app.activity.FaveDailyActivity;
import net.yvin.codaview.app.activity.MenuActivity;
import net.yvin.codaview.app.activity.utils.ActivityLuncher;
import net.yvin.codaview.app.activity.utils.StorageChecker;
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
                if (!new StorageChecker().isExternalStorageAvailable()) {
                    showAlertNoStorage();
                    break;
                }
                if (activity instanceof DailyActivity) {
                    FaveDailyStorage.faveDaily(((DailyActivity) activity).getDailyId());
                    Toast toast = Toast.makeText(activity, activity.getString(R.string.favoritize), Toast.LENGTH_LONG);
                    TextView text = (TextView) toast.getView().findViewById(android.R.id.message);
                    text.setTextColor(Color.parseColor("#ffff7105"));
                    text.setTextSize(16);
                    toast.show();
                } else {
                    new ActivityLuncher(new Intent(activity, FaveDailyActivity.class), activity);
                }
                break;
            case R.id.action_delete:
                if (activity instanceof FaveDailyActivity) {
                    Toast toast = Toast.makeText(activity, activity.getString(R.string.click_item_to_delete), Toast.LENGTH_LONG);
                    TextView text = (TextView) toast.getView().findViewById(android.R.id.message);
                    text.setTextColor(Color.parseColor("#ffff7105"));
                    text.setTextSize(16);
                    toast.show();
                    FaveDailyStorage.setDelete(true);
                }
        }
    }

    private void showAlertNoStorage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.alert)
                .setMessage(R.string.exstorage_notavailable)
                .setNegativeButton("ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
