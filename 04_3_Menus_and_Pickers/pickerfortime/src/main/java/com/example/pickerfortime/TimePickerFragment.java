package com.example.pickerfortime;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public TimePickerFragment() {
        // Required empty public constructor
    }


    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    // use current time as the default time in the picker
        final Calendar c = Calendar.getInstance();

       int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
    // create a new instance of timepickerdialog and return it
        return new TimePickerDialog(getActivity(),this,hour,minute,true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        // getactivity )when used in a frag) returns the activty the fragment is associated with
        // the alternative would require an intent
        MainActivity activity = (MainActivity) getActivity();
        activity.processTimePickerResult(hourOfDay,minute);

    }
}
