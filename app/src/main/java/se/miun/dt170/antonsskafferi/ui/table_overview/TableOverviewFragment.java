package se.miun.dt170.antonsskafferi.ui.table_overview;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.TableDialogSharedViewModel;
import se.miun.dt170.antonsskafferi.data.utility.DateConverter;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Reservation;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.ui.dialog.TableDialogViewModel;

/**
 * This is the fullscreen fragment for showing available tables.
 */
public class TableOverviewFragment extends Fragment implements Button.OnClickListener {

    private TableOverviewViewModel mViewModel;
    private View fragmentView;
    private TableDialogSharedViewModel sharedViewModel;
    private int numberOfTables;
    private TableDialogViewModel tableDialogViewModel;
    private ApiService mAPIService;
    private CountDownTimer countDownTimer;

    public static TableOverviewFragment newInstance() {
        return new TableOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        fragmentView = inflater.inflate(R.layout.table_overview_fragment, container, false);
        ViewGroup parent = fragmentView.findViewById(R.id.TableOverviewLayout);
        numberOfTables = parent.getChildCount();
        mAPIService = ApiUtils.getAPIService();

        // for each child apply a listener to the childs tableButton
        for (int tableIndex = 0; tableIndex < numberOfTables; tableIndex++) {
            TableView table = fragmentView.findViewById(R.id.table1 + tableIndex);
            table.setup(tableIndex + 1);
            table.setDialogText("Bord " + table.getTableNr());

            checkForOrders(table);


            Button tempButton = table.findViewById(R.id.tableButton);
            tempButton.setOnClickListener(this);
            //TODO ADD TABLES IN LSIT FOR EASY ACCESS LATER.
        }
        return fragmentView;
    }

    private void checkForOrders(TableView tableToCheck) {

        mAPIService.getOrderRows().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderRows>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Retrofit RxJava", e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(OrderRows response) {
                        response.getOrderRows().forEach(orderRow -> {
                            String orderTableID = orderRow.getOrderId().getTableId().getTableId();

                            if (Integer.parseInt(orderTableID) == tableToCheck.getTableNr()) {
                                if (!tableToCheck.getDialogText().contains(" har gäster.")) {
                                    tableToCheck.addOccupiedStatus();
                                    tableToCheck.setDialogText(tableToCheck.getDialogText() + " har gäster.");
                                }


                            }
                        });
                        Log.i("Retrofit RxJava", "get submitted to API." + response.toString());
                    }
                });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(TableOverviewViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(TableDialogSharedViewModel.class);
        tableDialogViewModel = new ViewModelProvider(requireActivity()).get(TableDialogViewModel.class);

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        tableDialogViewModel = new ViewModelProvider(requireActivity()).get(TableDialogViewModel.class);
//
//        // start a timer that ends in 292.5 million years
//        countDownTimer = new CountDownTimer(Long.MAX_VALUE, 2000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                tableDialogViewModel.getAllReservations().observe(getViewLifecycleOwner(), new Observer<Reservations>() {
//                    @Override
//                    public void onChanged(Reservations reservations) {
//                        updateFragment(reservations);
//                    }
//                });
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        };
//        countDownTimer.start();
//    }

    // gets ID for table button.
    @Override
    public void onClick(View v) {
        TableView table = (TableView) v.getParent();
        sharedViewModel.setTable(table);
        NavDirections action = TableOverviewFragmentDirections.actionTableOverviewFragmentToTableDialogFragment();
        Navigation.findNavController(getView()).navigate(action);
    }

    public void updateFragment(Reservations tablesReservations) {
        //TODO LOOP THROUGH ALL RESERVATIONS AND SET THE TABLES FOR CURRENT DATE.
        // TODO ADD NAME AND PHONE TO TABLE AND MAKE IT MUTABLE
        DateConverter date = new DateConverter();
        ArrayList<Reservation> todaysReservations = new ArrayList<>();
        Log.d("Reservation Repo", "" + tablesReservations.getReservations().size());

        // Find today's reservations
        tablesReservations.getReservations().forEach(reservation -> {
            // Reset tables
            TableView table = fragmentView.findViewById(R.id.table1 + (Integer.parseInt(reservation.getTableId().getTableId()) - 1));
            table.removeBookedStatus();
            table.setReservationID(reservation.getReservationId());
            table.setDialogText(String.format("Bord %s", table.getTableNr()));
            table.setArrivalTime(""); //ISO-8601
            table.checkForOrders(); // Update the table's variable hasOrders to true or false

            if (date.compareDates(reservation.getReservationDate(), date.getCurrentTime())) {
                Log.d("Reservation", String.format("Today's reservation: %s", reservation.toString()));
                todaysReservations.add(reservation);
            }
        });

        // Add booked status
        todaysReservations.forEach(reservation -> {
            TableView table = fragmentView.findViewById(R.id.table1 + (Integer.parseInt(reservation.getTableId().getTableId()) - 1));
            table.addBookedStatus();
            table.setReservationID(reservation.getReservationId());
            table.setDialogText(String.format("Bokat av: %s\nKontakt: %s", reservation.getReservationName(), reservation.getReservationPhone()));
            table.setArrivalTime(date.formatHHMM(reservation.getReservationDate())); //ISO-8601
        });
    }
}
