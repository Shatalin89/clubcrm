package ru.shatalin89yandex.clubcrm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

public class ClientView extends AppCompatActivity {
    AlertDialog.Builder ad;
    Context context;

    public int idalert;
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
        idalert =id;
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String sid=id+"";
        idclient.setText(sid);

        if(phone==null){phone="";}
        phoneView.setText(phone);
        nameview.setText(name);

        //Делаем диалоговое окно для удаления
        context=ClientView.this;
        String title= "Внимание!";
        String message="Вы действительно хотите удалить?";
        String buttonYes = "Да";
        String buttonNo = "Нет";
        ad = new AlertDialog.Builder(context);
        ad.setTitle(title);
        ad.setMessage(message);
        ad.setPositiveButton(buttonYes, new OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                deleteViewClient();
                Toast.makeText(context, "Пользователь " + idalert + " удален", Toast.LENGTH_SHORT).show();
            }
        });
        ad.setNegativeButton(buttonNo, new OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                Toast.makeText(context, "Пользователь " + idalert + " остался с нами", Toast.LENGTH_SHORT).show();
            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(context, "Отмена", Toast.LENGTH_SHORT).show();
            }
        });
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
                ad.show();
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
        int edit=2;
        resultIntent.putExtra("id", id);
        resultIntent.putExtra("name", name);
        resultIntent.putExtra("phone", phone);
        resultIntent.putExtra("arg", edit);

        System.out.println("clientview=" + id + " " + name + " " + phone);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void cancelViewClient(View view){
        Intent resultIntent = new Intent();
        setResult(RESULT_CANCELED, resultIntent);
        finish();
    }

    public void deleteViewClient(){
        Intent resultIntent = new Intent();
        TextView idclient = (TextView)findViewById(R.id.idClient);
        String id=idclient.getText().toString();
        int del=1;
        resultIntent.putExtra("id", id);
        resultIntent.putExtra("arg", del);
        setResult(RESULT_OK, resultIntent);
        finish();

    }


}

