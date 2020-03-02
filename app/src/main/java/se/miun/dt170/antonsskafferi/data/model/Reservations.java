package se.miun.dt170.antonsskafferi.data.model;

public class Reservations {
    private String reservationPhone;

    private String reservationId;

    private RestaurantTable tableId;

    private String reservationDate;

    private String reservationName;

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
