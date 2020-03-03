package se.miun.dt170.antonsskafferi.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.TableDialogSharedViewModel;
import se.miun.dt170.antonsskafferi.ui.table_overview.TableView;

/**
 * Popup dialog displayed when a {@link se.miun.dt170.antonsskafferi.ui.table_overview.TableView} is clicked.
 * Will navigate you to {@link se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment}
 * or reserve table.
 */
public class TableDialogFragment extends DialogFragment {
    private Button openOrderButton;
    private Button bookingButton;
    private View dialogView;
    private Fragment parent;
    private TableDialogSharedViewModel sharedViewModel;
    private TableView table;
    private Drawable popupAvailableColor;
    private Drawable popupBookedColor;
    private Drawable cancelButtonColor;
    private TextView textDisplay;
    private Button cancelButton;


    @Override
    public void onDestroy() {
        Toast.makeText(getActivity(), "DESTROY WAS CALLED", Toast.LENGTH_SHORT).show();
        super.onDestroy();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        popupAvailableColor = ContextCompat.getDrawable(this.getContext(),R.drawable.green_button_border);
        popupBookedColor = ContextCompat.getDrawable(this.getContext(),R.drawable.red_button_border);
        cancelButtonColor = ContextCompat.getDrawable(this.getContext(),R.drawable.white_button_border);

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        dialogView = layoutInflater.inflate(R.layout.table_dialog_fragment, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);

        sharedViewModel = new ViewModelProvider(requireActivity()).
                get(TableDialogSharedViewModel.class); //gets the shared view model from the associsated fragment.
        //creates a new observers that will update once the shared view model has new data

        //TODO THIS OBSERVER SEEEM TO FAIL TO OBSERVE.
        MutableLiveData<TableView> mutableTable = sharedViewModel.getTable();
        table = mutableTable.getValue(); // TEMP FIX
        mutableTable.observe(this, new Observer<TableView>() {
            @Override
            public void onChanged(TableView s) {
                table = s;
            }
        });

        return builder.create();
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialogView = view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parent = getParentFragment();
        openOrderButton = dialogView.findViewById(R.id.openOrderButton);
        bookingButton = dialogView.findViewById(R.id.bookingButton);
        textDisplay = dialogView.findViewById(R.id.dialogTextDisplay);
        cancelButton = dialogView.findViewById(R.id.cancelButton);

        textDisplay.setText("Bord " + Integer.toString(table.getTableNr()));
        cancelButton.setBackground(cancelButtonColor);

        adjustBookingButton();
        adjustOrderButton();

        cancelButton.setOnClickListener(v ->{
          this.dismiss();
        });
        openOrderButton.setOnClickListener(v -> {
            NavDirections action = TableDialogFragmentDirections.actionTableDialogFragmentToOrderOverviewFragment();
            Navigation.findNavController(parent.getView()).navigate(action);
        });

        bookingButton.setOnClickListener(v -> {
            table.setTableBooked(!table.isTableBooked());
            if(table.isTableBooked()){
            final AlertDialog.Builder enterTimeDialog = new AlertDialog.Builder(this.getContext());
            final EditText time = new EditText(this.getContext());
            time.setInputType(InputType.TYPE_CLASS_NUMBER);
            enterTimeDialog.setTitle("Enter the time for expected customer arrival")
                    .setView(time)
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            table.setArrivalTime(time.getText().toString());
                            adjustBookingButton();
                        }
                    })
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            table.setTableBooked(false);
                            adjustBookingButton();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            table.setTableBooked(false);
                            adjustBookingButton();
                        }
                    })
                    .show();
            }
            else{
                adjustBookingButton();
            }
        });
    }

    private void adjustBookingButton(){
        if(!table.isTableBooked()){ //table is available.
            bookingButton.setBackground(popupAvailableColor); //change to popup
            bookingButton.setText("Boka Bord");
            table.removeBooking();
        }
        else{
            bookingButton.setBackground(popupBookedColor);
            bookingButton.setText("Avboka Bord");
            table.bookTable();
        }
    }

    private void adjustOrderButton(){
        openOrderButton.setText("Ta en order");
        openOrderButton.setBackground(popupAvailableColor);
    }



}


