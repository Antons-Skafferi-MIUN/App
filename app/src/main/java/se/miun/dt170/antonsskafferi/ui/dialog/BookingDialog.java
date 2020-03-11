package se.miun.dt170.antonsskafferi.ui.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Calendar;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.APIWrappers.PostWrapper;
import se.miun.dt170.antonsskafferi.data.DateConverter;
import se.miun.dt170.antonsskafferi.data.model.Reservation;
import se.miun.dt170.antonsskafferi.data.model.Reservations;
import se.miun.dt170.antonsskafferi.data.model.RestaurantTable;

public class BookingDialog extends AlertDialog {
    private EditText name;
    private Button dateButton;
    private Button timeButton;
    private EditText phoneNumber;
    private LinearLayout layout;
    private Context context;
    private Calendar calender;
    private DatePickerDialog.OnDateSetListener date;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private DateConverter dateConverter;
    private Button bookingButton;
    private PostWrapper postWrapper;
    private TimePicker timePickerView;
    private BookingDialogViewModel bookingDialogViewModel;
    private TableDialogFragment dialogFragment;
    private TableDialogViewModel tableDialogViewModel;
    private String timeString;
    private boolean tableIsReserved;

// https://stackoverflow.com/questions/35599203/disable-specific-dates-of-day-in-android-date-picker
    // can be used to mark specific dates.
// get all reservations from database check if the date is booked
    //if the date is booked

    public BookingDialog(Context context, TableDialogFragment parent) {
        super(context);
        this.context = context;
        phoneNumber = new EditText(context);
        name = new EditText(context);
        dateButton = new Button(context);
        timeButton = new Button(context);
        layout = new LinearLayout(context);
        dateConverter = new DateConverter();
        calender = Calendar.getInstance();
        bookingButton = new Button(context);
        postWrapper = new PostWrapper();
        dialogFragment = parent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timeButton.setText("Klicka för att ange tid");
        dateButton.setText("Klicka för att ange datum");
        name.setHint("Ange kundnamn");
        phoneNumber.setHint("Ange kundens telefonnummer");
        bookingDialogViewModel = new ViewModelProvider(dialogFragment).get(BookingDialogViewModel.class);
        tableDialogViewModel = new ViewModelProvider(dialogFragment).get(TableDialogViewModel.class);

        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(name);
        layout.addView(phoneNumber);
        layout.addView(dateButton);
        layout.addView(timeButton);
        layout.addView(bookingButton);

        setContentView(layout);
        addDatePickerDialog();
        addDateButtonListener();
        addTimeButtonListener();
    }

    private void addDatePickerDialog() {
        //TODO MAKE IT IMPOSSIBLE TO BOOK DATES BAXCK IN TIME.

        date = (view, year, month, dayOfMonth) -> {
            String selectedDate = dateConverter.YYYYMMDDParser(year, month + 1, dayOfMonth);
            dateButton.setBackgroundColor(ContextCompat.getColor(context, R.color.popup_green));
            dateButton.setText(selectedDate);
        };
    }

    private void addDateButtonListener() {
        dateButton.setOnClickListener(v -> {
            datePickerDialog = new DatePickerDialog(context, date, calender
                    .get(Calendar.YEAR), calender.get(Calendar.MONTH),
                    calender.get(Calendar.DAY_OF_MONTH));

            datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", datePickerDialog);
            datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Avsluta", datePickerDialog);
            datePickerDialog.show();
        });
    }

    private void addTimeButtonListener() {
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("timeButton", "I LISTEND");
                timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeButton.setText(dateConverter.HHMMParser(hourOfDay, minute));
                        timeButton.setBackgroundColor(ContextCompat.getColor(context, R.color.popup_green));

                        timePickerView = view;
                    }
                }, calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE), true);

                timePickerDialog.setTitle("Välj tid.");
                timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", timePickerDialog);
                timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Avsluta", timePickerDialog);
                timePickerDialog.show();
            }
        });
    }

    public void setBookingButton(String buttonText, int tableId) {
        bookingButton.setText(buttonText);

        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInputCorrect = true;
                if (TextUtils.isEmpty(phoneNumber.getText().toString())) {
                    phoneNumber.setError("Fältet får inte vara tomt");
                    isInputCorrect = false;
                }
                //2020-03-06T11:55:40+01:00
                if (timePickerView == null) {
                    timeButton.setBackgroundColor(ContextCompat.getColor(context, R.color.popup_red));
                    isInputCorrect = false;
                }
                if (datePickerDialog == null) {
                    dateButton.setBackgroundColor(ContextCompat.getColor(context, R.color.popup_red));
                    isInputCorrect = false;
                }

                if (!isInputCorrect) {
                    return;
                }
                String date = dateButton.getText().toString();
                String time = timeButton.getText().toString();
                timeString = date + "T"
                        + time + ":00+01:00";
                timeString = dateConverter.formatStandard(timeString);
                final Observer<Reservations> observer = new Observer<Reservations>() {
                    @Override
                    public void onChanged(Reservations reservations) {
                        if (reservations == null) {
                            postReservation(timeString, tableId);
                            return;
                        }
                        tableIsReserved = false;
                        ArrayList<Reservation> reservationList = reservations.getReservations();
                        reservationList.forEach(reservation -> {
                            if (Integer.toString(tableId).equals(reservation.getTableId().getTableId())) { //this table
                                String reservedDate = dateConverter.formatYYYYMMDD(reservation.getReservationDate());
                                Log.i("reserved", "THIS IS RES DATE" + reservedDate);
                                Log.i("reserved", "THIS IS CURRENT DATE" + date);
                                if (!reservedDate.equals(date)) { //if the reserved date matches the date entered.
                                    postReservation(timeString, tableId);
                                    Log.i("insideMadeRes", "yay");


                                } else { //the table is reversed for the given date.
                                    dateButton.setBackgroundColor(ContextCompat.getColor(context, R.color.popup_red));
                                    dateButton.setText("THIS DATE IS ALREADY RESERVED " + date);
                                    Log.i("insideELSE", "OH NO");
                                }
                                tableIsReserved = true;
                            } // other table.

                        });
                        if (!tableIsReserved) {
                            postReservation(timeString, tableId);
                            Log.i("outsideMadeRes", "yay");
                        }
                    }
                };
                bookingDialogViewModel.getAllReservations().observe(dialogFragment, observer);
            }
        });
        bookingButton.setVisibility(View.VISIBLE);
    }

    public void postReservation(String timeString, int tableId) {
        timeString = dateConverter.formatStandard(timeString);
        Reservation reservation = new Reservation();
        RestaurantTable restaurantTable = new RestaurantTable(Integer.toString(tableId));
        reservation.setReservationDate(timeString);
        reservation.setReservationName(name.getText().toString());
        reservation.setReservationPhone(phoneNumber.getText().toString());
        reservation.setTableId(restaurantTable);
        postWrapper.postReservation(reservation);
        dialogFragment.adjustBookingButton();
        tableDialogViewModel.getAllReservations();
        dismiss();
    }
}

