package com.example.myapplication.API.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "restaurantTables")
public class RestaurantTable {

    @Element(name = "tableId")
    private String tableId;

    @Element(name = "tableAvailableSeats", required = false)
    private String tableAvailableSeats;

    @Element(name = "tableTotalSeats", required = false)
    private String tableTotalSeats;

    @Element(name = "tableStatus", required = false)
    private String tableStatus;

    /**
     * Only used for serialization of XML to object for Retrofit!
     */
    public RestaurantTable() {
    }

    /**
     * Use this constructor when you're doing a POST request.
     *
     * @param tableId
     */
    public RestaurantTable(String tableId) {
        this.tableId = tableId;
    }

    public String getTableAvailableSeats() {
        return tableAvailableSeats;
    }

    public void setTableAvailableSeats(String tableAvailableSeats) {
        this.tableAvailableSeats = tableAvailableSeats;
    }

    public String getTableTotalSeats() {
        return tableTotalSeats;
    }

    public void setTableTotalSeats(String tableTotalSeats) {
        this.tableTotalSeats = tableTotalSeats;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }

    @Override
    public String toString() {
        return "ClassPojo [tableAvailableSeats = " + tableAvailableSeats + ", tableTotalSeats = " + tableTotalSeats + ", tableId = " + tableId + ", tableStatus = " + tableStatus + "]";
    }
}
