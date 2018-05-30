package com.example.suyash.notificationlog;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import android.util.Log;


/**
 * Created by suyash on 5/30/18.
 */

@SuppressLint("OverrideAbstract")
public class NotificationService extends NotificationListenerService {

    private NotificationServiceReceiver notificationServiceReceiver;
    @Override
    public void onCreate(){
        super.onCreate();
        notificationServiceReceiver = new NotificationServiceReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.suyash.notificationlog.NOTIFICATION_SERVICE_EXAMPLE");
        registerReceiver(notificationServiceReceiver, intentFilter);
        Log.d("onNotificationService:", "Broadcast receiver registered");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(notificationServiceReceiver);
    }
    @Override
    public IBinder onBind(Intent intent){
        return super.onBind(intent);
    }
    @Override
    public void onNotificationPosted(StatusBarNotification statusBarNotification){
        Log.d("onNotificationPosted", "New notification posted");
        Log.d("onNotificationPosted","ID :" + statusBarNotification.getId() + "\t" + statusBarNotification.getNotification().tickerText + "\t" + statusBarNotification.getPackageName() + "\t" );
        Log.d("onNotificationPosted",   "\n" +  statusBarNotification.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT).toString()  + "\t" + statusBarNotification.getNotification().extras.getCharSequence(Notification.EXTRA_TITLE).toString());
        Intent intent = new  Intent("com.example.suyash.notificationlog.NOTIFICATION_SERVICE_EXAMPLE");
        intent.putExtra("extras","New Notification:\t" + statusBarNotification.getPackageName() + "\n" + statusBarNotification.getNotification().extras.getCharSequence(Notification.EXTRA_TITLE).toString() + ": \t" + statusBarNotification.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT).toString() +"\n" + "\n\n\n");
        sendBroadcast(intent);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification statusBarNotification){

    }

    class NotificationServiceReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }

}


