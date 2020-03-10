package com.example.myapplication.API.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "drinkss")
public class Drinks {

    @ElementList(inline = true)
    private ArrayList<Drink> drinks;

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<Drink> drinks) {
        this.drinks = drinks;
    }

    @Override
    public String toString() {
        return "ClassPojo [drinks = " + drinks + "]";
    }
}
