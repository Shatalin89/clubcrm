package ru.shatalin89yandex.clubcrm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClientView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientview);

        EditText nameview = (EditText) findViewById(R.id.nameView);
        EditText phoneView = (EditText) findViewById(R.id.PhoneView);
        TextView idclient = (TextView) findViewById(R.id.idClient);    ;
        Button editButton = (Button) findViewById(R.id.EditButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        editButton.setEnabled(false);
        cancelButton.setEnabled(false);
        Intent intent = getIntent();
        int id = intent.getIntExtra("i", 0);
        String name = intent.getStringExtra("name");
        Long phone = intent.getLongExtra("phone", 0);
        String sid=id+"";
        idclient.setText(sid);
        phoneView.setText(phone.toString());
        nameview.setText(name);
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_edit, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText nameview = (EditText) findViewById(R.id.nameView);
        EditText phoneView = (EditText) findViewById(R.id.PhoneView);
        int ids = item.getItemId();
        switch (ids) {
            case R.id.action_settings_view_edit:

                return true;
            case R.id.action_edit:

                EnableEdittext(nameview, phoneView);
                return true;
            case R.id.action_delete:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }




    private void EnableEdittext(EditText edit1, EditText edit2){

        Button editButton = (Button) findViewById(R.id.EditButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        editButton.setEnabled(true);
        cancelButton.setEnabled(true);
        //делаем активным окна для редактирования
        edit1.setCursorVisible(true);
        edit1.setFocusableInTouchMode(true);
        edit1.setLongClickable(true);

        edit2.setCursorVisible(true);
        edit2.setFocusableInTouchMode(true);
        edit2.setLongClickable(true);

    }

    public final static String THIEF="";

    public void saveViewClient(View view) {

        Intent resultIntent = new Intent();
        EditText nameview = (EditText) findViewById(R.id.nameView);
        EditText phoneView = (EditText) findViewById(R.id.PhoneView);
        TextView idclient = (TextView)findViewById(R.id.idClient);
        String id=idclient.getText().toString();
        String name=nameview.getText().toString();
        String phone=phoneView.getText().toString();

        resultIntent.putExtra("id", id);
        resultIntent.putExtra("name", name);
        resultIntent.putExtra("phone", phone);
        System.out.println("clientview="+id + " " + name + " " + phone);
        setResult(RESULT_OK, resultIntent);
        finish();
    }


}
