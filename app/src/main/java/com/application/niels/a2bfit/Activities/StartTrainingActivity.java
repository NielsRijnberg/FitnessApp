package com.application.niels.a2bfit.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Adapters.MyLayoutAdapter;
import Classes.DatabaseHelper;
import Classes.Oefening;
import Classes.Schema;
import Classes.Training;

public class StartTrainingActivity extends AppCompatActivity {

    DatabaseHelper db;
    Calendar calendar;
    Spinner spinner;
    Button btnStartTraining;
    Schema selectedSchema;
    EditText etDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_training);

        getSupportActionBar().setTitle("Start training");
        spinner = (Spinner) findViewById(R.id.spinnerSchemas);
        btnStartTraining = (Button) findViewById(R.id.btnStartTraining);
        etDatum = (EditText) findViewById(R.id.etDatum);
        etDatum.setFocusable(false);
        etDatum.setClickable(true);
        calendar = Calendar.getInstance();
        db = new DatabaseHelper(this);

        getSchemas();
        getSelectedSchema();
        goToDatePicker();
        startTraining();
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

    public void updateLabel(){
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMANY);

        etDatum.setText(sdf.format(calendar.getTime()));
    }

    public void goToDatePicker(){
        etDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(StartTrainingActivity.this,R.style.DatePickerTheme, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void getSelectedSchema(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSchema = (Schema) spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void startTraining(){
        btnStartTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int schemaID = selectedSchema.getSchemaID();
                String datum = etDatum.getText().toString();

                Training training = new Training(schemaID, datum);
                int trainingID = (int)db.StartTraining(training);

                Intent startTrainingMetSchema = new Intent(StartTrainingActivity.this, StartTrainingMetSchemaActivity.class);
                startTrainingMetSchema.putExtra("schemaID", schemaID);
                startTrainingMetSchema.putExtra("trainingID", trainingID);
                startTrainingMetSchema.putExtra("datum", datum);

                startActivity(startTrainingMetSchema);
            }
        });
    }

    public void getSchemas(){
        List<Schema> schemaList = db.HaalAlleSchemasOp();
        SpinnerAdapter spinnerAdapter = new MyLayoutAdapter<Schema>(this, android.R.layout.simple_list_item_1, android.R.id.text1, schemaList, 20, Color.BLUE);
        spinner.setAdapter(spinnerAdapter);

        if (schemaList.isEmpty()) {
            showMessage("Error", "Geen spiergroepen gevonden");
        }
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
