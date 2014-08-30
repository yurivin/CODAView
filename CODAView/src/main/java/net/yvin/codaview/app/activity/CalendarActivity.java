package net.yvin.codaview.app.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.activity.utils.ActivityLuncher;
import net.yvin.codaview.app.utils.Constants;
import net.yvin.codaview.app.utils.DateUtils;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends MenuAbstractActivity {
    Long date;
    CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        setMonthTitleColor();
        calendar = (CalendarView) findViewById(R.id.calendarView);
        date = calendar.getDate();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view,
                                            int year, int month, int dayOfMonth) {
                //We need this check because without if we try to scroll calendar it loads current date
                if(view.getDate() != date) {
                    Intent intent = new Intent(getApplicationContext(), DailyActivity.class);
                    intent.putExtra(Constants.DAILYID, DateUtils.CalendarToDailyId(month, dayOfMonth));
                    intent.putExtra(Constants.MONTH, month);
                    intent.putExtra(Constants.DAY_OF_MONTH, dayOfMonth);
                    Log.d("Calendar date ", DateUtils.CalendarToDailyId(month, dayOfMonth));
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void clickBtnOk(View v) {
        Calendar calendarDay = Calendar.getInstance();
        calendarDay.setTime(new Date(calendar.getDate()));
        Intent intent = new Intent(getApplicationContext(), DailyActivity.class);
        intent.putExtra("dailyId", DateUtils.CalendarToDailyId(calendarDay.get(Calendar.MONTH), calendarDay.get(Calendar.DAY_OF_MONTH)));
        Log.d("Calendar date ", DateUtils.CalendarToDailyId(calendarDay.get(Calendar.MONTH), calendarDay.get(Calendar.DAY_OF_MONTH)));
        new ActivityLuncher(intent, this);
    }

    private void setMonthTitleColor() {
        try {
            CalendarView cv = (CalendarView) this.findViewById(R.id.calendarView);
            Class<?> cvClass = cv.getClass();
            Field field = cvClass.getDeclaredField("mMonthName");
            field.setAccessible(true);

            try {
                TextView tv = (TextView) field.get(cv);
                tv.setTextColor(Color.RED);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
