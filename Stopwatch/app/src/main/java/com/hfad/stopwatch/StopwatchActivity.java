package com.hfad.stopwatch;

import android.os.Handler;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends Activity {

    private int seconds;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }


    public void onStart(View view){
        running = true;
    }

    public void onStop(View view){
        running = false;
    }

    public void onReset(View view){
        running = false;
        seconds = 0;
    }

    private void runTimer(){
        final TextView timerText = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int mins = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,mins,secs);
                timerText.setText(time);
                if (running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

}
