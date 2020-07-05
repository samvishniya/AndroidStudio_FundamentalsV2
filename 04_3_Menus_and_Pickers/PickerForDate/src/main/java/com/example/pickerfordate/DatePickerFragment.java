package com.example.pickerfordate;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Fragment for DatePicker
 *
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {



    // use onCreateDialog instead of onCreatView
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
  // use current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // create a new instance of datepickerdialog and return it;
        return new DatePickerDialog(getActivity(),this,year,month,day);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        // getactivity )when used in a frag) returns the activty the fragment is associated with
        // the alternative would require an intent
    MainActivity activity = (MainActivity) getActivity();
    activity.processDatePickerResult(year,month,day);
    }
}
