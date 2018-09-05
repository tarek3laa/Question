package com.example.nextapp.question;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {
    String[] ques;
    TextView t1;
    Button q1,q2,q3,q4;
    //ans ques count ans[0] & ques[0]
    int s = 0;
    String[] ans;
    String[] ans1;
    String[] ans2;
    String[] ans3;
    String RightAnsTest;
    //count iq for any quiz
    double count=0;
    int QuestionCount =2;
    TextView Question;


    private static final long START_TIME = 15000;
    private long mTime = START_TIME;
    private TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        timer = (TextView) findViewById(R.id.tv_timer);
        //ActionBar actionBar=getSupportActionBar();
       // actionBar.setTitle(Html.fromHtml("<font  color='#000000' > Sport</font>"));
       startTimer();

        q1 = (Button) findViewById(R.id.q1);
        q2 = (Button) findViewById(R.id.q2);
        q3 = (Button) findViewById(R.id.q3);
        q4 = (Button) findViewById(R.id.q4);
        t1 = (TextView) findViewById(R.id.t1);
        Question = (TextView) findViewById(R.id.tv_no__question);
        // make textview scrollable
        t1.setMovementMethod(new ScrollingMovementMethod());

        ans = getResources().getStringArray(R.array.RightAns);
        ques = getResources().getStringArray(R.array.Science);
        ans1 = getResources().getStringArray(R.array.wrong_ans1);
        ans2 = getResources().getStringArray(R.array.Wrongans2);
        ans3 = getResources().getStringArray(R.array.Wrongans3);
        RightAnsTest = ans[s];

        t1.setText(ques[s]);
      //  RandomModule();
        s=s+1;


    }

    private void startTimer() {
        mTime = START_TIME;
        timer();

    }

    private void timer() {

         new CountDownTimer(mTime, 1000) {
            @Override
            public void onTick(long l) {
                mTime = l;

                int m = (int) (mTime/1000) / 60;
                int s = (int) (mTime/1000) % 60;
                timer.setText(String.format(Locale.getDefault(), "0%2d:%02d", m, s));
            }

            @Override
            public void onFinish() {



            }
        }.start();
    }
    public void module1() {
        q1.setText(ans[s]);
        q2.setText(ans1[s]);
        q3.setText(ans2[s]);
        q4.setText(ans3[s]);
        checkerm1();
    }
    public void module2() {
        q1.setText(ans1[s]);
        q2.setText(ans[s]);
        q3.setText(ans3[s]);
        q4.setText(ans2[s]);
        checkerm2();
    }
    public void module3() {
        q1.setText(ans1[s]);
        q2.setText(ans3[s]);
        q3.setText(ans[s]);
        q4.setText(ans2[s]);
        checkerm3();
    }
    public void module4() {
        q1.setText(ans1[s]);
        q2.setText(ans2[s]);
        q3.setText(ans3[s]);
        q4.setText(ans[s]);
        checkerm4();
    }
    public void RandomModule(){
        Random RandomNumber=new Random();
        int number=RandomNumber.nextInt(4);
        if(number==1){
            module1();
        }else if(number==2){
            module2();
        }else if(number ==3){
            module3();
        }else {
            module4();
        }
    }
    public void checkerm1() {
        q1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      RightAns();
                                  }
                              }
        );
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongAns();
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongAns();
            }
        });
        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongAns();
            }
        });
    }
    public void checkerm2() {
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongAns();
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RightAns();
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongAns();
            }
        });

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongAns();
            }
        });
    }
    public void checkerm3() {
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongAns();
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongAns();
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RightAns();
            }
        });

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongAns();
            }
        });
    }
    public void checkerm4() {
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongAns();
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongAns();
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WrongAns();
            }
        });

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RightAns();
            }
        });
    }
    public  void RightAns(){
        t1.setText(ques[s]);
        RandomModule();
        s = s + 1;
        count +=0.5;
        Question.setText("Question "+ QuestionCount);
        QuestionCount = QuestionCount +1;
        if(s==10){
            Intent intent=new Intent(QuestionActivity.this,LastActivity.class);
            intent.putExtra("IQ",count);

            startActivity(intent);
        }
    }
    public  void WrongAns(){
        t1.setText(ques[s]);
        RandomModule();
        s = s + 1;
        Question.setText("Question "+ QuestionCount);
        QuestionCount = QuestionCount +1;
        if(s==10){
            Intent intent=new Intent(QuestionActivity.this,LastActivity.class);
            intent.putExtra("IQ",count);
            startActivity(intent);
        }
    }

}








