package com.hfad.duck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCheck(View view){
        boolean checked = ((CheckBox) view).isChecked();
    }

    public void toggleButton(View view){
        boolean on = ((ToggleButton) view).isChecked();

        if (on){
            //on
        }else{
            //off
        }
    }
}
