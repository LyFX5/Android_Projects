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

import java.util.ArrayList;

public class Eqation extends AppCompatActivity {

    private ShareActionProvider shareActionProvider; // для отправщика

    int dim = 0;

    GridView gridView;

    TextView resT;

    ArrayList<String> elements;

    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eqation);

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
            for (int i = 0; i < dim*dim + dim; i++){
                elements.add("");
            }
            res = "";
        }

        gridView = (GridView) findViewById(R.id.gridViewEq);
        gridView.setNumColumns(dim + 1);
        gridView.setAdapter(new EditTextAdapter(this, elements));

        resT = (TextView) findViewById(R.id.resEq);
        resT.setText(res);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        elements = new ArrayList<>();
        for (int i = 0; i < dim*dim + dim; i++){
            elements.add(((EditText) gridView.getChildAt(i)).getText().toString());
        }
        savedInstanceState.putStringArrayList("elements",elements);

        res = resT.getText().toString();
        savedInstanceState.putString("res",res);

        super.onSaveInstanceState(savedInstanceState);
    }

    public void onCulculate(View view){
        try{
            String str = "";
            try{
                str = Colculator.GausMethod(Option.matrixFromGridSystem(gridView,this,dim));

            }catch (NoSolutionExeption no){
                MainActivity.toaster(this,"No Solution");
            }
            catch (InfSolutionExeption inf){
                MainActivity.toaster(this,"Infinity Solution");
            }
            resT.setText(str);

        }catch (NoElemExeption nex){
            MainActivity.toaster(this,"Укажите все элементы");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Привет det");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_save:
                //todo
                MainActivity.toaster(this,"Матрица сохранена");
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
