package se.miun.dt170.antonsskafferi.data.model;

public class RestaurantTables {
    private String tableAvailableSeats;

    private String tableTotalSeats;

    private String tableId;

    private String tableStatus;

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
