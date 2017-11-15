package com.application.niels.a2bfit.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StartTrainingMetSchemaActivity extends AppCompatActivity {

    TextView tvSchemaType;
    EditText etDatum;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_training_met_schema);

        tvSchemaType = (TextView) findViewById(R.id.tvSchemaType);
        etDatum = (EditText) findViewById(R.id.etDatum);
        etDatum.setFocusable(false);
        etDatum.setClickable(true);
        calendar = Calendar.getInstance();

        goToDatePicker();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    public void goToDatePicker(){
        etDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(StartTrainingMetSchemaActivity.this,R.style.DatePickerTheme, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void updateLabel(){
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMANY);

        etDatum.setText(sdf.format(calendar.getTime()));
    }
}
