package com.hfad.linal;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Invers extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;

    GridView gridView;

    int dim;

    ArrayList<String> elements;

    TextView resT;

    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invers);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar(); // для кнопки назад
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        dim = intent.getIntExtra(MainActivity.Dim,0);

        if (savedInstanceState != null){
            elements = savedInstanceState.getStringArrayList("elements");
            res = savedInstanceState.getString("res");
        }else {
            elements  = new ArrayList<String>();
            for (int i = 0; i < dim*dim; i++){
                elements.add("");
            }
        }

        gridView = (GridView) findViewById(R.id.gridViewInvers);
        gridView.setNumColumns(dim);
        gridView.setAdapter(new EditTextAdapter(this, elements));

        resT = (TextView) findViewById(R.id.resInvers);
        resT.setText(res);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        elements = new ArrayList<>();
        for (int i = 0; i < dim*dim; i++){
            elements.add(((EditText) gridView.getChildAt(i)).getText().toString());
        }
        savedInstanceState.putStringArrayList("elements",elements);

        res = resT.getText().toString();
        savedInstanceState.putString("res",res);

        super.onSaveInstanceState(savedInstanceState);
    }

    public void onCount(View view){
        double[][] matrix = new double[dim][dim];
        try{
            matrix = Option.matrixFromGrid(gridView,this,dim);
        }catch (NoElemExeption nex){
            MainActivity.toaster(this,"Укажите все элементы");
        }

        matrix = Colculator.oppositeMatrix(matrix);

        if(matrix == null){
            MainActivity.toaster(this,"Матрица не обратима");
            return;
        }else {
            String s = "";
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    s = s + Option.rounder(matrix[i][j]) + "  ";
                }
                s = s + "\n";
            }
            resT.setText(s);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Привет invers");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_save:
                //todo
                CharSequence text = "Матрица Сохранена";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(this,text,duration);
                toast.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setShareActionIntent(String text){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        shareActionProvider.setShareIntent(intent);
    }
}
