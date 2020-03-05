package se.miun.dt170.antonsskafferi.ui.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
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

import java.util.ArrayList;
import java.util.Calendar;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.TableDialogSharedViewModel;
import se.miun.dt170.antonsskafferi.data.APIWrappers.DeleteWrapper;
import se.miun.dt170.antonsskafferi.data.model.Order;
import se.miun.dt170.antonsskafferi.data.model.OrderRow;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.Orders;
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


    @Override
    public void onDestroy() {
        Toast.makeText(getActivity(), "DESTROY WAS CALLED", Toast.LENGTH_SHORT).show();
        super.onDestroy();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        popupAvailableColor = ContextCompat.getDrawable(this.getContext(), R.drawable.green_button_border);
        popupBookedColor = ContextCompat.getDrawable(this.getContext(), R.drawable.red_button_border);
        cancelButtonColor = ContextCompat.getDrawable(this.getContext(), R.drawable.white_button_border);
        CancelButtonTextColor = ContextCompat.getColor(this.getContext(), R.color.deselected_faded_gray);
        deleteWrapper = new DeleteWrapper();
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        dialogView = layoutInflater.inflate(R.layout.table_dialog_fragment, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        orderRepository = new OrderRepository();
        sharedViewModel = new ViewModelProvider(requireActivity()).
                get(TableDialogSharedViewModel.class); //gets the shared view model from the associsated fragment.
        //creates a new observers that will update once the shared view model has new data

        //TODO THIS OBSERVER SEEEM TO FAIL TO OBSERVE.
        MutableLiveData<TableView> mutableTable = sharedViewModel.getTable();
        table = mutableTable.getValue(); // TEMP FIX
        dialogText = sharedViewModel.getDialogText();

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
        context = this.getContext();
        cancelButton.setBackground(cancelButtonColor);
        cancelButton.setTextColor(CancelButtonTextColor);

        adjustBookingButton();
        adjustOrderButton();

        cancelButton.setOnClickListener(v -> {
            final AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(this.getContext());
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


                final AlertDialog.Builder enterTimeDialog = new AlertDialog.Builder(this.getContext());
                final EditText name = new EditText(this.getContext());
                final Button dateButton = new Button(this.getContext());
                final Button timeButton = new Button(this.getContext());
                timeButton.setText("Klicka för att ange tid");
                dateButton.setText("Klicka för att ange datum");
                name.setHint("Ange kundnamn");

                final EditText phoneNumber = new EditText(this.getContext());
                phoneNumber.setHint("Ange kundens telefonnummer");
                final LinearLayout layout = new LinearLayout(this.getContext());
                layout.setOrientation(LinearLayout.VERTICAL);

                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String timeString = Integer.toString(year) + "-" + Integer.toString(month) +
                                "-" +Integer.toString(dayOfMonth);
                        dateButton.setText(timeString);
                        dateButton.setBackground(popupAvailableColor);
                    }
                };
                dateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(context, date, calender
                                .get(Calendar.YEAR), calender.get(Calendar.MONTH),
                                calender.get(Calendar.DAY_OF_MONTH)).show();

                    }
                });
                timeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int hour = calender.get(Calendar.HOUR_OF_DAY);
                        int minute = calender.get(Calendar.MINUTE);


                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                String timeString = Integer.toString(selectedHour) + "-" + Integer.toString(selectedMinute);
                                timeButton.setText(timeString);
                                timeButton.setBackground(popupAvailableColor);
                            }
                        }, hour, minute, true);
                        mTimePicker.setTitle("Välj tid.");
                        mTimePicker.show();


                    }
                });

                layout.addView(name);
                layout.addView(phoneNumber);
                layout.addView(dateButton);
                layout.addView(timeButton);

                enterTimeDialog
                        .setView(layout) //set layout here
                        .setPositiveButton("Klar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //table.setArrivalTime(time.getText().toString());
                                adjustBookingButton();
                            }
                        })
                        .setNegativeButton("Avsluta", new DialogInterface.OnClickListener() {
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


