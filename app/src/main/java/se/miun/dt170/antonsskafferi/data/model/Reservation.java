package se.miun.dt170.antonsskafferi.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "reservations")
public class Reservation {

    @Element(name = "reservationPhone", required = false)
    private String reservationPhone;

    @Element(name = "reservationId", required = false)
    private String reservationId;

    @Element(name = "tableId")
    private RestaurantTable tableId;

    @Element(name = "reservationDate")
    private String reservationDate;

    @Element(name = "reservationName", required = false)
    private String reservationName;

    public Reservation() {
    }

    /**
     *
     * @param reservationPhone
     * @param tableId
     * @param reservationDate
     * @param reservationName
     */
    public Reservation(String reservationPhone, RestaurantTable tableId, String reservationDate, String reservationName) {
        this.reservationPhone = reservationPhone;
        this.tableId = tableId;
        this.reservationDate = reservationDate;
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
