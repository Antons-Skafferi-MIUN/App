package se.miun.dt170.antonsskafferi.data.model;

public class Reservationss {
    private Reservations[] reservations;

    public Reservations[] getReservations() {
        return reservations;
    }

    public void setReservations(Reservations[] reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "ClassPojo [reservations = " + reservations + "]";
    }
}
