package com.example.abdullah.personmatcher;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.abdullah.personmatcher.Activity.AlertDialogueBuilder;
import com.example.abdullah.personmatcher.Fragment.CatagoryFragment;
import com.example.abdullah.personmatcher.Fragment.MainFragment;
import com.example.abdullah.personmatcher.Fragment.MapFragment;
import com.example.abdullah.personmatcher.Menu.FindList;
import com.example.abdullah.personmatcher.Menu.FindMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            Fragment fragment=new MainFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit();
        }

        new LoadAll().execute();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // update the main content by replacing fragments
        Fragment fragment=new CatagoryFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (id == R.id.nav_catagory) {
            // Handle the camera action
            fragment=new CatagoryFragment();
        } else if (id == R.id.nav_map) {
            // Handle the camera action
            fragment=new MapFragment();
        } else if (id == R.id.nav_message) {

        } else if (id == R.id.nav_call) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_setting) {

        }

        fragmentManager.beginTransaction()
                .replace(R.id.frame, fragment)
                .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class LoadAll extends AsyncTask<String, String, String> {
        /**
         * getting All products from url
         */
        protected String doInBackground(String... args)
        {
            try
            {
                //getting menus
                FindMenu find=new FindMenu();
                find.Load();

                //getting al the lists and person details
                FindList findlist=new FindList();
                findlist.load();
            }
            catch(Exception e)
            {
                AlertDialogueBuilder al=new AlertDialogueBuilder();
                al.DialogueBox(MainActivity.this,"No Internet or Server Down");
            }
            return  null;
        }
    }
}
