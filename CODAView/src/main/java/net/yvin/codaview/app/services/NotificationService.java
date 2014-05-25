package net.yvin.codaview.app.services;

import android.app.*;
import android.content.Intent;
import android.os.IBinder;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.DailyActivity;
import net.yvin.codaview.app.utils.NotificationUtils;

import java.util.concurrent.TimeUnit;

public class NotificationService extends Service {


    public NotificationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            TimeUnit.HOURS.sleep(4);
            sendNotification();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void sendNotification() {
        NotificationUtils nUtils = NotificationUtils.getInstance(this);
        nUtils.createReadDailyNotification();
    }
}
