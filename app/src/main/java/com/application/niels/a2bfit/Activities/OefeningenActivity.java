package com.application.niels.a2bfit.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.application.niels.a2bfit.R;

import java.util.List;

import Adapters.MyLayoutAdapter;
import Classes.DatabaseHelper;
import Model.Oefening;
import Model.Spiergroep;
import Model.Fitness;
import Repo.FitnessRepo;
import Repositories.SpiergroepRepository;

public class OefeningenActivity extends AppCompatActivity {

    ListView listView;
    Spinner spinner;
    Fitness fitness;

    SpiergroepRepository spiergroepRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefeningen);

        getSupportActionBar().setTitle("Oefeningen");

        DatabaseHelper db = new DatabaseHelper(this);
        FitnessRepo fitnessRepo = new FitnessRepo(db);
        fitness = new Fitness(fitnessRepo);

        listView = (ListView) findViewById(R.id.listViewOefeningen);
        spinner = (Spinner) findViewById(R.id.spinner);

        fillSpiergroepenListbox();
        getOefeningenVanSpiergroep();
        bekijkOefening();
    }

    public void getOefeningenVanSpiergroep(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fillOefeningenListboxBijSpiergroep();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(OefeningenActivity.this ,"Selecteer iets", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fillOefeningenListboxBijSpiergroep() {
        List<Oefening> oefeningList = fitness.getOefeningen(spinner.getSelectedItem().toString());
        ListAdapter listAdapter = new MyLayoutAdapter<Oefening>(this, android.R.layout.simple_list_item_1, android.R.id.text1, oefeningList);
        listView.setAdapter(listAdapter);

        if (oefeningList.isEmpty()) {
            showMessage("Error", "Geen oefeningen gevonden");
        }
    }

    public void fillSpiergroepenListbox() {
        List<Spiergroep> spiergroepList = fitness.getSpiergroepen();
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

                long oefeningID = selectedOefening.getOefeningID();
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
