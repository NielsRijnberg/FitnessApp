package com.application.niels.a2bfit.Activities;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

import java.util.ArrayList;
import java.util.List;

import Adapters.MyLayoutAdapter;
import Classes.DatabaseHelper;
import Classes.Schema;

public class StartTrainingActivity extends AppCompatActivity {

    DatabaseHelper db;
    Spinner spinner;
    Button btnStartTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_training);

        getSupportActionBar().setTitle("Start training");
        spinner = (Spinner) findViewById(R.id.spinnerSchemas);
        btnStartTraining = (Button) findViewById(R.id.btnStartTraining);
        db = new DatabaseHelper(this);

        getSchemas();
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
