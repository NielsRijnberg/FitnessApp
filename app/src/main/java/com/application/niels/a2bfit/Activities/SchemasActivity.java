package com.application.niels.a2bfit.Activities;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

import java.util.ArrayList;
import java.util.List;

import Classes.DatabaseHelper;
import Classes.Schema;
import Classes.Spiergroep;

public class SchemasActivity extends AppCompatActivity {

    DatabaseHelper db;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemas);

        db = new DatabaseHelper(this);
        spinner = (Spinner) findViewById(R.id.SchemaSpinner);

        getSupportActionBar().setTitle("Schema's");

        getSchemas();
    }

    public void getSchemas(){
        Cursor result = db.HaalAlleSchemasOp();
        List<Schema> schemaList = new ArrayList<Schema>();

        if (result.getCount() == 0) {
            showMessage("Error", "Geen spiergroepen gevonden");
            return;
        } else {
            while (result.moveToNext()) {
                int id = result.getInt(result.getColumnIndex("ID"));
                String type = result.getString(result.getColumnIndex("schematype"));
                schemaList.add(new Schema(id, type));

                SpinnerAdapter spinnerAdapter = new ArrayAdapter<Schema>(this, android.R.layout.simple_list_item_1, android.R.id.text1, schemaList){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){
                        View view = super.getView(position, convertView, parent);
                        TextView textview = (TextView) view.findViewById(android.R.id.text1);
                        textview.setTextColor(Color.BLUE);
                        textview.setTextSize(20);
                        return textview;
                    }
                };
                spinner.setAdapter(spinnerAdapter);
            }
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
