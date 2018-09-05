package com.example.nextapp.question;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {
    String[] ques;
    TextView mtvQuestion;
    Button q1, q2, q3, q4;


    //ans ques count ans[0] & ques[0]
    int s = 0;
    String[] ans;
    String[] ans1;
    String[] ans2;
    String[] ans3;

    String RightAnsTest;
    //count iq for any quiz
    double Score = 0;
    int QuestionCount = 2;
    TextView mtvQuestionNumber;
    String type = "";

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
        mtvQuestion = (TextView) findViewById(R.id.tv_question);

        mtvQuestionNumber = (TextView) findViewById(R.id.tv_no__question);
        // make textview scrollable
        mtvQuestion.setMovementMethod(new ScrollingMovementMethod());
        Intent intent = getIntent();
        if (intent.hasExtra("Question"))
            type = intent.getStringExtra("Question");

        if (type.equals("general info")) {
            ans = getResources().getStringArray(R.array.RightAns);
            ques = getResources().getStringArray(R.array.general_info);
            ans1 = getResources().getStringArray(R.array.wrong_ans1);
            ans2 = getResources().getStringArray(R.array.Wrongans2);
            ans3 = getResources().getStringArray(R.array.Wrongans3);
            RightAnsTest = ans[s];
        }
        mtvQuestion.setText(ques[s]);
        RandomModule();
        s++;




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

                int m = (int) (mTime / 1000) / 60;
                int s = (int) (mTime / 1000) % 60;
                timer.setText(String.format(Locale.getDefault(), "0%2d:%02d", m, s));
            }

            @Override
            public void onFinish() {

                if (q1.getText().toString().equals(ans[s]))
                    q1.setBackground(getDrawable(R.drawable.button_style_2));
                else if (q2.getText().toString().equals(ans[s]))
                    q2.setBackground(getDrawable(R.drawable.button_style_2));
                else if (q3.getText().toString().equals(ans[s]))
                    q3.setBackground(getDrawable(R.drawable.button_style_2));
                else if (q4.getText().toString().equals(ans[s]))
                    q4.setBackground(getDrawable(R.drawable.button_style_2));
            }
        }.start();
    }



    //TODO : Replace checkerm1(),checkerm2(),checkerm3()And checkerm4() with check()

    public void module1() {
        q1.setText(ans[s]);
        q2.setText(ans1[s]);
        q3.setText(ans2[s]);
        q4.setText(ans3[s]);

        //checkerm1();
        // check();
    }

    public void module2() {
        q1.setText(ans1[s]);
        q2.setText(ans[s]);
        q3.setText(ans3[s]);
        q4.setText(ans2[s]);
      // checkerm2();
        // check();
    }

    public void module3() {
        q1.setText(ans1[s]);
        q2.setText(ans3[s]);
        q3.setText(ans[s]);
        q4.setText(ans2[s]);

       // checkerm3();
        //   check();
    }

    public void module4() {
        q1.setText(ans1[s]);
        q2.setText(ans2[s]);
        q3.setText(ans3[s]);
        q4.setText(ans[s]);

        // checkerm4();
       // check();
    }

    public void RandomModule() {


        //  q1.setBackground(getResources().getDrawable(R.drawable.button_style));
        //  q2.setBackground(getResources().getDrawable(R.drawable.button_style));
        //  q3.setBackground(getResources().getDrawable(R.drawable.button_style));
        //  q4.setBackground(getResources().getDrawable(R.drawable.button_style));


        startTimer();

        Random RandomNumber = new Random();
        int number = RandomNumber.nextInt(4);
        if (number == 1) {
            module1();
        } else if (number == 2) {
            module2();
        } else if (number == 3) {
            module3();
        } else {
            module4();
        }
    }

    public void checkerm1() {
        q1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {

                                      setColor(q1, true);
                                      RightAns();
                                  }
                              }
        );
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q2, false);

                WrongAns();
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q3, false);

                WrongAns();
            }
        });
        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q4, false);


                WrongAns();
            }
        });
    }

    public void checkerm2() {
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q1, false);


                WrongAns();
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q2, true);


                RightAns();
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q3, false);

                WrongAns();
            }
        });

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q4, false);

                WrongAns();
            }
        });
    }

    public void checkerm3() {
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q1, false);


                WrongAns();
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q2, false);

                WrongAns();
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q3, true);
                RightAns();
            }
        });

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q4, false);

                WrongAns();
            }
        });
    }

    public void checkerm4() {
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q1, false);

                WrongAns();
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setColor(q2, false);


                WrongAns();
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q3, false);
                WrongAns();
            }
        });

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(q4, true);
                RightAns();
            }
        });
    }

    public void RightAns() {
        Score += 0.5;

        mtvQuestion.setText(ques[s]);
        s++;

        mtvQuestionNumber.setText("Question " + QuestionCount);
        QuestionCount++;

        RandomModule();
    }

    public void WrongAns() {

        mtvQuestion.setText(ques[s]);
        s++;
        mtvQuestionNumber.setText("Question " + QuestionCount);
        QuestionCount++;
        RandomModule();
    }

    private void setColor(Button button, boolean b) {

        if (b) {
            button.setBackground(getDrawable(R.drawable.button_style_2));
        } else {
            button.setBackground(getDrawable(R.drawable.button_style2));
        }



    }

    void check(){


        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(q1.getText().toString().equals(ans[s])){

                    setColor(q1,true);

                    RightAns();

                }else {
                    setColor(q1,false);
                    if(q2.getText().toString().equals(ans[s])){

                        setColor(q2,true);


                    }else if(q3.getText().toString().equals(ans[s])){

                        setColor(q3,true);


                    }
                    else if(q4.getText().toString().equals(ans[s])){

                        setColor(q4,true);


                    }
                    WrongAns();

                }



            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(q2.getText().toString().equals(ans[s])){

                    setColor(q2,true);
                    RightAns();

                }else {
                    setColor(q2,false);
                     if(q1.getText().toString().equals(ans[s])){

                        setColor(q1,true);
                    }else if(q3.getText().toString().equals(ans[s])){

                         setColor(q3,true);
                     }
                     else if(q4.getText().toString().equals(ans[s])){

                         setColor(q4,true);


                     }
                    WrongAns();

                }



            }

            }
        );
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(q3.getText().toString().equals(ans[s])){

                    setColor(q3,true);
                    RightAns();

                }else {
                    setColor(q3,false);


                    if(q1.getText().toString().equals(ans[s])){

                        setColor(q1,true);
                    }else if(q2.getText().toString().equals(ans[s])){

                        setColor(q2,true);
                    }
                    else if(q4.getText().toString().equals(ans[s])){

                        setColor(q4,true);


                    }
                WrongAns();

                }



            }

            }
        );

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(q4.getText().toString().equals(ans[s])){

                    setColor(q4,true);
                    RightAns();

                }else {
                    setColor(q4,false);



                    if(q1.getText().toString().equals(ans[s])){

                        setColor(q1,true);
                    }else if(q3.getText().toString().equals(ans[s])){

                        setColor(q3,true);
                    }
                    else if(q2.getText().toString().equals(ans[s])){

                        setColor(q2,true);


                    }
                    WrongAns();
                }



            }


        });


    }





    
}


