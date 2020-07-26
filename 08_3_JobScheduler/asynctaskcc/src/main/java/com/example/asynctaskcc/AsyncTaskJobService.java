package com.example.asynctaskcc;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.arch.core.executor.TaskExecutor;


/*
Previsouly all my job services have just been to show a notification
This one will start an AsyncTask (when constraints are met)
The asynctask should sleep for 5 secs????
If the constraints stop being met while thread is sleeping, the job should be rescheduled and a toast show "job failed"

 asynctask has been deprecated, not gonna learn this anyway
its useful to leran that normally when you are scheduling jobs
the jobs are coplex, so you need to tell the scheduler when to stop the job
this is done by using the method "jobFinished"
jobfinishged takes 2 params, 1. the parameters uponwhich to stop (usually this is the jobParameters given to the scheduler class from a jobinfo bundle (see jobinfo builder stuff in mainacitivty)
                               2. boolean whether yo uwant to reschedule upon finishing job


 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class AsyncTaskJobService extends JobService {



    @Override
    public boolean onStartJob(JobParameters jobParameters) {


        return false;
    }

   // private class MyAsyncTask extends Asyn


    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        // return true cos you want it to reshedule in case of failure, not stop

        Toast.makeText(this,"Job failed",Toast.LENGTH_SHORT).show();
        return true;
    }


    }

}
