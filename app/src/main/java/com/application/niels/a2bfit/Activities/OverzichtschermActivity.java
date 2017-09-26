package com.application.niels.a2bfit.Activities;

import android.content.ClipData;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.application.niels.a2bfit.R;


public class OverzichtschermActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overzichtscherm);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                mDrawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_Producten:
                        Toast.makeText(getApplicationContext(), "Producten", Toast.LENGTH_SHORT).show();
                        Intent productIntent = new Intent(OverzichtschermActivity.this, ProductActivity.class);
                        startActivity(productIntent);
                        return true;
                    case R.id.nav_Aankopen:
                        Toast.makeText(getApplicationContext(), "Aankopen", Toast.LENGTH_SHORT).show();
                        Intent aankopenIntent = new Intent(OverzichtschermActivity.this, AankopenActivity.class);
                        startActivity(aankopenIntent);
                        return true;
                    case R.id.nav_Account:
                        Toast.makeText(getApplicationContext(), "Account", Toast.LENGTH_SHORT).show();
                        Intent accountIntent = new Intent(OverzichtschermActivity.this, AccountActivity.class);
                        startActivity(accountIntent);
                        return true;
                    case R.id.nav_Instellingen:
                        Toast.makeText(getApplicationContext(), "Instellingen", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_Oefeningen:
                        Toast.makeText(getApplicationContext(), "Oefeningen", Toast.LENGTH_SHORT).show();
                        Intent oefeningenIntent = new Intent(OverzichtschermActivity.this, OefeningenActivity.class);
                        startActivity(oefeningenIntent);
                        return true;
                    case R.id.nav_Schemas:
                        Toast.makeText(getApplicationContext(), "Schema's", Toast.LENGTH_SHORT).show();
                        Intent schemasIntent = new Intent(OverzichtschermActivity.this, SchemasActivity.class);
                        startActivity(schemasIntent);
                        return true;
                    case R.id.nav_Uitloggen:
                        Toast.makeText(getApplicationContext(), "Uitloggen", Toast.LENGTH_SHORT).show();
                        Intent uitloggenIntent = new Intent(OverzichtschermActivity.this, LoginActivity.class);
                        startActivity(uitloggenIntent);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
