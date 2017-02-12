package com.example.jacktownsend.marchon;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.jacktownsend.marchon.api.ApiErrorException;
import com.example.jacktownsend.marchon.api.ApiInterface;
import com.example.jacktownsend.marchon.api.Notification;
import com.example.jacktownsend.marchon.participant.MarchViewActivity;
import com.example.jacktownsend.marchon.participant.NotificationsListActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificationPollerService extends Service {


    public NotificationPollerService() {
    }

    public final static List<Notification> currentList = new ArrayList<>();

    private final static Object lock = new Object();

    String apiString;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        apiString = intent.getStringExtra("api");
        final int march_id = intent.getIntExtra("march_id", -1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                ApiInterface api = new ApiInterface(apiString);
                while (true) {
                    try {
                        ArrayList<Notification> old = new ArrayList<Notification>(currentList);
                        synchronized (lock) {
                            currentList.clear();
                            List<Notification> nn = api.getNotifications(march_id);
                            if (nn != null)
                                currentList.addAll(api.getNotifications(march_id));
                        }

                        for (int i = old.size(); i < currentList.size(); i++) {
                            makeNotification(currentList.get(i));
                        }

                        Thread.currentThread().sleep(1000 * 15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ApiErrorException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        }).start();
        return START_REDELIVER_INTENT;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    public void makeNotification(Notification notification) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.small)
                        .setContentTitle(notification.title)
                        .setContentText(notification.description);
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, NotificationsListActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MarchViewActivity.class);
//// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(12455, mBuilder.build()); // int is arbitrary
    }
}
