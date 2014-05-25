package net.yvin.codaview.app.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import net.yvin.codaview.app.R;
import net.yvin.codaview.app.activity.DailyActivity;

import java.util.HashMap;

/**
 * Created by Юрий on 25.05.2014.
 */
public class NotificationUtils {

    private static final String TAG = NotificationUtils.class.getSimpleName();

    private static NotificationUtils instance;

    private static Context context;
    private NotificationManager manager; // Системная утилита, упарляющая уведомлениями
    private int lastId = 0; //постоянно увеличивающееся поле, уникальный номер каждого уведомления
    private HashMap<Integer, Notification> notifications; //массив ключ-значение на все отображаемые пользователю уведомления


    //приватный контструктор для Singleton
    private NotificationUtils(Context context) {
        this.context = context;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifications = new HashMap<Integer, Notification>();
    }

    /**
     * Получение ссылки на синглтон
     */
    public static NotificationUtils getInstance(Context context) {
        if (instance == null) {
            instance = new NotificationUtils(context);
        } else {
            instance.context = context;
        }
        return instance;
    }

    public int createReadDailyNotification(){
        Intent notificationIntent = new Intent(context, DailyActivity.class);
        Notification.Builder nBuilder =  new Notification.Builder(context)
                .setSmallIcon(R.drawable.coda)
                .setContentTitle(context.getString(R.string.CODA))
                .setContentText(context.getString(R.string.read_daily_notification))
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT))
                .setTicker(context.getString(R.string.read_daily))
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL);
        Notification notification = nBuilder.build();
        manager.notify(lastId, notification);
        notifications.put(lastId, notification); //теперь мы можем обращаться к нему по id
        return lastId++;
    }

}
