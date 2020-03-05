package se.miun.dt170.antonsskafferi.ui.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.Calendar;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.TableDialogSharedViewModel;
import se.miun.dt170.antonsskafferi.data.APIWrappers.DeleteWrapper;
import se.miun.dt170.antonsskafferi.data.model.OrderRow;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
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


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        context = this.getContext();
        popupAvailableColor = ContextCompat.getDrawable(context, R.drawable.green_button_border);
        popupBookedColor = ContextCompat.getDrawable(context, R.drawable.red_button_border);
        cancelButtonColor = ContextCompat.getDrawable(context, R.drawable.white_button_border);
        CancelButtonTextColor = ContextCompat.getColor(context, R.color.deselected_faded_gray);
        deleteWrapper = new DeleteWrapper();
        orderRepository = new OrderRepository();
        sharedViewModel = new ViewModelProvider(requireActivity()).
                get(TableDialogSharedViewModel.class);

        mutableTable = sharedViewModel.getTable();
        table = mutableTable.getValue(); // TEMP FIX
        dialogText = sharedViewModel.getDialogText();

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
        parent = getParentFragment();
        openOrderButton = dialogView.findViewById(R.id.openOrderButton);
        bookingButton = dialogView.findViewById(R.id.bookingButton);
        textDisplay = dialogView.findViewById(R.id.dialogTextDisplay);
        cancelButton = dialogView.findViewById(R.id.cancelButton);
        calender = Calendar.getInstance();
        textDisplay.setText(dialogText);

        cancelButton.setBackground(cancelButtonColor);
        cancelButton.setTextColor(CancelButtonTextColor);

        adjustBookingButton();
        adjustOrderButton();

        cancelButton.setOnClickListener(v -> {
            final AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(context);
            confirmationDialog.setTitle("Är du säker på att du vill rensa ordern?")
                    .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            orderRepository.getOrderRows()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Subscriber<OrderRows>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override
                                        public void onNext(OrderRows orderRows) {
                                            Log.i("ORDER ROW ", "AT TOP");
                                            ArrayList<OrderRow> allOrderRows = orderRows.getOrderRows();
                                            int orderID = -1;
                                            for (OrderRow orderRow : allOrderRows) {
                                                int orderRowTableID = Integer.parseInt(orderRow.getOrderId().getTableId().getTableId());
                                                if (orderRowTableID == table.getTableNr()) {
                                                    orderID = Integer.parseInt(orderRow.getOrderId().getOrderId());
                                                    Log.i("ORDER ID", orderRow.getOrderId().getOrderId());
                                                    Log.i("ORDER TABLE", orderRow.getOrderId().getTableId().getTableId());
                                                    deleteWrapper.deleteOrderRow(orderRowTableID);
                                                }
                                                ;
                                            }
                                            if (orderID != -1) {
                                                deleteWrapper.deleteOrder(orderID);
                                            }
                                        }

                                    });

                        }
                    })
                    .setNegativeButton("Nej", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dismiss();
                        }
                    })
                    .show();


        });

        openOrderButton.setOnClickListener(v -> {
            NavDirections action = TableDialogFragmentDirections.actionTableDialogFragmentToOrderOverviewFragment();
            Navigation.findNavController(parent.getView()).navigate(action);
        });

        bookingButton.setOnClickListener(v -> {
            table.setTableBooked(!table.isTableBooked());
            if (table.isTableBooked()) {
                final BookingDialog myDialog = new BookingDialog(context);
                myDialog.setBookingButton("Boka", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("onBooking", "POST to database");
                    }
                });
                myDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) { //gets called when back button is pressed or pressed outside
                        table.setTableBooked(false);
                        adjustBookingButton();
                        Log.i("onCancel", "on cancel was pressed");
                    }
                });
                myDialog.show();
                myDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        |WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    } else {
        adjustBookingButton();
            }
        });
    }

    private void adjustBookingButton() {
        if (!table.isTableBooked()) { //table is available.
            bookingButton.setBackground(popupAvailableColor); //change to popup
            bookingButton.setText("Boka Bord");
            table.removeBooking();
        } else {
            bookingButton.setBackground(popupBookedColor);
            bookingButton.setText("Avboka Bord");
            table.bookTable();
        }
    }

    private void adjustOrderButton() {
        openOrderButton.setText("Ta en order");
        openOrderButton.setBackground(popupAvailableColor);
    }

}


