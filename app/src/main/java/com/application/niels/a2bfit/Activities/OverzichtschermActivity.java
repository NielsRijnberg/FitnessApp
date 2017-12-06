package com.application.niels.a2bfit.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.application.niels.a2bfit.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

import Classes.DatabaseHelper;
import Adapters.MyImageAdapter;
import me.relex.circleindicator.CircleIndicator;


public class OverzichtschermActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] FOTOS = {R.drawable.apparaten_bfit, R.drawable.apparaten_bfitt, R.drawable.apparaten_bfittt};
    private ArrayList<Integer> FOTOArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overzichtscherm);

        initPhotoSlider();
        initDatabase();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        getSupportActionBar().setTitle("2BFIT");

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        HandleMenuItems();
    }

    private void initDatabase() {
        DatabaseHelper db = new DatabaseHelper(this);
        SQLiteDatabase sqlDb = db.getWritableDatabase();
        db.dropTables(sqlDb);
        db.createTablesIfNotExists(sqlDb);
        db.insertSampleData(sqlDb);
        db.close();
    }

    private void HandleMenuItems() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

            if(menuItem.isChecked()) menuItem.setChecked(false);
            else menuItem.setChecked(true);

            mDrawerLayout.closeDrawers();

            switch (menuItem.getItemId()){
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
                case R.id.nav_StartTraining:
                    Toast.makeText(getApplicationContext(), "Start training", Toast.LENGTH_SHORT).show();
                    Intent startTrainingIntent = new Intent(OverzichtschermActivity.this, StartTrainingActivity.class);
                    startActivity(startTrainingIntent);
                    return true;
                case R.id.nav_Trainingsdetails:
                    Toast.makeText(getApplicationContext(), "Trainingsdetails", Toast.LENGTH_SHORT).show();
                    Intent trainingsDetailsIntent = new Intent(OverzichtschermActivity.this, TrainingDetailsActivity.class);
                    startActivity(trainingsDetailsIntent);
                    return true;
                case R.id.nav_Uitloggen:
                    Toast.makeText(getApplicationContext(), "Uitloggen", Toast.LENGTH_SHORT).show();
                    Intent uitloggenIntent = new Intent(OverzichtschermActivity.this, LoginActivity.class);
                    startActivity(uitloggenIntent);
                    return true;
                default:
                    Toast.makeText(getApplicationContext(), "Er is iets mis gegaan...", Toast.LENGTH_SHORT).show();
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

    private void initPhotoSlider() {
        for(int i = 0; i< FOTOS.length; i++)
            FOTOArray.add(FOTOS[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyImageAdapter(OverzichtschermActivity.this, FOTOArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == FOTOS.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }
}
