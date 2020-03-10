package com.example.myapplication.API.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "reservationss")
public class Reservations {

    @ElementList(inline = true)
    private ArrayList<Reservation> reservations;

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "ClassPojo [reservations = " + reservations + "]";
    }
}
