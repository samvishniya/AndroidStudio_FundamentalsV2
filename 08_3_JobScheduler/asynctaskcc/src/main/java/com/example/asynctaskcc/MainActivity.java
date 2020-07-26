package com.example.asynctaskcc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private JobScheduler scheduler;
    private static final int JOB_ID = 0;
    private Switch deviceIdleSwitch;
    private Switch deviceChargingSwitch;
    private SeekBar seekBar;

    // why switchers initializxe in oncreate, and radiobuttons in schedulejob?
    // tested in both methods, seems fine
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        // this is a dynamic label that uses some value from the seekbar to display how many seconds  the user has set for the deadline
        final TextView seekBarProgressLabel = findViewById(R.id.seekBarProgress);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentValue, boolean b) {
                // if the seekbar value has been changed i.e its over 0, display it in the seekbarprogreslabel
                if (currentValue > 0) {
                    // s for seconds
                    seekBarProgressLabel.setText(currentValue + " s");
                } else {
                    seekBarProgressLabel.setText(R.string.label_not_set);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void scheduleJob(View view) {


        deviceIdleSwitch = findViewById(R.id.deviceIdleSwitch);
        deviceChargingSwitch = findViewById(R.id.deviceChargingSwitch);

        // intialize scheduler
        scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);


        // related to seekbar: the override deadline should only be set if the int value of seekbar >0
        // so we need to create an int to store the seek bars progress, and we will create aboolean to determine if int value >0
        // we actually call the overridedeadline (or not depending on boolean) after the builder ->below
        int seekBarInteger = seekBar.getProgress();
        boolean seekBarSet = seekBarInteger > 0;


        RadioGroup networkOptions = findViewById(R.id.networkOptions);
        int selectedNetworkID = networkOptions.getCheckedRadioButtonId();
        // create integer variable for the selected network option set to default aka none
        int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
        // then use switch statemtnt for each possible IDs , assigning the correct network option
        // in the default case itll just use the above default aka NONE
        switch (selectedNetworkID) {

            case R.id.anyNetworkRadioButton:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.wifiNetworkRadioButton:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
        }
        // now build a job schedule
        // initalize with a jobid and a componentName
        // note that the componentName uses the name of your jobservice class
        ComponentName serviceName = new ComponentName(getPackageName(), AsyncTaskJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName);
        // build using the builder type
        // setting required network type according to the previous switch-statement result
        // add on to this the constraints based on the switchbuttons (unlreated to the switch statement)
        builder.setRequiredNetworkType(selectedNetworkOption)
                .setRequiresDeviceIdle(deviceIdleSwitch.isChecked())
                .setRequiresCharging(deviceChargingSwitch.isChecked());

        // checking if need to add override deadline here
        if (seekBarSet) {
            builder.setOverrideDeadline(seekBarInteger * 1000);
        }


        // this constraintset ensures a job is only schedlued if ALL paramaters are met, e.g. must be charging AND wifi
        // it uses OR gates and a boolean
        // if any of these are set , constraintset is true and the builder will do something
        boolean constraintSet = (selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE) || deviceChargingSwitch.isChecked() || deviceIdleSwitch.isChecked() || seekBarSet;

        if (constraintSet) {


            // then schedule() the jobscheduler object & use build() to pass in the jobinfo object

            JobInfo myJobInfo = builder.build();
            scheduler.schedule(myJobInfo);
            Toast.makeText(this, "Job Scheduled, job will run when " +
                    "the constraints are met.", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(this, "Please set at least one constraint",
                    Toast.LENGTH_SHORT).show();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancelJobs(View view) {

        if (scheduler != null) {
            scheduler.cancelAll();
            scheduler = null;
            Toast.makeText(this, "All jobs cancelled", Toast.LENGTH_SHORT).show();
        }
    }


}
