package com.example.nextapp.question;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nextapp.question.Data.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LastActivity extends AppCompatActivity {
    TextView MSG;
    //menuActivity
    Button MenuAct ;
    ImageView imageView;
    TextView userback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last) ;
        userback=(TextView)findViewById(R.id.userback);

        MSG=(TextView)findViewById(R.id.msg);
        MenuAct=(Button) findViewById(R.id.MenuAc);
        imageView=(ImageView) findViewById(R.id.circleimageback);
        String locationfile =  MainActivity.sharedPreferences.getString(User.IMAGE_PATH_KEY,null) ;

 // profile photoback
        profilephotoback(locationfile,imageView) ;

       userback.setText(User.getUserName());

        Double iqpointstring= getIntent().getDoubleExtra("IQ",0.00) ;
        MenuAct.getBackground().setAlpha(0);

        if (iqpointstring>=40) {
            MSG.setText(" You are very good , keep up your level Pro . You got for every question 0.5 point : "+iqpointstring) ;
        }else if(iqpointstring>=25 && iqpointstring<40) {
            MSG.setText(" You are good but need some focus . You got for every question 0.5 point : "+iqpointstring) ;
        }else {
            MSG.setText(" Oh ! ,Your Level is Not Good , Try to improve yourself . You got for every question  0.5  point , so your score is : "+iqpointstring) ;
        }

        MenuAct.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LastActivity.this,MainActivity.class) ;
                startActivity(intent);
                finish();


            }
        }) ;

    }
    private  void profilephotoback(String Location, ImageView imageView)
    {

        try {
            File filepath=new File(Location, "profile.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(filepath));

            imageView.setImageBitmap(bitmap) ;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}