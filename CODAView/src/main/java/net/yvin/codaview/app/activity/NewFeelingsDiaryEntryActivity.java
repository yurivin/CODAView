package net.yvin.codaview.app.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;

import java.util.Calendar;

/**
 * Created by Юрий on 27.07.2014.
 */
public class NewFeelingsDiaryEntryActivity extends MenuAbstractActivity {

    int yearG, monthOfYearG, dayOfMonthG, hourOfDayG, minuteG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelingsdiaryentry);
    }

    public void clickBtnTimeFrom(View v) {
        setdateTime();
    }

    public void clickBtnTimeTo(View v) {
        setdateTime();
    }

    public void clickBtnFeelings(View v) {

    }

    public void clickBtnAddComment(View v) {

    }

    private void setdateTime() {
        final Calendar c = Calendar.getInstance();
        showTimeDialog(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
        showDateDialog(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }


    private void showDateDialog(int mYear, int mMonth, int mDay) {
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Log.d("Date from dialog", dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);
                        dayOfMonthG = dayOfMonth;
                        monthOfYearG = monthOfYear;
                        yearG = year;
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    private void showTimeDialog(int mHour, int mMinute) {
        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        Log.d("Time from dialog", hourOfDay + ":" + minute);
                        hourOfDayG = hourOfDay;
                        minuteG = minute;
                    }
                }, mHour, mMinute, true);
        tpd.show();
    }
}
