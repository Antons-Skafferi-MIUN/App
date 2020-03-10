package com.example.myapplication.API.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "reservations")
public class Reservation {

    @Element(name = "reservationId", required = false)
    private String reservationId; // auto-increment, don't specify this in constructor!

    @Element(name = "tableId")
    private RestaurantTable tableId;

    @Element(name = "reservationDate")
    private String reservationDate;

    @Element(name = "reservationPhone")
    private String reservationPhone;

    @Element(name = "reservationName", required = false)
    private String reservationName;

    /**
     * Only used for serialization of XML to object for Retrofit!
     */
    public Reservation() {
    }

    /**
     * Use this constructor when you're doing a POST request.
     *
     * @param tableId          a {@link RestaurantTable} with only an ID as it's constructor
     * @param reservationDate  use {@link se.miun.dt170.antonsskafferi.data.DateConverter} getCurrentTime() formatted as ISO-8601
     * @param reservationPhone customer phone number
     * @param reservationName  customer name
     */
    public Reservation(@NotNull RestaurantTable tableId, @NotNull String reservationDate, @NotNull String reservationPhone, @Nullable String reservationName) {
        this.tableId = tableId;
        this.reservationDate = reservationDate;
        this.reservationPhone = reservationPhone;
        this.reservationName = reservationName;
    }

    public String getReservationPhone() {
        return reservationPhone;
    }

    public void setReservationPhone(String reservationPhone) {
        this.reservationPhone = reservationPhone;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public RestaurantTable getTableId() {
        return tableId;
    }

    public void setTableId(RestaurantTable tableId) {
        this.tableId = tableId;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    @Override
    public String toString() {
        return "ClassPojo [reservationPhone = " + reservationPhone + ", reservationId = " + reservationId + ", tableId = " + tableId + ", reservationDate = " + reservationDate + ", reservationName = " + reservationName + "]";
    }
}
