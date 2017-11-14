package com.application.niels.a2bfit.Activities;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.application.niels.a2bfit.R;

import java.util.ArrayList;
import java.util.List;

import Classes.DatabaseHelper;
import Classes.Oefening;
import Classes.Schema;
import Classes.Spiergroep;
import Repositories.SchemaRepository;

public class SchemasActivity extends AppCompatActivity {

    DatabaseHelper db;
    Spinner spinner;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemas);

        db = new DatabaseHelper(this);
        spinner = (Spinner) findViewById(R.id.SchemaSpinner);
        listview = (ListView) findViewById(R.id.ListViewOefeningenVanSchema);

        getSupportActionBar().setTitle("Schema's");

        getSchemas();
        getOefeningenVanSchema();
    }



    public void getOefeningenVanSchema(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getOefeningen();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(SchemasActivity.this ,"Selecteer iets", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getOefeningen() {
        Cursor result = db.HaalOefeningenOpBijSchema(spinner.getSelectedItem().toString());
        List<Oefening> oefeningList = new ArrayList<Oefening>();

        if (result.getCount() == 0) {
            showMessage("Error", "Geen oefeningen gevonden");
            return;
        } else {
            while (result.moveToNext()) {
                int id = result.getInt(result.getColumnIndex("ID"));
                String naam = result.getString(result.getColumnIndex("oefeningnaam"));
                int aantalSets = result.getInt(result.getColumnIndex("aantalsets"));
                int aantalReps = result.getInt(result.getColumnIndex("aantalreps"));
                String foto = result.getString(result.getColumnIndex("oefeningfoto"));
                oefeningList.add(new Oefening(id, naam, aantalSets, aantalReps, foto));

                ListAdapter listAdapter = new ArrayAdapter<Oefening>(this, android.R.layout.simple_list_item_1, android.R.id.text1, oefeningList){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){
                        View view = super.getView(position, convertView, parent);
                        TextView textview = (TextView) view.findViewById(android.R.id.text1);
                        textview.setTextColor(Color.WHITE);
                        return textview;
                    }
                };
                listview.setAdapter(listAdapter);
            }
        }
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
