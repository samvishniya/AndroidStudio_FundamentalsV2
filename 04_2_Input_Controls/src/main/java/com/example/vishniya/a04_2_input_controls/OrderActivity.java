package com.example.vishniya.a04_2_Input_Controls;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vishniya.a04_2_Input_Controls.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String orderMessage;
    private TextView orderHeaderTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        Intent intent = getIntent();

        orderMessage = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        orderHeaderTextView = findViewById(R.id.order_message_textview);

        orderHeaderTextView.setText(orderMessage);

        Spinner labelSpinner = findViewById(R.id.label_spinner);
        if (labelSpinner!= null ){
            labelSpinner.setOnItemSelectedListener(this);
        }
      ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.labels_array , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
if(labelSpinner != null){
    labelSpinner.setAdapter(adapter);
}


    }


    public void onRadioButtonClicked(View view) {
        // is the button now checked? this turns true if the button is selected
        boolean checked = ((RadioButton) view).isChecked();
        // check which button was clicked
/*
           if (view.getId()== R.id.sameday) {
    if(checked)
        displayToast(getString(R.string.same_day_messenger_service));
        }
*/

// a switch comparing the clicked view's id against id of the radio buttons, to see what message to put
        switch (view.getId()) {
            case R.id.sameday:
                if (checked)
                    // Same day service
                    displayToast(getString(R.string.same_day_messenger_service));
                break;
            case R.id.nextday:
                if (checked)
                    // Next day delivery
                    displayToast(getString(R.string.next_day_ground_delivery));
                break;
            case R.id.pickup:
                if (checked)
                    // Pick up
                    displayToast(getString(R.string.pick_up));
                break;
            default:
                // Do nothing.
                break;
        }


    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }


    // these are for the spinner, coding if item is selected#
    // we retrieve the users selected item using getitematposition, then assign it to spinnerlabel (also displaying atoast)
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
String spinnerLabel=adapterView.getItemAtPosition(position).toString();
displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
