package com.example.suyash.notificationlog;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button,permission, clear;
    TextView textView;
    NotificationReceiver notificationReciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =(Button)findViewById(R.id.button);
        permission = (Button)findViewById(R.id.permissions);
        clear = (Button)findViewById(R.id.clear);
        textView = (TextView)findViewById(R.id.textView);

        notificationReciever = new NotificationReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.suyash.notificationlog.NOTIFICATION_SERVICE_EXAMPLE");
        registerReceiver(notificationReciever, intentFilter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification(getApplicationContext());
            }
        });
        permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS));
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(null);
            }
        });
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        unregisterReceiver(notificationReciever);
    }

    private void createNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("My Notification");
        builder.setContentText("Notification created.");
        builder.setTicker("Notification created.");
        builder.setSmallIcon(R.drawable.ic_android_black_24dp);
        builder.setAutoCancel(true);
        notificationManager.notify((int)System.currentTimeMillis(),builder.build());
    }

    class NotificationReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String string = intent.getStringExtra("extras") + textView.getText();
            textView.setText(string);
        }
    }

}
