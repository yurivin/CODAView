package net.yvin.codaview.app.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;

import java.util.*;

/**
 * Created by Юрий on 27.07.2014.
 */
public class NewFeelingsDiaryEntryActivity extends MenuAbstractActivity {

    int yearG, monthOfYearG, dayOfMonthG, hourOfDayG, minuteG;
    List<String> feelingsG = new ArrayList<String>();
    Button addFeelings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelingsdiaryentry);
        addFeelings = (Button)findViewById(R.id.btnFeelings);
    }

    public void clickBtnTimeFrom(View v) {
        setDateTime();
    }

    public void clickBtnTimeTo(View v) {
        setDateTime();
    }

    public void clickBtnFeelings(View v) {

        List<String> feelingsList = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.afraid_feelings)));
        feelingsList.addAll(Arrays.asList(getResources().getStringArray(R.array.angry_feelings)));
        feelingsList.addAll(Arrays.asList(getResources().getStringArray(R.array.happy_feelings)));
        feelingsList.addAll(Arrays.asList(getResources().getStringArray(R.array.love_feelings)));
        feelingsList.addAll(Arrays.asList(getResources().getStringArray(R.array.other_feelings)));
        Collections.sort(feelingsList);

        final String[] feelingsArray = new String[feelingsList.size()];
        for(int i = 0; i < feelingsList.size(); i++)
            feelingsArray[i] = feelingsList.get(i);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.clickable_text_list_item, feelingsArray);

        new AlertDialog.Builder(this)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Dialog", "Item selected: " + feelingsArray[which]);
                        feelingsG.add(feelingsArray[which]);
                        StringBuilder builder = new StringBuilder();
                        addFeelings.setTextSize(14);
                        builder.append(feelingsArray[which]);
                        builder.append(", ");
                        builder.append(addFeelings.getText());
                        addFeelings.setText(builder.toString());
                    }
                })
                .setTitle("Add feeling")
                .setCancelable(true)
                .show();


    }

    public void clickBtnAddComment(View v) {

    }

    private void setDateTime() {
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
                        monthOfYearG = monthOfYear + 1;
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
