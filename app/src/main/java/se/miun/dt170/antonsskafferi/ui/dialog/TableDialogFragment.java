package se.miun.dt170.antonsskafferi.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.TableDialogSharedViewModel;

/**
 * Popup dialog displayed when a {@link se.miun.dt170.antonsskafferi.ui.table_overview.table_button.TableButtonFragment} is clicked.
 * Will navigate you to {@link se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment}
 * or reserve table.
 */
public class TableDialogFragment extends DialogFragment {
    private Button openOrderButton;
    private Button bookingButton;
    private NumberPicker numberPicker;
    private View dialogView;
    private Fragment parent;
    private int numberOfSeats;
    private int green = Color.parseColor("#39FF14");
    private int red = Color.parseColor("#FF0000");
    private TableDialogSharedViewModel sharedViewModel;

    @Override
    public void onDestroy() {
        Toast.makeText(getActivity(), "DESTROY WAS CALLED", Toast.LENGTH_SHORT).show();
        super.onDestroy();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        dialogView = layoutInflater.inflate(R.layout.table_dialog_fragment, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);

        sharedViewModel = new ViewModelProvider(requireActivity()).
                get(TableDialogSharedViewModel.class); //gets the shared view model from the associsated fragment.
        //creates a new observers that will update once the shared view model has new data
        MutableLiveData<String> tableColor = sharedViewModel.getTableColor();
        tableColor.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), "I LIKE TO CRASH", Toast.LENGTH_SHORT).show();
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
        numberPicker = dialogView.findViewById(R.id.availableSeats);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(5);
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> numberOfSeats = newVal);


        adjustBookingButton();
        adjustOrderButton();


                openOrderButton.setOnClickListener(v -> {
                    NavDirections action = TableDialogFragmentDirections.actionTableDialogFragmentToOrderOverviewFragment();
                    Navigation.findNavController(parent.getView()).navigate(action);
                });

        bookingButton.setOnClickListener(v -> {

        });

    }

    private void adjustBookingButton(){
        if(sharedViewModel.getTableColor().getValue().equals("Red")){
            bookingButton.setBackgroundColor(green);
            bookingButton.setText("Boka Bord");
        }
        else{
            bookingButton.setBackgroundColor(red);
            bookingButton.setText("Avboka Bord");
        }
    }

    private void adjustOrderButton(){
        if(sharedViewModel.getIsTableOpen().getValue() == true){
            openOrderButton.setText("Ta en order");
        }
        else{
            openOrderButton.setText("Öppna bord");

        }
    }



}


