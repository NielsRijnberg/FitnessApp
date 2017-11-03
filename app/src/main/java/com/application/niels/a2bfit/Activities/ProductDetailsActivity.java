package com.application.niels.a2bfit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.application.niels.a2bfit.R;

public class ProductDetailsActivity extends AppCompatActivity {

    int productID;
    String naam;
    double kosten;
    String omschrijving;
    TextView tvProductNaam;
    TextView tvProductKosten;
    TextView tvProductOmschrijving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        tvProductNaam = (TextView) findViewById(R.id.tvProductNaam);
        tvProductKosten = (TextView) findViewById(R.id.tvProductKosten);
        tvProductOmschrijving = (TextView) findViewById(R.id.tvProductOmschrijving);

        getProductDetails();
        setTextBoxes();
    }

    private void getProductDetails(){
        productID = getIntent().getExtras().getInt("ID");
        naam = getIntent().getExtras().getString("naam");
        kosten = getIntent().getExtras().getDouble("kosten");
        omschrijving = getIntent().getExtras().getString("omschrijving");
    }

    private void setTextBoxes(){
        tvProductNaam.setText(naam);
        tvProductKosten.setText("€ " + kosten + "0");
        tvProductOmschrijving.setText(omschrijving);
    }
}
