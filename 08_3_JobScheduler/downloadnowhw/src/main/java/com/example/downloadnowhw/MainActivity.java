package com.example.downloadnowhw;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
In this homework, a button is used to start a download job

Additionally the download job is scheduled to perform once per day, when idle , connected to power and to wifi
OR itll download again if you tap the button again
 */

public class MainActivity extends AppCompatActivity {

    private static final int JOBID = 0;
    JobScheduler scheduler;
    NotificationManager notificationManager;
    private static final String PRIMARY_CHANNEL_ID = "primary_channel_id";
    private static final int notifyIntentRequestCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void scheduleJob(View view) {
        sendNotification();
        scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        // 24 hours converted to ms
        int interval = 24 * 60 * 60 * 1000;

        ComponentName serviceName = new ComponentName(getPackageName(), NotificationJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(JOBID, serviceName)
                .setRequiresCharging(true)
                .setRequiresDeviceIdle(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPeriodic(interval);

        JobInfo info = builder.build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (scheduler != null) {
                scheduler.schedule(info);
            }
        }
        Toast.makeText(this, "Download job scheduled", Toast.LENGTH_SHORT).show();

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel primaryChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Download job service notification", NotificationManager.IMPORTANCE_HIGH);

        primaryChannel.setDescription("Download notification");
        primaryChannel.setLightColor(Color.YELLOW);
        primaryChannel.enableVibration(true);
        primaryChannel.enableLights(true);

        notificationManager.createNotificationChannel(primaryChannel);
    }

    private void sendNotification() {

        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingNotifyIntent = PendingIntent.getActivity(this, notifyIntentRequestCode, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_download)
                .setContentIntent(pendingNotifyIntent)
                .setContentTitle("Job Service")
                .setContentText("Download in progress");

        notificationManager.notify(notifyIntentRequestCode, builder.build());

    }

    /*

to do a notification - create jobservice class
1. make method to create notification channel, setting its actions and stuff
2. create pending intent using notification compat to build it
3. use notifmanager . notify method to start
 */

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    class NotificationJobService extends JobService {

        @Override
        public boolean onStartJob(JobParameters jobParameters) {
            sendNotification();
// this backgroudn task is a download (normally) so youd usually do return true to use a background thread, however were just doing a notification so return false

            return false;
        }

        @Override
        public boolean onStopJob(JobParameters jobParameters) {

            return true;
        }


    }


}
