package se.miun.dt170.antonsskafferi.ui.dialog;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.repository.OrderRepository;
import se.miun.dt170.antonsskafferi.data.repository.ReservationRepository;

public class BookingDialogViewModel extends ViewModel {
    private  ReservationRepository reservationRepository;
    private MutableLiveData<Reservations> allReservations;

    public BookingDialogViewModel(){
        reservationRepository = new ReservationRepository();

    }   
    public MutableLiveData<Reservations> getAllReservations() {
        if(allReservations == null){
            allReservations = reservationRepository.getAllReservations();
        }
        return  allReservations;
    }
    public void updateReservations(){
        Reservations reservations = reservationRepository.getAllReservations().getValue();
        allReservations.setValue(reservations);
    }


}
