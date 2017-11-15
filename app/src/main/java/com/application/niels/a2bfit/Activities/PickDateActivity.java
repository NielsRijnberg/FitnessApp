package com.application.niels.a2bfit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.application.niels.a2bfit.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PickDateActivity extends AppCompatActivity {

    DatePicker datePicker;
    Button btnKiesDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_date);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        btnKiesDatum = (Button) findViewById(R.id.btnKiesDatum);

        sendDate();
    }

    public static String getDate(DatePicker datepicker){
        int day = datepicker.getDayOfMonth();
        int month = datepicker.getMonth() + 1;
        int year = datepicker.getYear();

        String date = ""+day + "-" + month + "-" + year;

        return date;
    }

    public void sendDate(){
        btnKiesDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateString = getDate(datePicker);
                //String datumVanOefening = getDate(datePicker).toString();
                Intent passDateIntent = new Intent(PickDateActivity.this, StartTrainingMetSchemaActivity.class);
                passDateIntent.putExtra("datumVanOefening", dateString);
                startActivity(passDateIntent);
            }
        });
    }
}
