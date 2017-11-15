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
import android.widget.Toast;

import com.application.niels.a2bfit.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import Adapters.MyLayoutAdapter;
import Classes.DatabaseHelper;
import Classes.Oefening;
import Classes.Product;
import Classes.Spiergroep;
import Contexts.ISpiergroepContext;
import Repositories.SpiergroepRepository;
import SqlContexts.SqlSpiergroepContext;

public class OefeningenActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button btnBekijkOefening;
    ListView listView;
    Spinner spinner;

    SpiergroepRepository spiergroepRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefeningen);

        getSupportActionBar().setTitle("Oefeningen");

        db = new DatabaseHelper(this);
        spiergroepRepo = new SpiergroepRepository(new SqlSpiergroepContext());

        btnBekijkOefening = (Button) findViewById(R.id.btnBekijkOefening);
        listView = (ListView) findViewById(R.id.listViewOefeningen);
        spinner = (Spinner) findViewById(R.id.spinner);

        getSpiergroepen();
        getOefeningenVanSpiergroep();
        bekijkOefening();
    }

    public void getOefeningenVanSpiergroep(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getOefeningen();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(OefeningenActivity.this ,"Selecteer iets", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getOefeningen() {
        List<Oefening> oefeningList = db.HaalOefeningenOpBijSpiergroep(spinner.getSelectedItem().toString());
        ListAdapter listAdapter = new MyLayoutAdapter<Oefening>(this, android.R.layout.simple_list_item_1, android.R.id.text1, oefeningList);
        listView.setAdapter(listAdapter);

        if (oefeningList.isEmpty()) {
            showMessage("Error", "Geen oefeningen gevonden");
        }
    }

    public void getSpiergroepen() {
        List<Spiergroep> spiergroepList = db.HaalAlleSpiergroepenOp();
        SpinnerAdapter spinnerAdapter = new MyLayoutAdapter<Spiergroep>(this, android.R.layout.simple_list_item_1, android.R.id.text1, spiergroepList, 20, Color.BLUE);
        spinner.setAdapter(spinnerAdapter);

        if (spiergroepList.isEmpty()) {
            showMessage("Error", "Geen spiergroepen gevonden");
        }
    }

    public void bekijkOefening(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Oefening selectedOefening = (Oefening) listView.getItemAtPosition(position);

                int oefeningID = selectedOefening.getOefeningID();
                String naam = selectedOefening.getNaam();
                String foto = selectedOefening.getFoto();
                String omschrijving = selectedOefening.getOmschrijving();

                Intent oefeningDetailsIntent = new Intent(OefeningenActivity.this, OefeningDetailsActivity.class);
                oefeningDetailsIntent.putExtra("ID", oefeningID);
                oefeningDetailsIntent.putExtra("naam", naam);
                oefeningDetailsIntent.putExtra("oefeningfoto", foto);
                oefeningDetailsIntent.putExtra("oefeningomschrijving", omschrijving);
                startActivity(oefeningDetailsIntent);
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
