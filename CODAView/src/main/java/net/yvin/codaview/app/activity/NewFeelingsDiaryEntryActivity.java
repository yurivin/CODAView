package net.yvin.codaview.app.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.utils.AssetsWriter;
import net.yvin.codaview.app.utils.FeelingDiaryReader;

import java.util.*;

/**
 * Created by Юрий on 27.07.2014.
 */
public class NewFeelingsDiaryEntryActivity extends MenuAbstractActivity implements RatingBar.OnRatingBarChangeListener {

    int yearFromG, monthOfYearFromG, dayOfMonthFromG, hourOfDayFromG, minuteFromG,
            yearToG, monthOfYearToG, dayOfMonthToG, hourOfDayToG, minuteToG;
    Button btnAddFeelings, btnTimeFrom, btnTimeTo, btnComment, btnReady;
    RatingBar ratingBar, intensityBar;
    float feelingsRating, feelingsIntensity;
    final String DATE_SEPARATOR = "-";
    final String TIME_SEPARATOR = ":";
    boolean[] mCheckedItems;
    List<String> selectedFeelings;
    String comment;
    AssetsWriter assetsWriter = new AssetsWriter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelingsdiaryentry);
        btnAddFeelings = (Button) findViewById(R.id.btnFeelings);
        ratingBar = (RatingBar) findViewById(R.id.feelingsRating);// create RatingBar object
        ratingBar.setOnRatingBarChangeListener(this);
        intensityBar = (RatingBar) findViewById(R.id.feelingsIntensity);// create RatingBar object
        intensityBar.setOnRatingBarChangeListener(this);
        btnTimeFrom = (Button) findViewById(R.id.feelTimeFrom);
        btnTimeTo = (Button) findViewById(R.id.feelTimeTo);
        btnComment = (Button) findViewById(R.id.btnComment);
        btnReady = (Button) findViewById(R.id.btnReady);
    }

    public void clickBtnTimeFrom(View v) {
        setDateTime(btnTimeFrom);
    }

    public void clickBtnTimeTo(View v) {
        setDateTime(btnTimeTo);
    }

    public void clickBtnAddComment(View v) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        alertDialog.setTitle(R.string.add_comment);
        final EditText input = new EditText(this);
        input.setBackgroundColor(getResources().getColor(R.color.editText));
        if (comment != null) input.setText(comment);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input)
                .setCancelable(true)
                .setPositiveButton(R.string.ready,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                comment = input.getText().toString();
                                Log.d("Comment text: ", comment);
                                btnComment.setTextSize(14);
                                btnComment.setText(comment);
                            }
                        })
                .show();
    }

    public void clickBtnReady(View v) {
        assetsWriter.feelingsDiary(String.valueOf(yearFromG), String.valueOf(monthOfYearFromG), String.valueOf(dayOfMonthFromG), String.valueOf(hourOfDayFromG), String.valueOf(minuteFromG),
                String.valueOf(yearToG), String.valueOf(monthOfYearToG), String.valueOf(dayOfMonthToG), String.valueOf(hourOfDayToG), String.valueOf(minuteToG), String.valueOf(feelingsIntensity),
                String.valueOf(feelingsRating), selectedFeelings.toString(), comment);
    }

    public void clickBtnFeelings(View v) {

        List<String> feelingsList = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.afraid_feelings)));
        feelingsList.addAll(Arrays.asList(getResources().getStringArray(R.array.angry_feelings)));
        feelingsList.addAll(Arrays.asList(getResources().getStringArray(R.array.happy_feelings)));
        feelingsList.addAll(Arrays.asList(getResources().getStringArray(R.array.love_feelings)));
        feelingsList.addAll(Arrays.asList(getResources().getStringArray(R.array.other_feelings)));
        Collections.sort(feelingsList);

        final String[] feelingsArray = new String[feelingsList.size()];
        for (int i = 0; i < feelingsList.size(); i++)
            feelingsArray[i] = feelingsList.get(i);

        mCheckedItems = new boolean[feelingsArray.length];

        new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                .setMultiChoiceItems(feelingsArray, mCheckedItems,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                mCheckedItems[which] = isChecked;
                            }
                        })
                .setPositiveButton(R.string.ready,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                selectedFeelings = new ArrayList<>();
                                StringBuilder builder = new StringBuilder();
                                btnAddFeelings.setTextSize(14);
                                btnAddFeelings.setText(R.string.add_filling);
                                for (int i = 0; i < feelingsArray.length; i++) {

                                    if (mCheckedItems[i]) {
                                        selectedFeelings.add(feelingsArray[i]);
                                        builder.append(feelingsArray[i]);
                                        builder.append(", ");
                                    }
                                }
                                btnAddFeelings.setText(builder.toString());
                                Log.d("Selected feelings", builder.toString());
                            }
                        })
                .setTitle(R.string.add_filling)
                .setCancelable(true)
                .show();
    }

    private void setDateTime(Button btnTime) {
        final Calendar c = Calendar.getInstance();
        showTimeDialog(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), btnTime);
        showDateDialog(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), btnTime);
    }


    private void showDateDialog(final int mYear, int mMonth, int mDay, final Button btnTime) {
        DatePickerDialog dpd = new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Log.d("Date from dialog", dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);
                        if (btnTime.equals(btnTimeFrom)) {
                            yearFromG = year;
                            monthOfYearFromG = monthOfYear + 1;
                            dayOfMonthFromG = dayOfMonth;
                            btnTime.setText(dayOfMonthFromG + DATE_SEPARATOR + monthOfYearFromG + DATE_SEPARATOR + yearFromG + "   ");
                        } else if (btnTime.equals(btnTimeTo)) {
                            yearToG = year;
                            monthOfYearToG = monthOfYear + 1;
                            dayOfMonthToG = dayOfMonth;
                            btnTime.setText(dayOfMonthToG + DATE_SEPARATOR + monthOfYearToG + DATE_SEPARATOR + yearToG + "   ");
                        }
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    private void showTimeDialog(int mHour, int mMinute, final Button btnTime) {
        TimePickerDialog tpd = new TimePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK,
                new TimePickerDialog.OnTimeSetListener() {
                    boolean realSetup = false;

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        Log.d("Time from dialog", hourOfDay + ":" + minute);
                        if (btnTime.equals(btnTimeFrom) && realSetup == true) {
                            hourOfDayFromG = hourOfDay;
                            minuteFromG = minute;
                            btnTime.setText(btnTime.getText() + String.valueOf(hourOfDayFromG) + TIME_SEPARATOR + String.valueOf(minuteFromG));
                        } else if (btnTime.equals(btnTimeTo) && realSetup == true) {
                            hourOfDayToG = hourOfDay;
                            minuteToG = minute;
                            btnTime.setText(btnTime.getText() + String.valueOf(hourOfDayToG) + TIME_SEPARATOR + String.valueOf(minuteToG));
                        }
                        realSetup = true;
                    }
                }, mHour, mMinute, true);
        tpd.show();
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        switch(ratingBar.getId()) {
            case R.id.feelingsRating :
                feelingsRating = ratingBar.getRating();
            case R.id.feelingsIntensity :
                feelingsIntensity = ratingBar.getRating();
        }

    }
}
