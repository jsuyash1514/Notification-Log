package com.example.suyash.notificationlog;

import android.annotation.SuppressLint;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

/**
 * Created by suyash on 5/30/18.
 */

@SuppressLint("OverrideAbstract")
public class Notification extends NotificationListenerService {
    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification statusBarNotification){

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification statusBarNotification){

    }

}
