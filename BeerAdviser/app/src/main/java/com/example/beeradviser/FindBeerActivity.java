package com.example.beeradviser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FindBeerActivity extends Activity {

    BeerExpert beerExpert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }

    public void onClickFindBeer(View view){
        Spinner color = (Spinner) findViewById(R.id.color);
        TextView brands = (TextView) findViewById(R.id.brands);
        String beerType = String.valueOf(color.getSelectedItem());
        List <String> choosenBrands = beerExpert.getBrands(beerType);
        StringBuilder formatedList = new StringBuilder();
        for (String brand : choosenBrands){
            formatedList.append(brand).append("\n");
        }
        brands.setText(formatedList);
    }
}
