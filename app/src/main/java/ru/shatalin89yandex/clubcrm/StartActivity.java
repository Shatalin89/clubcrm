package ru.shatalin89yandex.clubcrm;


import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.sql.DriverManager;




public class StartActivity extends AppCompatActivity {
    DataBaseWork dbw=new DataBaseWork();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Spinner spinner= (Spinner) findViewById(R.id.host_array);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.host_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }



    public void connect(View view) {

        Spinner spinner=(Spinner) findViewById(R.id.host_array);
        TextView textView= (TextView)findViewById(R.id.infoView);
        EditText userEdit= (EditText)findViewById(R.id.userText);
        EditText passText =(EditText)findViewById(R.id.passText);
        String url="jdbc:postgresql://"+spinner.getSelectedItem().toString()+":5432/irkutsk";
        String user=userEdit.getText().toString();
        String pass=passText.getText().toString();
        textView.setText(url);

        //StartActivity.
        Intent WorkActivity= new Intent(this, WorkActivity.class);
        WorkActivity.putExtra("url", url);
        WorkActivity.putExtra("user", user);
        WorkActivity.putExtra("pass", pass);
        textView.setText("Подключение осуществленно");
        startActivity(WorkActivity);
    }
}
