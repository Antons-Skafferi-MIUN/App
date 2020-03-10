package com.example.myapplication.API.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "foodss")
public class Foods {

    @ElementList(inline = true)
    private ArrayList<Food> foods;

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "ClassPojo [foods = " + foods + "]";
    }
}
