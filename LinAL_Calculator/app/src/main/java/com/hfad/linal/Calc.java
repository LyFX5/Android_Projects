package com.hfad.linal;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Calc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar(); // для кнопки назад
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onCalc(View view){
        String expression = ((EditText)findViewById(R.id.editExpr)).getText().toString();
        double res = Option.doubleFromExpression(expression);
        ((TextView)findViewById(R.id.res)).setText(String.valueOf(res));
    }
}
