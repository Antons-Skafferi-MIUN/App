package se.miun.dt170.antonsskafferi.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.util.Calendar;
import java.util.Set;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.TableDialogSharedViewModel;
import se.miun.dt170.antonsskafferi.activity.RestaurantSharedViewModel;
import se.miun.dt170.antonsskafferi.data.APIWrappers.DeleteWrapper;
import se.miun.dt170.antonsskafferi.data.repository.OrderRepository;
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
    private int CancelButtonTextColor;
    private TextView textDisplay;
    private Button cancelButton;
    private String dialogText;
    private OrderRepository orderRepository;
    private DeleteWrapper deleteWrapper;
    private Calendar calender;
    private Context context;
    private MutableLiveData<TableView> mutableTable;
    private TableDialogViewModel tableDialogViewModel;
    private Drawable activeCancelButtonColor;
    private int activeCancelButtonTextColor;
    private Button cancelBookingButton;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        context = this.getContext();
        popupAvailableColor = ContextCompat.getDrawable(context, R.drawable.green_button_border);
        popupBookedColor = ContextCompat.getDrawable(context, R.drawable.red_button_border);
        cancelButtonColor = ContextCompat.getDrawable(context, R.drawable.inactive_clear_button_border);
        CancelButtonTextColor = ContextCompat.getColor(context, R.color.table_overview_faded_clear_text);
        activeCancelButtonColor = ContextCompat.getDrawable(context, R.drawable.active_clear_button_border);
        activeCancelButtonTextColor = ContextCompat.getColor(context, R.color.table_overview_text_dark);
        deleteWrapper = new DeleteWrapper();
        orderRepository = new OrderRepository();
        sharedViewModel = new ViewModelProvider(requireActivity()).
                get(TableDialogSharedViewModel.class);
        mutableTable = sharedViewModel.getTable();
        table = mutableTable.getValue(); // TEMP FIX
        dialogText = table.getDialogText();

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        dialogView = layoutInflater.inflate(R.layout.table_dialog_fragment, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);

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

        tableDialogViewModel = new ViewModelProvider(requireActivity()).
                get(TableDialogViewModel.class);

        parent = getParentFragment();
        openOrderButton = dialogView.findViewById(R.id.openOrderButton);
        bookingButton = dialogView.findViewById(R.id.bookingButton);
        textDisplay = dialogView.findViewById(R.id.dialogTextDisplay);
        cancelButton = dialogView.findViewById(R.id.cancelButton);
        cancelBookingButton = dialogView.findViewById(R.id.cancelBookingButton);
        calender = Calendar.getInstance();
        textDisplay.setText(dialogText);

        if (table.isTableOpen()) {
            cancelButton.setBackground(cancelButtonColor);
            cancelButton.setTextColor(CancelButtonTextColor);
        }
        else {
            cancelButton.setBackground(activeCancelButtonColor);
            cancelButton.setTextColor(activeCancelButtonTextColor);
        }

        if (table.isTableBooked()) {
            cancelBookingButton.setBackground(activeCancelButtonColor);
            cancelBookingButton.setTextColor(activeCancelButtonTextColor);
        }
        else {
            cancelBookingButton.setBackground(cancelButtonColor);
            cancelBookingButton.setTextColor(CancelButtonTextColor);
        }


        //adjustBookingButton();
        adjustOrderButton();

        cancelButton.setOnClickListener(v -> {
/*<<<<<<< HEAD
            final AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(context);
            confirmationDialog.setTitle("Är du säker på att du vill rensa ordern?")
                    .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tableDialogViewModel.clearCurrentOrderFromDatabase(table.getTableNr());
                            tableDialogViewModel.clearOrderSet();
                            table.setTableOpen(true);
                            cancelButton.setBackground(cancelButtonColor);
                            cancelButton.setTextColor(CancelButtonTextColor);
                            Log.d("OrderSet", Integer.toString(tableDialogViewModel.getOrdersToRemoveFromKitchen().size()));
                        }
                    })
                    .setNegativeButton("Nej", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                        }
                    })
                    .show();
=======*/
            if (table.hasOrders()) {
                final AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(context);
                confirmationDialog.setTitle("Är du säker på att du vill rensa ordern?")
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tableDialogViewModel.clearCurrentOrderFromDatabase(table.getTableNr());

                                tableDialogViewModel.clearOrderSet();
                                table.setTableOpen(true);
                                cancelButton.setBackground(cancelButtonColor);
                                cancelButton.setTextColor(CancelButtonTextColor);

                            }
                        })
                        .setNegativeButton("Nej", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dismiss();
                            }
                        })
                        .show();
            }

        });

        openOrderButton.setOnClickListener(v -> {
            int tableNr = table.getTableNr();
            NavDirections action = TableDialogFragmentDirections.actionTableDialogFragmentToOrderOverviewFragment().setTableID(tableNr);
            Navigation.findNavController(parent.getView()).navigate(action);
        });

        bookingButton.setOnClickListener(v -> {
                final BookingDialog myDialog = new BookingDialog(context, this);
                myDialog.setBookingButton("Boka", table.getTableNr());

                //gets called when back button is pressed or pressed outside
                myDialog.setOnCancelListener(dialog -> {
                    table.setTableBooked(false);
                    Log.i("onCancel", "on cancel was pressed");
                });

                myDialog.show();
                myDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                table.setTableBooked(true);
        });


        cancelBookingButton.setOnClickListener(v -> {
            if (table.isTableBooked()) {
                // Avbokning
                Log.d("Avboka", "Avbokar " + table.getReservationID());
                deleteWrapper.deleteReservation(table.getReservationID());
                dismiss();
                cancelBookingButton.setBackground(cancelButtonColor);
                cancelBookingButton.setTextColor(CancelButtonTextColor);

            }
        });
    }


    private void adjustOrderButton() {
        openOrderButton.setText("Ta en order");
        openOrderButton.setBackground(popupAvailableColor);
    }
}


