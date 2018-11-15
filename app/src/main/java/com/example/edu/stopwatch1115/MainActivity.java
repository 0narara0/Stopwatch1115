package com.example.edu.stopwatch1115;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewTime;
    Button start, pause, reset;
    long startTime=0, timeBuff=0, millisecondTime, updateTime=0;
    int minutes=0, seconds=0, milliseconds=0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTime = findViewById(R.id.textViewTime);
        start = findViewById(R.id.buttonStart);
        start.setOnClickListener(this);
        pause = findViewById(R.id.buttonPause);
        pause.setOnClickListener(this);
        reset = findViewById(R.id.buttonReset);
        reset.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonStart:
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);
                start.setText("시작");
                break;

            case R.id.buttonPause:
                timeBuff+=millisecondTime;
                handler.removeCallbacks(runnable);
                pause.setText("정지");
                break;

            case R.id.buttonReset:
                reset.setText("초기화");
                handler.removeCallbacks(runnable);
                startTime = 0;
                timeBuff = 0;
                textViewTime.setText("0:00:000");
                break;

        }

    }
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {

            millisecondTime = SystemClock.uptimeMillis()-startTime;
            updateTime = timeBuff + millisecondTime;
            seconds = (int)(updateTime/1000);
            minutes = seconds/60;
            seconds = seconds % 60;
            milliseconds = (int)(updateTime % 1000);
            textViewTime.setText(minutes+":"+String.format("%02d",seconds)+":"+String.format("%03d",milliseconds));
            handler.postDelayed(this,0);
        }
    };
}
