package com.hfad.linal;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String Dim = "dim";

    public static void toaster( Context context, CharSequence text){
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }

    public int getDim(){
        int d = 0;
        String dim = ((EditText) findViewById(R.id.dim)).getText().toString();
        if(dim.equals("")){
            toaster(this,"Введите размерность");
        }else {
                d = Integer.valueOf((dim));
        }
        return d;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<Option> optionAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,Option.options);

        ListView listView = (ListView) findViewById(R.id.options);

        listView.setAdapter(optionAdapter);

        AdapterView.OnItemClickListener optionsListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int dim = getDim();
                    switch (position) {
                        case 0:
                            if (dim != 0) {
                                Intent intent1 = new Intent(MainActivity.this, Determ.class);
                                intent1.putExtra(Dim, dim);
                                startActivity(intent1);
                            }
                            break;
                        case 1:
                            if (dim != 0) {
                                Intent intent2 = new Intent(MainActivity.this, Invers.class);
                                intent2.putExtra(Dim, dim);
                                startActivity(intent2);
                            }
                            break;
                        case 2:
                            if (dim != 0) {
                                Intent intent3 = new Intent(MainActivity.this, Eqation.class);
                                intent3.putExtra(Dim, dim);
                                startActivity(intent3);
                            }
                            break;
                        case 3:
                            Intent intent4 = new Intent(MainActivity.this, Multiply.class);
                            startActivity(intent4);
                            break;
                        case 4:
                            Intent intent5 = new Intent(MainActivity.this,Calc.class);
                            startActivity(intent5);
                            break;
                    }
            }
        };

        listView.setOnItemClickListener(optionsListener);

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            //todo
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
