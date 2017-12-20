package com.application.niels.a2bfit.Activities;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

import java.util.List;

import Adapters.MyLayoutAdapter;
import Classes.DatabaseHelper;
import Classes.Oefening;
import Classes.Prestatie;
import Classes.Schema;

public class PrestatieActivity extends AppCompatActivity {

    DatabaseHelper db;
    ListView lvPrestaties;
    Prestatie selectedPrestatie;
    TextView tvPrestatieOmschrijving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestatie);

        db = new DatabaseHelper(this);
        lvPrestaties = (ListView) findViewById(R.id.ListViewPrestaties);
        tvPrestatieOmschrijving = (TextView) findViewById(R.id.tvPrestatieOmschrijving);


        getSupportActionBar().setTitle("Prestaties");
        getPrestaties();
        selectedListItem();
    }

    public void selectedListItem(){
        lvPrestaties.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedPrestatie = (Prestatie) lvPrestaties.getItemAtPosition(position);
                tvPrestatieOmschrijving.setText(selectedPrestatie.getOmschrijving());
            }
        });
    }

    public void getPrestaties() {
        List<Prestatie> prestatieList = db.HaalAllePrestatiesOp();
        ListAdapter listAdapter = new MyLayoutAdapter<Prestatie>(this, android.R.layout.simple_list_item_1, android.R.id.text1, prestatieList);
        lvPrestaties.setAdapter(listAdapter);

        if (prestatieList.isEmpty()) {
            showMessage("Error", "Geen oefeningen gevonden");
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
