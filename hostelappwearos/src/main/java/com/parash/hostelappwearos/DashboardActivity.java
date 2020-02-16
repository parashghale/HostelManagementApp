package com.parash.hostelappwearos;

import android.app.Notification;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class DashboardActivity extends WearableActivity {

    private TextView mTextView;
    NotificationManagerCompat notificationCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        notificationCompat = NotificationManagerCompat.from(this);        mTextView = (TextView) findViewById(R.id.text);

        mTextView.setText("This is Dashboard");
        DisplayNotification();
        setAmbientEnabled();
    }

    private void DisplayNotification( ) {

        Notification notification = new NotificationCompat.Builder(getApplicationContext(), CreateChannel.CHANNEL_1)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Welcome to hostel app ").setContentText("You will find all the things u need here")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationCompat.notify(1,notification);
    }
}
