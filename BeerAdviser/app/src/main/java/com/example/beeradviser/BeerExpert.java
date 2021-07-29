package com.example.beeradviser;

import java.util.*;

public class BeerExpert {

    public List<String> getBrands(String color){
        List<String> brands = new ArrayList<>();
        if (color.equals("amber")) {
            brands.add("Jack Amber");
            brands.add("Red Moose");
        } else {
            brands.add("Jail Pale Ale");
            brands.add("Gout Stout");
        }
        return brands;
    }

}
