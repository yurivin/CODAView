package net.yvin.codaview.app.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.activity.feelingsDiary.FeelingsDiaryActivity;
import net.yvin.codaview.app.activity.utils.ActivityLuncher;
import net.yvin.codaview.app.activity.utils.StorageChecker;
import net.yvin.codaview.app.utils.Constants;

import static net.yvin.codaview.app.utils.Constants.*;

/**
 * Created by Yuriy on 24.04.2014.
 */
public class MenuActivity extends MenuAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void clickBtnCalendar(View v) {
        new ActivityLuncher(new Intent(this, CalendarActivity.class), this);
    }

    public void clickBtnSteps(View v) {
        Intent intent = new Intent(this, TwelveActivity.class);
        intent.putExtra(Constants.TWELVE, STEPS);
        new ActivityLuncher(intent, this);
    }

    public void clickBtnTraditions(View v) {
        Intent intent = new Intent(this, TwelveActivity.class);
        intent.putExtra(Constants.TWELVE, TRADITIONS);
        new ActivityLuncher(intent, this);
    }

    public void clickBtnPromises(View v) {
        Intent intent = new Intent(this, TwelveActivity.class);
        intent.putExtra(Constants.TWELVE, PROMISES);
        new ActivityLuncher(intent, this);
    }

    public void clickBtnPrayer(View v) {
        new ActivityLuncher(new Intent(this, PrayerActivity.class), this);
    }

    public void clickBtnWayBeginning(View v) {
        new ActivityLuncher(new Intent(this, WayBiginingActivity.class), this);
    }

    public void clickBtnFillingsDiary(View v) {
        if (new StorageChecker().isExternalStorageAvailable()) {
            new ActivityLuncher(new Intent(this, FeelingsDiaryActivity.class), this);
        } else {
            showAlertNoStorage();
        }
    }

    public void clickBtnFillingsNewEntry(View v) {
        if (new StorageChecker().isExternalStorageAvailable()) {
            new ActivityLuncher(new Intent(this, NewFeelingsDiaryEntryActivity.class), this);
        } else {
            showAlertNoStorage();
        }
    }

    public void clickBtnHowToHelp(View v) {
        new ActivityLuncher(new Intent(this, HowToHelpActivity.class), this);
    }

    public void clickBtnDaily(View v){
        new ActivityLuncher(new Intent(this, DailyActivity.class), this);
    }

    private void showAlertNoStorage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
