package net.yvin.codaview.app.activity.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Юрий on 24.05.2014.
 */
public class ActivityLuncher {

    public ActivityLuncher(Intent intent, Activity context) {
        lunchActivity(intent, context);
    }

    private void lunchActivity(Intent intent, Activity context) {
        context.startActivity(intent);
//        context.finish();
    }
}
