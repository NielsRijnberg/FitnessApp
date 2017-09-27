package com.application.niels.a2bfit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.application.niels.a2bfit.R;

import Classes.DatabaseHelper;

public class ProductActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        myDb = new DatabaseHelper(this);
    }
}
