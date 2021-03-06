package com.a2bfit.Activity;


import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.application.niels.a2bfit.R;
import java.util.List;
import com.a2bfit.Adapter.MyLayoutAdapter;
import com.a2bfit.Repo.DatabaseHelper;
import com.a2bfit.Model.Spiergroep;
import com.a2bfit.Model.Training;
import com.a2bfit.Model.TrainingsOefening;
import com.a2bfit.Model.Gebruiker;
import com.a2bfit.Repo.GebruikerRepo;

public class TrainingDetailsActivity extends AppCompatActivity {

    Spinner TrainingSpinner;
    EditText etTotaalAantalOefeningen;
    EditText etTotaalAantalHerhalingen;
    EditText etTotaalGewicht;
    Gebruiker gebruiker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_details);

        gebruiker = new Gebruiker(new GebruikerRepo(new DatabaseHelper(this)));

        TrainingSpinner = (Spinner) findViewById(R.id.TrainingSpinner);
        etTotaalAantalOefeningen = (EditText) findViewById(R.id.etTotaalAantalOefeningen);
        etTotaalAantalHerhalingen = (EditText) findViewById(R.id.etTotaalAantalHerhalingen);
        etTotaalGewicht = (EditText) findViewById(R.id.etTotaalGewicht);

        fillSpinner();
        updateSpinner();
    }

    public void fillSpinner(){
        List<Training> trainingList = gebruiker.getTrainingen();
        SpinnerAdapter spinnerAdapter = new MyLayoutAdapter<Spiergroep>(this, android.R.layout.simple_list_item_1, android.R.id.text1, trainingList, 20, Color.BLUE);
        TrainingSpinner.setAdapter(spinnerAdapter);

        if (trainingList.isEmpty()) {
            showMessage("Error", "Geen trainingen gevonden");
        }
    }

    public void updateSpinner(){
        TrainingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setAantalOefeningen();
                setTotaalAantalHerhalingen();
                setTotaalGewicht();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void setAantalOefeningen(){
        Training selectedTraining = (Training) TrainingSpinner.getSelectedItem();
        long trainingID = selectedTraining.getTrainingID();
        List<TrainingsOefening> oefeningenVanTraining = gebruiker.getOefeningenBijTraining(trainingID);
        int aantalOefeningen = oefeningenVanTraining.size();
        etTotaalAantalOefeningen.setText(aantalOefeningen + " oefeningen gedaan.");
    }

    public void setTotaalAantalHerhalingen(){
        int totaalAantalHerhalingen = 0;

        Training selectedTraining = (Training) TrainingSpinner.getSelectedItem();
        long trainingID = selectedTraining.getTrainingID();

        List<TrainingsOefening> oefeningenVanTraining = gebruiker.getOefeningenBijTraining(trainingID);
        for (TrainingsOefening trainingsOefening : oefeningenVanTraining) {
            int[] setsEnReps = gebruiker.getSetsEnReps(trainingsOefening.getOefeningID());
            totaalAantalHerhalingen += (setsEnReps[0] * setsEnReps[1]);
        }

        etTotaalAantalHerhalingen.setText(totaalAantalHerhalingen + " herhalingen gedaan.");
    }

    public void setTotaalGewicht(){
        int totaalGewicht = 0;

        Training selectedTraining = (Training) TrainingSpinner.getSelectedItem();
        long trainingID = selectedTraining.getTrainingID();


        List<TrainingsOefening> oefeningenVanTraining = gebruiker.getOefeningenBijTraining(trainingID);
        for (TrainingsOefening trainingsOefening : oefeningenVanTraining) {
            int[] setsEnReps = gebruiker.getSetsEnReps(trainingsOefening.getOefeningID());
            totaalGewicht += (trainingsOefening.getGewicht() * setsEnReps[0] * setsEnReps[1]);
        }


        etTotaalGewicht.setText(totaalGewicht+" kilo getild.");
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
