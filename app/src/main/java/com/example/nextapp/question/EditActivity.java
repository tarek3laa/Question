package com.example.nextapp.question;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nextapp.question.Data.User;
import com.example.nextapp.question.Data.Users;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditActivity extends AppCompatActivity {
    EditText editname;
    EditText editemail;
    EditText editpass;
    Button savebu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);//findview
        editemail=(EditText)findViewById(R.id.editemail);
        editname=(EditText)findViewById(R.id.editname);
        editpass=(EditText)findViewById(R.id.editpass);
        savebu=(Button)findViewById(R.id.savebutton);


        editname.setText(User.getName());
        editemail.setText(User.getEmail());

        savebu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String namestring=editname.getText().toString();
                final String emailstring=editemail.getText().toString();
                String passstring =editpass.getText().toString();
                User.setName(namestring);
                User.setEmail(emailstring);

                DocumentReference documentReference= FirebaseFirestore.getInstance().collection(Users.collectionReference).document(User.getUserName());

                documentReference.update("name",namestring);
                documentReference.update("email",emailstring);
                documentReference.update("password",passstring);
                finish();

            }
        });

    }
}
