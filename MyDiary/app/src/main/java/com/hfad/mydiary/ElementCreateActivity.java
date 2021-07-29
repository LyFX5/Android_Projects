 package com.hfad.mydiary;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

 public class ElementCreateActivity extends AppCompatActivity {

     EditText nameIn;
     EditText discriptionIn;
     EditText contentIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_create);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar(); // для кнопки назад
        actionBar.setDisplayHomeAsUpEnabled(true);

        nameIn = (EditText) findViewById(R.id.nameEdit);
        discriptionIn = (EditText) findViewById(R.id.discriptionEdit);
        contentIn = (EditText) findViewById(R.id.contentEdit);

        Intent intent = getIntent();

        if (intent != null) {
            String name = intent.getStringExtra(ElementActivity.nameToCreateAct);
            if (name != null) {
                nameIn.setText(name);
            }
            String discription = intent.getStringExtra(ElementActivity.discriptionToCreateAct);
            if (discription != null) {
                discriptionIn.setText(discription);
            }
            String content = intent.getStringExtra(ElementActivity.contentToCreateAct);
            if (content != null) {
                contentIn.setText(content);
            }
        }
    }

    public void onCreateElement(View view){
        Element element = new Element(nameIn.getText().toString());
        element.setDiscription(discriptionIn.getText().toString());
        element.setContent(contentIn.getText().toString());
        MyDiaryDataBaseHelper.insertElement(new MyDiaryDataBaseHelper(this).getReadableDatabase(),element);
        Intent intent = new Intent(ElementCreateActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
