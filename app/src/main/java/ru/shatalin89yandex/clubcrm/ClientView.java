package ru.shatalin89yandex.clubcrm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class ClientView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientview);
        TextView nameview = (TextView)findViewById(R.id.nameView);
        TextView phoneView = (TextView)findViewById(R.id.PhoneView);
        TextView idclient = (TextView)findViewById(R.id.idClient);
        nameview.setActivated(false);
        phoneView.setActivated(false);
        Button editButton = (Button)findViewById(R.id.EditButton);
        Button cancelButton = (Button)findViewById(R.id.cancelButton);
        editButton.setEnabled(false);
        cancelButton.setEnabled(false);
        Intent intent=getIntent();
        int id= intent.getIntExtra("i", 0);
        idclient.setText(id);
        String name=intent.getStringExtra("name");
        Long phone=intent.getLongExtra("phone", 0);
        phoneView.setText(phone.toString());
        nameview.setText(name);
        System.out.println(id);

    }

    @Override
    public boolean onCreateOptionsMenu(MenuItem item){
        //getMenuInflater().inflate(R.menu.menu_view_edit, menu);
        //return true;

        int ids = item.getItemId();
        switch (id){
            case R.id.action_edit:

                return true;
            case  R.
        }

    }
}
