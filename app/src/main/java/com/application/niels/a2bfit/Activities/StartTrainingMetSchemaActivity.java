package com.application.niels.a2bfit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StartTrainingMetSchemaActivity extends AppCompatActivity {

    TextView tvSchemaType;
    EditText etDatum;
    String dateFromDatePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_training_met_schema);

        tvSchemaType = (TextView) findViewById(R.id.tvSchemaType);
        etDatum = (EditText) findViewById(R.id.etDatum);
        etDatum.setFocusable(false);
        etDatum.setClickable(true);

        goToDatePicker();
        getDateFromDatePicker();
    }


    public void goToDatePicker(){
        etDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent datePickerIntent = new Intent(StartTrainingMetSchemaActivity.this, PickDateActivity.class);
                startActivity(datePickerIntent);
            }
        });
    }

    public void getDateFromDatePicker(){
        if (getIntent().hasExtra("datumVanOefening")){
            dateFromDatePicker = getIntent().getExtras().getString("datumVanOefening");
            etDatum.setText(dateFromDatePicker);
        }
    }
}
