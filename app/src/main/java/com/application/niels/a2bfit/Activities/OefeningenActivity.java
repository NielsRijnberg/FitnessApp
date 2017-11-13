package com.application.niels.a2bfit.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import Classes.DatabaseHelper;
import Classes.Oefening;
import Classes.Product;
import Classes.Spiergroep;

public class OefeningenActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button btnBekijkOefening;
    ListView listView;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefeningen);

        db = new DatabaseHelper(this);

        btnBekijkOefening = (Button) findViewById(R.id.btnBekijkOefening);
        listView = (ListView) findViewById(R.id.listViewOefeningen);
        spinner = (Spinner) findViewById(R.id.spinner);

        getSupportActionBar().setTitle("Oefeningen");

        getOefeningen();
        getSpiergroepen();
        bekijkOefening();
    }

    public void getOefeningen() {
        Cursor result = db.HaalAlleOefeningenOp();
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
                listView.setAdapter(listAdapter);
            }
        }
    }

    public void getSpiergroepen() {
        Cursor result = db.HaalAlleSpiergroepenOp();
        List<Spiergroep> spiergroepList = new ArrayList<Spiergroep>();

        if (result.getCount() == 0) {
            showMessage("Error", "Geen spiergroepen gevonden");
            return;
        } else {
            while (result.moveToNext()) {
                int id = result.getInt(result.getColumnIndex("ID"));
                String naam = result.getString(result.getColumnIndex("spiergroepnaam"));
                spiergroepList.add(new Spiergroep(id, naam));

                SpinnerAdapter spinnerAdapter = new ArrayAdapter<Spiergroep>(this, android.R.layout.simple_list_item_1, android.R.id.text1, spiergroepList){
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

    public void bekijkOefening(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Oefening selectedOefening = (Oefening) listView.getItemAtPosition(position);

                int oefeningID = selectedOefening.getOefeningID();
                String naam = selectedOefening.getNaam();
                int aantalSets = selectedOefening.getAantalSets();
                int aantalReps = selectedOefening.getAantalReps();
                String foto = selectedOefening.getFoto();

                Intent oefeningDetailsIntent = new Intent(OefeningenActivity.this, OefeningDetailsActivity.class);

                oefeningDetailsIntent.putExtra("ID", oefeningID);
                oefeningDetailsIntent.putExtra("naam", naam);
                oefeningDetailsIntent.putExtra("aantalSets", aantalSets);
                oefeningDetailsIntent.putExtra("aantalReps", aantalReps);
                oefeningDetailsIntent.putExtra("oefeningfoto", foto);
                startActivity(oefeningDetailsIntent);
            }
        });
    }


    /*public void putOefeningenBijSpiergroep() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }*/

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
