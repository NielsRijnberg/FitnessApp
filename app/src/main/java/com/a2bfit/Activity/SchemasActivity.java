package com.a2bfit.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.application.niels.a2bfit.R;

import java.util.List;

import com.a2bfit.Adapter.MyLayoutAdapter;
import com.a2bfit.Repo.DatabaseHelper;
import com.a2bfit.Model.Oefening;
import com.a2bfit.Model.Schema;
import com.a2bfit.Model.Gebruiker;
import com.a2bfit.Repo.GebruikerRepo;

public class SchemasActivity extends AppCompatActivity {

    Spinner spinner;
    ListView listview;
    Button btnBekijkOefeningVanSchema;
    Oefening selectedOefening;
    Gebruiker gebruiker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemas);

        gebruiker = new Gebruiker(new GebruikerRepo(new DatabaseHelper(this)));

        spinner = (Spinner) findViewById(R.id.SchemaSpinner);
        listview = (ListView) findViewById(R.id.ListViewOefeningenVanSchema);
        btnBekijkOefeningVanSchema = (Button) findViewById(R.id.btnBekijkOefeningVanSchema);

        getSupportActionBar().setTitle("Schema's");

        getSchemas();
        getOefeningenVanSchema();
        selectedListItem();
        bekijkOefening();
    }

    public void selectedListItem(){
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedOefening = (Oefening) listview.getItemAtPosition(position);
            }
        });
    }

    public void bekijkOefening() {

        btnBekijkOefeningVanSchema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedOefening != null) {
                    long oefeningID = selectedOefening.getOefeningID();
                    String naam = selectedOefening.getNaam();
                    String foto = selectedOefening.getFoto();
                    String omschrijving = selectedOefening.getOmschrijving();

                    Intent oefeningDetailsIntent = new Intent(SchemasActivity.this, OefeningDetailsActivity.class);
                    oefeningDetailsIntent.putExtra("ID", oefeningID);
                    oefeningDetailsIntent.putExtra("naam", naam);
                    oefeningDetailsIntent.putExtra("oefeningfoto", foto);
                    oefeningDetailsIntent.putExtra("oefeningomschrijving", omschrijving);
                    startActivity(oefeningDetailsIntent);
                }
                else {
                    showMessage("Error", "Selecteer een oefening");
                }
            }
        });
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
        List<Oefening> oefeningList = gebruiker.getOefeningenVoorSchema(((Schema) spinner.getSelectedItem()).getSchemaID());
        ListAdapter listAdapter = new MyLayoutAdapter<Oefening>(this, android.R.layout.simple_list_item_1, android.R.id.text1, oefeningList);
        listview.setAdapter(listAdapter);

        if (oefeningList.isEmpty()) {
            showMessage("Error", "Geen oefeningen gevonden");
            selectedOefening = null;
        } else {
            selectedOefening = oefeningList.get(0);
        }
    }

    public void getSchemas(){
        List<Schema> schemaList = gebruiker.getSchemas();
        SpinnerAdapter spinnerAdapter = new MyLayoutAdapter<Oefening>(this, android.R.layout.simple_list_item_1, android.R.id.text1, schemaList, 20, Color.BLUE);
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
