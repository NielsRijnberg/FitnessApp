package com.a2bfit.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.niels.a2bfit.R;

import java.util.List;

import com.a2bfit.Adapter.MyLayoutAdapter;
import com.a2bfit.Repo.DatabaseHelper;
import com.a2bfit.Model.Oefening;
import com.a2bfit.Model.TrainingsOefening;
import com.a2bfit.Model.Gebruiker;
import com.a2bfit.Repo.GebruikerRepo;

public class StartTrainingMetSchemaActivity extends AppCompatActivity {

    TextView tvSchemaType;
    EditText etGewicht;
    ListView lvOefeningenVanSchema;
    Button btnOefeningAfvinken;
    Button btnTrainingBeeindigen;
    Button btnBekijkOefening;

    int selectedIndex;
    List<Oefening> oefeningenVanSchema;
    ListAdapter listAdapter;
    boolean isOefeningSelected = false;
    Oefening selectedOefening;
    Gebruiker gebruiker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_training_met_schema);

        gebruiker = new Gebruiker(new GebruikerRepo(new DatabaseHelper(this)));

        tvSchemaType = (TextView) findViewById(R.id.tvSchemaType);
        etGewicht = (EditText) findViewById(R.id.etGewicht);
        lvOefeningenVanSchema = (ListView) findViewById(R.id.lvOefeningenVanSchema);
        btnOefeningAfvinken = (Button) findViewById(R.id.btnOefeningAfvinken);
        btnTrainingBeeindigen = (Button) findViewById(R.id.btnTrainingBeeindigen);
        btnBekijkOefening = (Button) findViewById(R.id.btnBekijkOefening);

        long schemaID = getIntent().getExtras().getLong("schemaID");
        oefeningenVanSchema = gebruiker.getOefeningenVoorSchema(schemaID);

        setOefeningenListbox();
        getClickedOefening();
        VinkOefeningAf();
        TrainingBeeindigen();
        bekijkOefening();
        selectedListItem();
    }

    public void selectedListItem(){
        lvOefeningenVanSchema.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedOefening = (Oefening) lvOefeningenVanSchema.getItemAtPosition(position);
            }
        });
    }

    public void bekijkOefening() {
        btnBekijkOefening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long oefeningID = selectedOefening.getOefeningID();
                String naam = selectedOefening.getNaam();
                String foto = selectedOefening.getFoto();
                String omschrijving = selectedOefening.getOmschrijving();

                Intent oefeningDetailsIntent = new Intent(StartTrainingMetSchemaActivity.this, OefeningDetailsActivity.class);
                oefeningDetailsIntent.putExtra("ID", oefeningID);
                oefeningDetailsIntent.putExtra("naam", naam);
                oefeningDetailsIntent.putExtra("oefeningfoto", foto);
                oefeningDetailsIntent.putExtra("oefeningomschrijving", omschrijving);
                startActivity(oefeningDetailsIntent);
            }
        });
    }

    public void getClickedOefening(){
        lvOefeningenVanSchema.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedIndex = i;
                isOefeningSelected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                isOefeningSelected = false;
            }
        });
    }

    public void setOefeningenListbox(){
        listAdapter = new MyLayoutAdapter<Oefening>(this, android.R.layout.simple_list_item_1, android.R.id.text1, oefeningenVanSchema);
        lvOefeningenVanSchema.setAdapter(listAdapter);

        if (oefeningenVanSchema.isEmpty()){
            showMessage("Error", "Geen oefeningen gevonden");
        }
    }


    //TODO zorgen dat ifstatement die checkt op gewicht het doet
    public void VinkOefeningAf(){
        btnOefeningAfvinken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gewichtAsString = etGewicht.getText().toString();
                if (!gewichtAsString.isEmpty()){
                    int gewicht = Integer.valueOf(gewichtAsString);


                    long trainingID = getIntent().getExtras().getLong("trainingID");
                    long oefeningID = ((Oefening)lvOefeningenVanSchema.getItemAtPosition(selectedIndex)).getOefeningID();

                    TrainingsOefening currentTrainingsOefening = new TrainingsOefening(trainingID, oefeningID, gewicht);
                    gebruiker.vinkOefeningAf(currentTrainingsOefening);
                    Toast.makeText(StartTrainingMetSchemaActivity.this, "Oefening afgevinkt", Toast.LENGTH_LONG).show();

/*                    else {
                        Toast.makeText(StartTrainingMetSchemaActivity.this, "Selecteer een oefening", Toast.LENGTH_LONG).show();
                    }*/
                }
                else{
                    Toast.makeText(StartTrainingMetSchemaActivity.this, "Vul een gewicht in", Toast.LENGTH_LONG).show();
                }
                oefeningenVanSchema.remove(selectedIndex);
                lvOefeningenVanSchema.invalidateViews();
            }
        });
    }

    public void TrainingBeeindigen(){
        btnTrainingBeeindigen.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder adb;
            @Override
            public void onClick(View view) {
                if (oefeningenVanSchema.size() > 0){
                    adb =new AlertDialog.Builder(StartTrainingMetSchemaActivity.this);
                    //adb.setView(R.layout.);
                    adb.setTitle("Training beëindigen");
                    adb.setMessage("Weet je zeker dat je de training wilt beëindigen, er zijn nog oefeningen over");
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent overzichtSchermIntent = new Intent(StartTrainingMetSchemaActivity.this, OverzichtschermActivity.class);
                            startActivity(overzichtSchermIntent);
                        }});
                    adb.show();
                }
                else{
                    Intent overzichtSchermIntent = new Intent(StartTrainingMetSchemaActivity.this, OverzichtschermActivity.class);
                    startActivity(overzichtSchermIntent);
                    Toast.makeText(StartTrainingMetSchemaActivity.this ,"Training beëindigd", Toast.LENGTH_LONG).show();
                }
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
