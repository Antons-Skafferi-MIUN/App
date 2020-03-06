package se.miun.dt170.antonsskafferi.ui.table_overview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.TableDialogSharedViewModel;
import se.miun.dt170.antonsskafferi.data.DateConverter;
import se.miun.dt170.antonsskafferi.data.model.Reservation;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.model.RestaurantTable;
import se.miun.dt170.antonsskafferi.data.repository.TableRepository;

/**
 * This is the fullscreen fragment for showing available tables.
 */
public class TableOverviewFragment extends Fragment implements Button.OnClickListener {

    private TableOverviewViewModel mViewModel;
    private View fragmentView;
    private TableDialogSharedViewModel sharedViewModel;
    private int numberOfTables;

    public static TableOverviewFragment newInstance() {
        return new TableOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        fragmentView = inflater.inflate(R.layout.table_overview_fragment, container, false);
        ViewGroup parent = fragmentView.findViewById(R.id.TableOverviewLayout);
        numberOfTables = parent.getChildCount();
        for (int tableIndex = 0; tableIndex < numberOfTables; tableIndex++) { // for each child apply a listener to the childs tableButton

            TableView table = fragmentView.findViewById(R.id.table1 + tableIndex);
            table.setup(tableIndex + 1);

            Button tempButton = table.findViewById(R.id.tableButton);
            tempButton.setOnClickListener(this);
            //TODO ADD TABLES IN LSIT FOR EASY ACCESS LATER.
        }
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TableRepository reservations = new TableRepository();
        reservations.getRestaurantTables(this);

        // Log.i("RESERVATIONS IN TABLEOVERVIEWFRAGMENT", reservations.getValue().toString());

        mViewModel = ViewModelProviders.of(this).get(TableOverviewViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(TableDialogSharedViewModel.class);

    }

    @Override
    public void onClick(View v) { // gets ID for table button.
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
            if (date.compareDates(reservation.getReservationDate(), date.getCurrentTime())) {
                Log.d("Reservation", String.format("Today's reservation: %s", reservation.toString()));
                todaysReservations.add(reservation);
            }
        });

        todaysReservations.forEach(reservation -> {
            TableView table = fragmentView.findViewById(R.id.table1 + Integer.parseInt(reservation.getTableId().getTableId()));
            if(reservation.getTableId().getTableStatus().equals("vacant")) {
                sharedViewModel.setDialogText("Bord " + table.getTableNr());
                table.removeBooking();
            } else {
                table.bookTable();
                sharedViewModel.setDialogText(String.format("Bokat av: %s\nKontakt: %s", reservation.getReservationName(), reservation.getReservationPhone()));
            }
            table.setArrivalTime(date.formatHHMM(reservation.getReservationDate())); //ISO-8601
        });

//        for (int tableIndex = 0; tableIndex < numberOfTables; tableIndex++) {
//            Reservation reservation = todaysReservations.get(tableIndex);
//            RestaurantTable restaurantTable = reservation.getTableId();
//            TableView table = fragmentView.findViewById(R.id.table1 + tableIndex);
//
//            if (restaurantTable.getTableStatus().equals("vacant")) {
//                sharedViewModel.setDialogText("Bord " + table.getTableNr());
//                table.removeBooking();
//            } else {
//                table.bookTable();
//                sharedViewModel.setDialogText("Bokat av: " + reservation.getReservationName() + "\n" + "Kontakt: " + reservation.getReservationPhone());
//            }
//            Log.i("Retrofit RxJava", new Integer(tableIndex).toString());
//            Log.i("GET RESERV DATE", reservation.getReservationDate());
//
//            table.setArrivalTime(date.formatHHMM(reservation.getReservationDate())); //ISO-8601
//        }
    }
}
