package ru.shatalin89yandex.clubcrm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DataBaseWork dbw=new DataBaseWork();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String user = intent.getStringExtra("user");
        String pass = intent.getStringExtra("pass");
        dbw.ConnectDB(url, user, pass);

        String driverstatus=DriverManager.getDrivers().toString();
        TextView textInfo=(TextView)findViewById(R.id.infoText);
        TextView textInfo2=(TextView)findViewById(R.id.infoText2);
        textInfo2.setText(dbw.conres);
        textInfo.setText(url+user+pass);

        try {
            clientshow();
        } catch (SQLException e) {
            e.printStackTrace();
        }


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
        getMenuInflater().inflate(R.menu.work, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void getData(View view) throws SQLException {
        TextView textInfo= (TextView)findViewById(R.id.infoText);


        String table="club.client";
        dbw.getData(table);
        ResultSet loc = dbw.resquery;
        //Запихиваем данные в listView
        ListView clientInfo=(ListView)findViewById(R.id.listClient);
        final ArrayList<String> infoclient = new ArrayList<String>();
        while (loc.next()){
            int i= loc.getInt(1);
            String s = loc.getString(2);
            Long t = loc.getLong(3);
            infoclient.add(i+". "+s+" ("+t+")");
        }
        loc.close();
        dbw.resquery.close();
        final ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, infoclient);
        clientInfo.setAdapter(adapter);
    }



    void clientshow() throws SQLException{
        TextView textInfo= (TextView)findViewById(R.id.infoText);


        String table="club.client";
        dbw.getData(table);
        ResultSet loc = dbw.resquery;
        //Запихиваем данные в listView
        ListView clientInfo=(ListView)findViewById(R.id.listClient);
        final ArrayList<String> infoclient = new ArrayList<String>();
        while (loc.next()){
            int i= loc.getInt(1);
            String s = loc.getString(2);
            Long t = loc.getLong(3);
            infoclient.add(i+". "+s+" ("+t+")");
        }
        loc.close();
        dbw.resquery.close();
        final ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, infoclient);
        clientInfo.setAdapter(adapter);

    }
}
