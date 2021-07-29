package com.hfad.mydiary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class ElementActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private int position;
    private String elName;
    private String elDescription;
    private String elContent;
    public static final String nameToCreateAct = "nameToCreateAct";
    public static final String discriptionToCreateAct = "discriptionToCreateAct";
    public static final String contentToCreateAct = "contentToCreateAct";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar(); // для кнопки назад
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        position = intent.getIntExtra(MainActivity.ID_NAME,0);

        db = new MyDiaryDataBaseHelper(this).getReadableDatabase();

        Cursor cursor = db.query("ELEMENTS",
                new String[]{"NAME","DESCRIPTION","CONTENT"},
                "_id = ?",
                new String[]{Integer.toString(position)},
                null,null,null);

        if (cursor.moveToFirst()){
            elName = cursor.getString(0);
            elDescription = cursor.getString(1);
            elContent = cursor.getString(2);
            ((TextView)findViewById(R.id.name)).setText(elName);
            ((TextView)findViewById(R.id.discription)).setText(elDescription);
            ((TextView)findViewById(R.id.content)).setText(elContent);
            cursor.close();
            db.close();
        }
    }

    public void onEdit(View view){
        db = new MyDiaryDataBaseHelper(this).getReadableDatabase();
        MyDiaryDataBaseHelper.delateElement(db,position);
        db.close();
        Intent intent = new Intent(ElementActivity.this,ElementCreateActivity.class);
        intent.putExtra(nameToCreateAct,elName);
        intent.putExtra(discriptionToCreateAct,elDescription);
        intent.putExtra(contentToCreateAct,elContent);
        startActivity(intent);
        MainActivity.toaster(this,"Элемент Удалён");
    }

    public void onDelete(View view){
        db = new MyDiaryDataBaseHelper(this).getReadableDatabase();
        MyDiaryDataBaseHelper.delateElement(db,position);
        db.close();
        Intent intent = new Intent(ElementActivity.this,MainActivity.class);
        startActivity(intent);
        MainActivity.toaster(this,"Элемент Удалён");
    }
}
