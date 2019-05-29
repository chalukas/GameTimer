package com.example.gametimer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private int startTimeInMilli = 30000;

    private TextView textViewCountdown;
    private Button buttonStartPause;
    private Button buttonReset;
    private Button nextButton;

    private CountDownTimer countDownTimer;

    private boolean timerRunning;

    private int timeLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().hasExtra("com.example.gametimer.secondsPass")) {
            startTimeInMilli = getIntent().getExtras().getInt("com.example.gametimer.secondsPass") * 1000;
        }
        timeLeft = startTimeInMilli;

        textViewCountdown = findViewById(R.id.textViewCountdown);
        buttonStartPause = findViewById(R.id.buttonStartPause);
        buttonReset = findViewById(R.id.button_reset);
        nextButton = findViewById(R.id.nextButton);

        buttonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });


        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetUpTimer();
            }
        });

        updateCountdownText();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
                resetTimer();
                startTimer();
            }
        });




    }

    private void startTimer() {



        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = (int) millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                textViewCountdown.setText("Next");
                buttonStartPause.setText("start");
                //buttonStartPause.setVisibility(View.INVISIBLE);
                //buttonReset.setVisibility(View.VISIBLE);
            }
        }.start();
        timerRunning = true;
        buttonStartPause.setText("pause");
        //buttonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        buttonStartPause.setText("Start");
        //buttonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        timeLeft = startTimeInMilli;
        updateCountdownText();
        //buttonReset.setVisibility(View.INVISIBLE);
        //buttonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountdownText() {
        int minutes = (timeLeft / 1000) / 60;
        int seconds = (timeLeft / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountdown.setText(timeLeftFormatted);
    }

    private void resetUpTimer() {
        Intent reset = new Intent(getApplicationContext(), TimerSetupActivity.class);
        startActivity(reset);
    }
}
