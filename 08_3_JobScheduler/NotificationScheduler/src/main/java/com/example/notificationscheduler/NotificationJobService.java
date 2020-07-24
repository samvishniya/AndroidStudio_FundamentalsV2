package com.example.notificationscheduler;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

/*
A lot of scheduled jobs are long running tastks and you must offload onto a diferent thread (do this by returning a true boolean fron onStartJob

        However this app is just a notoification so we can safely use the main thread (return false from onStartJob)
*/
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

// only works with api 21+
// need to register this class in the manifest with the below
// <service
//   android:name=".NotificationJobService"
//   android:permission="android.permission.BIND_JOB_SERVICE"/>
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NotificationJobService extends JobService {


    NotificationManager notificationManager;
    private static final String PRIMARY_CHANNEL_ID="primary_notification_channel";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        createNotificationChannel();

        Intent notifyIntent = new Intent(this,MainActivity.class);

        PendingIntent contentPendingIntent = PendingIntent.getActivity(this,0,notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(this,PRIMARY_CHANNEL_ID)
                .setContentTitle("Job Service")
                .setContentText("Your job is running!")
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.ic_job_running)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        notificationManager.notify(0, builder.build());
        return false;
    }

    // make sure this returns true, because if the job fails you still want the job to be rescheduled not dropped
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }


    public void createNotificationChannel() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // notification channels only in api 26+ so check
        if(Build.VERSION.SDK_INT>=26) {

            // create notif channel
            NotificationChannel primaryChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,"Job Service notification", NotificationManager.IMPORTANCE_HIGH);


            primaryChannel.enableLights(true);
            primaryChannel.setLightColor(Color.RED);
            primaryChannel.enableVibration(true);
            primaryChannel.setDescription
                    ("Notifications from Job Service");

            notificationManager.createNotificationChannel(primaryChannel);

        }

    }

}
