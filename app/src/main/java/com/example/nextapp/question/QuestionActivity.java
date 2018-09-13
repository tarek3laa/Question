package com.example.nextapp.question;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nextapp.question.Data.User;

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
    boolean onClick=false,trueAns=false;
    String RightAnsTest;
    //count iq for any quiz
    double count ;
    int QuestionCount = 2;
    TextView Question;
    String type = "";
    CountDownTimer countDownTimer;
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

        Question = (TextView) findViewById(R.id.tv_no__question);
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




    }

    private void startTimer() {

        mTime = START_TIME;
        timer();
    }

    private void timer() {

       countDownTimer = new CountDownTimer(mTime, 1000) {
            @Override
            public void onTick(long l) {
                mTime = l;
                if((mTime/1000)<=5)timer.setTextColor(getResources().getColor(R.color.wrong_answer));
                else timer.setTextColor(getResources().getColor(R.color.color3));
                if((mTime/1000)>=10)
                timer.setText("00:"+String.valueOf(mTime/1000));
                else
                    timer.setText("00:0"+String.valueOf(mTime/1000));


            }

            @Override
            public void onFinish() {

                System.out.println("on Finish");
                if(q1.getText().toString().equals(ans[s]))
                    setColor(q1,true);
                else if(q2.getText().toString().equals(ans[s]))
                    setColor(q2,true);
                else if(q3.getText().toString().equals(ans[s]))
                    setColor(q3,true);
                else if(q4.getText().toString().equals(ans[s]))
                    setColor(q4,true);
                trueAns=false;
                timer2(5);






            }
        }.start();
    }

    private void timer2(int sec){
        sec*=1000;
            new CountDownTimer(sec,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                onClick=false;
                startTimer();


                    if (trueAns) RightAns();
                    else WrongAns();




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

    public void RandomModule() {
            q1.setBackground(getResources().getDrawable(R.drawable.button_style));
            q2.setBackground(getResources().getDrawable(R.drawable.button_style));
            q3.setBackground(getResources().getDrawable(R.drawable.button_style));
            q4.setBackground(getResources().getDrawable(R.drawable.button_style));
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

                                      if(!onClick) {
                                          setColor(q1, true);

                                          if (countDownTimer != null)
                                              countDownTimer.cancel();
                                          trueAns = true;
                                          onClick = true;

                                          if(q1.getText().toString().equals(ans[s]))
                                              setColor(q1,true);
                                          else if(q2.getText().toString().equals(ans[s]))
                                              setColor(q2,true);
                                          else if(q3.getText().toString().equals(ans[s]))
                                              setColor(q3,true);
                                          else if(q4.getText().toString().equals(ans[s]))
                                              setColor(q4,true);
                                          trueAns=false;

                                          timer2(5);
                                      }

                                  }
                              }
        );
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!onClick) {

                    setColor(q2, false);
                    if (countDownTimer != null)
                        countDownTimer.cancel();
                    trueAns = false;
                    onClick = true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;

                    timer2(5);
                }

            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!onClick) {
                    setColor(q3, false);
                    if (countDownTimer != null)
                        countDownTimer.cancel();
                    trueAns = false;
                    onClick = true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;

                    timer2(5);
                }

            }
        });
        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(!onClick) {
                        setColor(q4, false);
                        if (countDownTimer != null)
                            countDownTimer.cancel();
                        trueAns = false;
                        onClick = true;

                        if(q1.getText().toString().equals(ans[s]))
                            setColor(q1,true);
                        else if(q2.getText().toString().equals(ans[s]))
                            setColor(q2,true);
                        else if(q3.getText().toString().equals(ans[s]))
                            setColor(q3,true);
                        else if(q4.getText().toString().equals(ans[s]))
                            setColor(q4,true);
                        trueAns=false;

                        timer2(5);
                    }

            }
        });
    }

    public void checkerm2() {
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!onClick) {
                    setColor(q1, false);
                    if (countDownTimer != null)
                        countDownTimer.cancel();
                    trueAns = false;
                    onClick = true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;

                    timer2(5);
                }

            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!onClick) {
                    setColor(q2, true);
                    if (countDownTimer != null)
                        countDownTimer.cancel();
                    trueAns = true;
                    onClick = true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;

                    timer2(5);
                }
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!onClick) {
                    setColor(q3, false);
                    if (countDownTimer != null)
                        countDownTimer.cancel();
                    trueAns = false;
                    onClick = true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;


                    timer2(5);

                }
            }
        });

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!onClick) {
                    setColor(q4, false);
                    if (countDownTimer != null)
                        countDownTimer.cancel();
                    trueAns = false;
                    onClick = true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;

                    timer2(5);
                }
            }
        });
    }

    public void checkerm3() {
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!onClick){
                setColor(q1, false);
                if (countDownTimer!=null)
                    countDownTimer.cancel();
                trueAns=false;
                onClick=true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;

                timer2(5);
            }
            }

        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!onClick) {
                    setColor(q2, false);
                    if (countDownTimer != null)
                        countDownTimer.cancel();
                    trueAns = false;
                    onClick = true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;

                    timer2(5);
                }
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!onClick) {
                    setColor(q3, true);
                    if (countDownTimer != null)
                        countDownTimer.cancel();
                    trueAns = true;
                    onClick = true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;

                    timer2(5);
                }
            }
        });

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!onClick) {
                    setColor(q4, false);
                    if (countDownTimer != null)
                        countDownTimer.cancel();
                    trueAns = false;
                    onClick = true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;

                    timer2(5);
                }
            }
        });
    }

    public void checkerm4() {
        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!onClick) {
                    setColor(q1, false);
                    if (countDownTimer != null)
                        countDownTimer.cancel();
                    trueAns = false;
                    onClick = true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;

                    timer2(5);

                }
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!onClick) {
                    setColor(q2, false);
                    if (countDownTimer != null)
                        countDownTimer.cancel();
                    trueAns = false;
                    onClick = true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;

                    timer2(5);
                }
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!onClick) {
                    setColor(q3, false);
                    if (countDownTimer != null)
                        countDownTimer.cancel();
                    trueAns = false;
                    onClick = true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;

                    timer2(5);
                }
            }
        });

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!onClick) {
                    setColor(q4, true);
                    if (countDownTimer != null)
                        countDownTimer.cancel();
                    trueAns = true;
                    onClick = true;

                    if(q1.getText().toString().equals(ans[s]))
                        setColor(q1,true);
                    else if(q2.getText().toString().equals(ans[s]))
                        setColor(q2,true);
                    else if(q3.getText().toString().equals(ans[s]))
                        setColor(q3,true);
                    else if(q4.getText().toString().equals(ans[s]))
                        setColor(q4,true);
                    trueAns=false;

                    timer2(5);
                }
            }
        });
    }

    public void RightAns() {
              count += 0.5;
             s++;
            mtvQuestion.setText(ques[s]);

            Question.setText("Question " + QuestionCount);
            QuestionCount++;
             RandomModule();
            if (s == 107) {
                Intent intent = new Intent(QuestionActivity.this, LastActivity.class);
                intent.putExtra("IQ", count);

                startActivity(intent);
            }

    }
    public void WrongAns() {
        s++;
            mtvQuestion.setText(ques[s]);

            Question.setText("Question " + QuestionCount);
            QuestionCount++;
        RandomModule();
            if (s == 107) {
                Intent intent = new Intent(QuestionActivity.this, LastActivity.class);
                intent.putExtra("IQ", count);
                startActivity(intent);
            }



    }
    private void setColor(Button button, boolean b) {

        if (b) {
            button.setBackground(getDrawable(R.drawable.button_style_2));
        } else {
            button.setBackground(getDrawable(R.drawable.button_style2));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}


