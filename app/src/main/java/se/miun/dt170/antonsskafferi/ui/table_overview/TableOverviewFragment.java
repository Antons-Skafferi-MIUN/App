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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.TableDialogSharedViewModel;
import se.miun.dt170.antonsskafferi.data.DateConverter;
import se.miun.dt170.antonsskafferi.data.model.Reservation;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
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

    public static TableOverviewFragment newInstance() {
        return new TableOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        fragmentView = inflater.inflate(R.layout.table_overview_fragment, container, false);
        ViewGroup parent = fragmentView.findViewById(R.id.TableOverviewLayout);
        numberOfTables = parent.getChildCount();

        // for each child apply a listener to the childs tableButton
        for (int tableIndex = 0; tableIndex < numberOfTables; tableIndex++) {
            TableView table = fragmentView.findViewById(R.id.table1 + tableIndex);
            table.setup(tableIndex + 1);
            table.setDialogText("Bord " + table.getTableNr());

            Button tempButton = table.findViewById(R.id.tableButton);
            tempButton.setOnClickListener(this);
            //TODO ADD TABLES IN LSIT FOR EASY ACCESS LATER.
        }
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        TableRepository reservations = new TableRepository();
//        reservations.getRestaurantTables(response -> updateFragment(response));

        mViewModel = new ViewModelProvider(this).get(TableOverviewViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(TableDialogSharedViewModel.class);
        tableDialogViewModel = new ViewModelProvider(this).get(TableDialogViewModel.class);

        tableDialogViewModel.getAllReservations().observe(getViewLifecycleOwner(), new Observer<Reservations>() {
            @Override
            public void onChanged(Reservations reservations) {
                updateFragment(reservations);
            }
        });
    }

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
            if (date.compareDates(reservation.getReservationDate(), date.getCurrentTime())) {
                Log.d("Reservation", String.format("Today's reservation: %s", reservation.toString()));
                todaysReservations.add(reservation);
            }
        });

        todaysReservations.forEach(reservation -> {
            TableView table = fragmentView.findViewById(R.id.table1 + (Integer.parseInt(reservation.getTableId().getTableId()) - 1));
            table.addBookedStatus();
            table.setReservationID(reservation.getReservationId());
            table.setDialogText(String.format("Bokat av: %s\nKontakt: %s", reservation.getReservationName(), reservation.getReservationPhone()));
            table.setArrivalTime(date.formatHHMM(reservation.getReservationDate())); //ISO-8601
        });
    }
}
