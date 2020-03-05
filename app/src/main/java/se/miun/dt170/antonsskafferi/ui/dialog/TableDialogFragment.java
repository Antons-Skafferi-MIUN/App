package se.miun.dt170.antonsskafferi.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
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

import java.util.ArrayList;

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
    private boolean isDone;
    private Orders orders;
    private OrderRows orderRows;
    private OrderRepository orderRepository;
    private DeleteWrapper deleteWrapper;





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

        textDisplay.setText(dialogText);

        cancelButton.setBackground(cancelButtonColor);
        cancelButton.setTextColor(CancelButtonTextColor);

        adjustBookingButton();
        adjustOrderButton();

        cancelButton.setOnClickListener(v ->{
            orderRepository.getOrders()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Orders>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Orders orders) {
                    Log.i("ORDERS TEST", orders.toString());
                    ArrayList<Order> allOrders = orders.getOrders();

                    for(Order order : allOrders) {
                        int orderTableID = Integer.parseInt(order.getTableId().getTableId());
                        if(orderTableID == table.getTableNr()) //this order belongs to this table
                        {
                            int currentorder = orderTableID;
                            Log.i("Current Table: ",Integer.toString(table.getTableNr()));
                            Log.i("Current Order: ",Integer.toString(currentorder));
                            orderRepository.getOrderRows()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Subscriber<OrderRows>() {
                                        @Override
                                        public void onCompleted() {
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.i("ERROR IN GET ORDER ROWS", e.toString());
                                        }
                                        @Override
                                        public void onNext(OrderRows orderrows) {
                                            ArrayList<OrderRow> allOrderRows = orderrows.getOrderRows();
                                            for(OrderRow orderRow : allOrderRows){
                                                int orderRowOrderID = Integer.parseInt(orderRow.getOrderId().getOrderId());
                                                if(orderRowOrderID == currentorder){ //if the or
                                                    Log.i("Deleting ROW: ", orderRow.getOrderRowId());
                                                    deleteWrapper.deleteOrderRow(orderRowOrderID);
                                                };
                                            }
                                            deleteWrapper.deleteOrder(currentorder);
                                        }
                                    });
                        }
                    }

                    }
            });

            final AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(this.getContext());
            confirmationDialog.setTitle("Är du säker på att du vill stänga bordet?")

                    .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.i("ORDERS TEST", "DELETING ALL");

                    //get table ID
                    //Get all orders related to table.
                    //get all order rows related to order
                    //delete all order rows for each order

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
            int tableNr = table.getTableNr();
            NavDirections action = TableDialogFragmentDirections.actionTableDialogFragmentToOrderOverviewFragment().setTableID(tableNr);
            Navigation.findNavController(parent.getView()).navigate(action);
        });

        bookingButton.setOnClickListener(v -> {
            table.setTableBooked(!table.isTableBooked());
            if (table.isTableBooked()) {
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

    public void setTableOrders(Orders orders) {
        this.orders = orders;
    }

    public void setOrderRows(OrderRows orderRows) {
        this.orderRows = orderRows;
    }

    public Action0 waiting(){
        isDone = false;
        Log.i("ORDERS TEST", "WAITING");
        return null;
    }
    public Action0 done(){
        Log.i("ORDERS TEST", "DONE");
        isDone = true;
        return null;
    }


}


