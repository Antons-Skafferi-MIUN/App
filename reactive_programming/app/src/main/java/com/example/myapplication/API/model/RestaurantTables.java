package com.example.myapplication.API.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "restaurantTabless")
public class RestaurantTables {

    @ElementList(inline = true)
    private ArrayList<RestaurantTable> restaurantTables;

    public ArrayList<RestaurantTable> getRestaurantTables() {
        return restaurantTables;
    }

    public void setRestaurantTables(ArrayList<RestaurantTable> restaurantTables) {
        this.restaurantTables = restaurantTables;
    }

    @Override
    public String toString() {
        return "ClassPojo [restaurantTables = " + restaurantTables + "]";
    }
}
