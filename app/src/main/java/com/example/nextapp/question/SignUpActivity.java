package com.example.nextapp.question;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nextapp.question.Data.DataBase;

public class SignUpActivity extends AppCompatActivity {

    EditText metName,metUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        metName=(EditText)findViewById(R.id.et_name);
        metUser=(EditText)findViewById(R.id.et_user);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.question_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.m_save:
                DataBase dataBase = new DataBase();
                dataBase.setData(metName.getText().toString(),metUser.getText().toString());
        }

        return super.onOptionsItemSelected(item);
    }
}
