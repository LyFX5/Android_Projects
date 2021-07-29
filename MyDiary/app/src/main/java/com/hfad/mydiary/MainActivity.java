package com.hfad.mydiary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static String ID_NAME = "idname";

    private SQLiteDatabase db;
    private Cursor cursor;

    public static void toaster(Context context, CharSequence text){
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.elementsList);

        try{
            SQLiteOpenHelper helper = new MyDiaryDataBaseHelper(this);
            db = helper.getReadableDatabase();

            cursor = db.query("ELEMENTS",
                    new String[]{"_id","NAME"},
                    null,null,null,null,null);

            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);

            listView.setAdapter(listAdapter);

        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this,"Database is not evalble",Toast.LENGTH_SHORT);
            toast.show();
        }

        AdapterView.OnItemClickListener optionsListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, ElementActivity.class);
                intent.putExtra(ID_NAME, (int) id);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(optionsListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add_el:
                Intent intent = new Intent(MainActivity.this,ElementCreateActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
