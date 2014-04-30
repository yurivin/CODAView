package net.yvin.codaview.app.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;
import net.yvin.codaview.app.utils.DateUtils;

import java.lang.reflect.Field;

public class CalendarActivity extends MenuAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        setMonthTitleColor();
        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view,
                                            int year, int month, int dayOfMonth) {
                Intent intent = new Intent(getApplicationContext(), DailyActivity.class);
                intent.putExtra("dailyId", DateUtils.CalendarToDailyId(year, month, dayOfMonth));
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(),
                        dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
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
