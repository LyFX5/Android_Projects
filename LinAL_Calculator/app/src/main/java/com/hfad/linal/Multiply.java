package com.hfad.linal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class Multiply extends AppCompatActivity {

    private ShareActionProvider shareActionProvider; // для отправщика

    int dim = 0;

    GridView gridView;

    TextView resT;

    ArrayList<String> elements;

    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiply);
    }

    public void onMultiply(View view){

    }
}
