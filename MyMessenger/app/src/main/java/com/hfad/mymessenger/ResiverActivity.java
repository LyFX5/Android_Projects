package com.hfad.mymessenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ResiverActivity extends Activity {

    public static final String MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resiver);

        Intent intent = getIntent();
        String incomeMes = intent.getStringExtra(MESSAGE);
        TextView textView = (TextView) findViewById(R.id.message);
        textView.setText(incomeMes);
    }
}
