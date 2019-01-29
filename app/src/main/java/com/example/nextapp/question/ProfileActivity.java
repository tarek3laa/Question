package com.example.nextapp.question;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nextapp.question.Data.User;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity  {

    private static final int SELECT_IMAGE = 25 ;
    private TextView name,email,rank,score ,user;
    FloatingActionButton fb;
    CircleImageView imageView ;
    FloatingActionButton editprofile;
    private AdView mAdView;
    private android.support.v7.app.ActionBar actionBar ;
    CircleImageView logout ;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_profile);


         actionBar = this.getSupportActionBar();
        if(actionBar!=null)actionBar.setDisplayHomeAsUpEnabled(true);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) ;





        //ad mob
        MobileAds.initialize(this, User.APP_ID) ;
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build() ;
        mAdView.loadAd(adRequest) ;

        name=(TextView)findViewById(R.id.tv_pa_name);
        email=(TextView)findViewById(R.id.tv_pa_email) ;
        user=(TextView)findViewById(R.id.tv_pa_user) ;

        rank=(TextView)findViewById(R.id.tv_pa_rank);
        score=(TextView)findViewById(R.id.tv_pa_score) ;

        rank.setText(String.valueOf(User.getRank()));
        score.setText(String.valueOf(User.getScore()));
        user.setText(String.valueOf(User.getUserName()));

        name.setText(User.getName());
        email.setText(User.getEmail());

        logout=(CircleImageView) findViewById(R.id.logout) ;

        imageView=(CircleImageView) findViewById(R.id.ci_profile) ;

       //logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ProfileActivity.this,MainActivity.class);

            }
        });


        // set image button
        fb=(FloatingActionButton)findViewById(R.id.floatingActionButton) ;

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // intent for open gallery and return selected image
                Intent intent = new Intent() ;
                intent.setType("image/*") ;
                intent.setAction(Intent.ACTION_GET_CONTENT) ;//
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE) ;

            }
        });


        editprofile=(FloatingActionButton)findViewById(R.id.editfloat);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,EditActivity.class);
                startActivity(intent);
            }
        }) ;


    }




    /**
     * this method calling after the user choose an image
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data)
     {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE)
         {
            if (resultCode == Activity.RESULT_OK)
             {
                if (data != null)
                 {
                    try
                     {

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                        imageView.setImageBitmap(bitmap);
                        //save image to app folder and put this path in sharedPreferences
                        MainActivity.putsharedPreferences(saveToInternalStorage(bitmap).toString(),User.IMAGE_PATH_KEY);

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
            } else if (resultCode == Activity.RESULT_CANCELED)
             {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
         }
    }

    /**
     *
     * @param bitmapImage
     * @return
     */
    @NonNull
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext()) ;
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE) ;
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace() ;
            }
        }
        return directory.getAbsolutePath();
    }

    /**
     *
     * @param path
     */
    private  void loadImageFromStorage(String path,CircleImageView imageView)
    {

        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

           imageView.setImageBitmap(b) ;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume(){
        super.onResume();

       String path =  MainActivity.sharedPreferences.getString(User.IMAGE_PATH_KEY,null);
       loadImageFromStorage(path,imageView);
        name.setText(User.getName());
        email.setText(User.getEmail());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)  {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }



}
