package net.yvin.codaview.app.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.base.MenuAbstractActivity;

import java.lang.reflect.Field;

public class CalendarActivity extends MenuAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        setMonthTitleColor();
    }

    protected void setMonthTitleColor() {
        try
        {
            CalendarView cv = (CalendarView) this.findViewById(R.id.calendarView1);
            Class<?> cvClass = cv.getClass();
            Field field = cvClass.getDeclaredField("mMonthName");
            field.setAccessible(true);

            try
            {
                TextView tv = (TextView) field.get(cv);
                tv.setTextColor(Color.RED);
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
    }
}
