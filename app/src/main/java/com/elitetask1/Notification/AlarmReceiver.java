package com.elitetask1.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap  ;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.elitetask1.elitetask1.MainActivity;
import com.elitetask1.elitetask1.R;

import java.util.Calendar;

import static com.elitetask1.Notification.AppNotification.CHANNEL_1_ID;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bitmap picture = BitmapFactory.decodeResource(context.getResources(), R.drawable.india);

        Calendar now = Calendar.getInstance();
        int minute = now.get(Calendar.MINUTE);
        int hourofday = now.get(Calendar.HOUR_OF_DAY);
        if (minute == 23 && hourofday == 12) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context, CHANNEL_1_ID)
                            .setSmallIcon(R.drawable.ic_home_black_24dp)
                            .setContentTitle("Keep Coding - 2")
                            .setStyle(new NotificationCompat.BigPictureStyle()
                                    .bigPicture(picture)
                                    .bigLargeIcon(null))
                            .setLargeIcon(picture)
                            .setContentText("Keeep watching latest");
            Intent resultIntent = new Intent(context, MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
        }
    }
}
