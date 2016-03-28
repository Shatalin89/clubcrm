package ru.shatalin89yandex.clubcrm;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import ru.shatalin89yandex.clubcrm.fragment.FClientList;


public class WorkActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FClientList fclients = new FClientList();

    public DataBaseWork dbw=new DataBaseWork();
    static final private int CHOOSE_THIEF=0;
    static final private int ADD_CLIENT=0;
    int argForAdd=0;



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


        //выводим информацию о клиентах при подключении
        try {
            clientshow();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        final ListView clientInfo=(ListView)findViewById(R.id.listClient);
        clientInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                int idpos = position;
                argForAdd = 1;
                Long idclient = dbw.idlist[idpos];
                String teblename = "client";

                //запускам активити с выбранными элементами
                Intent ClientView = new Intent(WorkActivity.this, ClientView.class);
                try {
                    dbw.getDataID(idclient, teblename);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                ResultSet loc = dbw.resquery;
                try {
                    while (loc.next()) {
                        Long i = loc.getLong(1);
                        String name = loc.getString(2);
                        String phone = loc.getString(3);

                        ClientView.putExtra("i", i);
                        ClientView.putExtra("name", name);
                        ClientView.putExtra("phone", phone);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    loc.close();
                    dbw.resquery.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                // ClientView.putExtra("idclient", idclient);
                // startActivity(ClientView);
                ClientView.putExtra("arg", 2);
                startActivityForResult(ClientView, CHOOSE_THIEF);
            }


        });
        clientInfo.setEnabled(false);



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
        FragmentTransaction ftrans= getFragmentManager().beginTransaction();

        if (id == R.id.nav_client) {
            ftrans.replace(R.id.container, fclients);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getData(View view) throws SQLException {
        Intent ClientView= new Intent(WorkActivity.this, ClientView.class);
        long i=0;
        String name="";
        String phone="";
        String autoinc="club.client";
        i=dbw.getlastID(autoinc);
        System.out.println("lastid="+i+autoinc);
        ClientView.putExtra("arg", 1);
        ClientView.putExtra("i", i+1);
        ClientView.putExtra("name", name);
        ClientView.putExtra("phone", phone);
        argForAdd=2;
        startActivityForResult(ClientView, ADD_CLIENT);
    }

    void clientshow() throws SQLException {
        int j=0;
        dbw.idlist = new Long[20];
        String table="club.client";
        dbw.getData(table);
        ResultSet loc = dbw.resquery;
        //Запихиваем данные в listView
        ListView clientInfo=(ListView)findViewById(R.id.listClient);
        final ArrayList<String> infoclient = new ArrayList<String>();
        while (loc.next()){
            Long i= loc.getLong(1);
            dbw.idlist[j]=i;
            String s = loc.getString(2);
            String t = loc.getString(3);
            // infoclient.add(i+". "+s+" ("+t+")");
            infoclient.add(s+" ("+t+")");
            j++;
        }
        loc.close();
        dbw.resquery.close();
        final ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, infoclient);
        clientInfo.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode==CHOOSE_THIEF && argForAdd==1){
            int arg=data.getIntExtra("arg", 0);
            if (resultCode==RESULT_OK) {
                if (arg==0){
                    System.out.println("чтото произошло");
                }
                if (arg==1){
                    String id = data.getStringExtra("id");
                    String table="client";
                    try {
                        dbw.delDataID(id, table);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        dbw.resquery.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        clientshow();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(arg==2) {
                    String id = data.getStringExtra("id");
                    String name = data.getStringExtra("name");
                    String pbone = data.getStringExtra("phone");
                    String table = "client";
                    try {
                        dbw.editData(table, id, "name", "telephone", name, pbone);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    TextView infClient = (TextView) findViewById(R.id.infClient);
                    infClient.setText(dbw.querystring);
                    try {
                        clientshow();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (resultCode==RESULT_CANCELED){
                try {
                    clientshow();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if( requestCode==ADD_CLIENT && argForAdd==2) {
            if (resultCode==RESULT_OK) {
                String id = data.getStringExtra("id");
                String name = data.getStringExtra("name");
                String pbone = data.getStringExtra("phone");
                String table = "client";
                try {
                    dbw.addData(table, name, pbone);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    clientshow();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultCode==RESULT_CANCELED){
                try {
                    clientshow();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
