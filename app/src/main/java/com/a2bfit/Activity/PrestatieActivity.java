package com.a2bfit.Activity;

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

import com.a2bfit.Adapter.MyLayoutAdapter;
import com.a2bfit.Repo.DatabaseHelper;
import com.a2bfit.Model.Prestatie;
import com.a2bfit.Model.Gebruiker;
import com.a2bfit.Repo.GebruikerRepo;

public class PrestatieActivity extends AppCompatActivity {

    ListView lvPrestaties;
    Prestatie selectedPrestatie;
    TextView tvPrestatieOmschrijving;
    Gebruiker gebruiker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestatie);

        DatabaseHelper db = new DatabaseHelper(this);
        GebruikerRepo gebruikerRepo = new GebruikerRepo(db);
        gebruiker = new Gebruiker(gebruikerRepo);

        lvPrestaties = (ListView) findViewById(R.id.ListViewPrestaties);
        tvPrestatieOmschrijving = (TextView) findViewById(R.id.tvPrestatieOmschrijving);


        getSupportActionBar().setTitle("Prestaties");
        FillPrestatieListbox();
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

    public void FillPrestatieListbox() {
        List<Prestatie> prestatieList = gebruiker.getPrestaties();
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
