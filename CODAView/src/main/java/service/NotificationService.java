package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Юрий on 12.05.2014.
 */
public class NotificationService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
