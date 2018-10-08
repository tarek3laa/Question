package com.example.nextapp.question;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LastActivity extends AppCompatActivity {
    TextView iqpoint;
    TextView MSG;
    //menuActivity
    Button MenuAct ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last) ;

        iqpoint=(TextView)findViewById(R.id.iqtext);
        MSG=(TextView)findViewById(R.id.msg);
        MenuAct=(Button) findViewById(R.id.MenuAc);

        Double iqpointstring= getIntent().getDoubleExtra("IQ",0.00) ;
        iqpoint.setText("Your Points is : "+iqpointstring);
        MenuAct.getBackground().setAlpha(0);

        if (iqpointstring>=40) {
            MSG.setText(" You are very good , keep up your level Pro . You got for every question 0.5 point.") ;
        }else if(iqpointstring>=25 && iqpointstring<40) {
            MSG.setText(" You are good but need some focus . You got for every question 0.5 point.") ;
        }else {
            MSG.setText(" Oh!,Your Level is Not Good , Try to improve yourself . You got for every question 0.5 point. ") ;
        }

        MenuAct.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LastActivity.this,MainActivity.class) ;
                startActivity(intent);

            }
        }) ;


    }
}