package com.application.niels.a2bfit.Activities;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.application.niels.a2bfit.R;

import Classes.DatabaseHelper;

public class ProductActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        db = new DatabaseHelper(this);
        getProducten();
    }

    public void getProducten(){
        Cursor result = db.HaalAlleProductenOp();

        if (result.getCount() == 0){
            showMessage("Error", "Geen oefeningen gevonden");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (result.moveToNext()){
            buffer.append("ProductID :"+ result.getString(0)+ "\n");
            buffer.append("Naam :"+ result.getString(1)+ "\n");
            buffer.append("Kosten :"+ result.getString(2)+ "\n");
            buffer.append("Omschrijving :"+ result.getString(3)+ "\n\n");
        }
        showMessage("Data", buffer.toString());
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
