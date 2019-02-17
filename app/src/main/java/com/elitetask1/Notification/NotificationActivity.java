package com.elitetask1.Notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.elitetask1.elitetask1.MainActivity;
import com.elitetask1.elitetask1.R;

import java.util.Calendar;
import java.util.Locale;

import static com.elitetask1.Notification.AppNotification.CHANNEL_1_ID;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //Alarm Service
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5000, pendingIntent);

        Calendar cal = Calendar.getInstance();
        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.india);
        int minute = cal.get(Calendar.MINUTE);
        int hourofday = cal.get(Calendar.HOUR_OF_DAY);
        if (minute == 23 && hourofday == 12) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this, CHANNEL_1_ID)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Keep Coding - 1")
                            .setLargeIcon(picture)
                            .setColor(Color.BLUE)
                            .setContentText("Stay with us");
            Intent resultIntent = new Intent(this, MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
        }
    }
}

