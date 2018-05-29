package com.example.suyash.notificationlog;

import android.app.NotificationManager;
import android.content.Context;
import android.service.notification.NotificationListenerService;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =(Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification(getApplicationContext());
            }
        });
    }

    private void createNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("My Notification");
        builder.setContentText("Notification created.");
        builder.setSmallIcon(R.drawable.ic_android_black_24dp);
        notificationManager.notify((int)System.currentTimeMillis(),builder.build());
    }

}
