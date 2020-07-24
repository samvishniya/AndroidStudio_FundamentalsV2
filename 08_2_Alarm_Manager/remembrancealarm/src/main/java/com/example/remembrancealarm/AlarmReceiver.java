package com.example.remembrancealarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private NotificationManager notificationManager;

    // member constants for the notif id and notif channel id
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";


    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        deliverNotification(context);
    }

    // this is the notification (the alarmreceiver class combines the notification with the alarm element to create a alarm notification
    private void deliverNotification(Context context) {
        Intent contentIntent = new Intent(context,MainActivity.class);

        /*
         PendingIntent flags tell the system how to handle the situation when multiple instances of the same PendingIntent are created (meaning that the instances contain the same Intent).
         The FLAG_UPDATE_CURRENT flag tells the system to use the old Intent but replace the extras data. Because you don't have any extras in this Intent, you can use the same PendingIntent over and over.
         */
        PendingIntent contentPendingIntent = PendingIntent.getActivity(context,NOTIFICATION_ID,contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_remember)
                .setContentTitle("Remember Alarm")
                .setContentText("Please Lead the remembrance now with silence")
                // notice the next line REQUIRES a pendingintent a normal intent wont do
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        // finally after all that setting up, deliver the notification using notifmanager
        notificationManager.notify(NOTIFICATION_ID,builder.build());

    }
}
