package se.miun.dt170.antonsskafferi.ui.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.Calendar;

import se.miun.dt170.antonsskafferi.data.DateConverter;

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


    public BookingDialog(Context context) {
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timeButton.setText("Klicka för att ange tid");
        dateButton.setText("Klicka för att ange datum");
        name.setHint("Ange kundnamn");
        phoneNumber.setHint("Ange kundens telefonnummer");

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

    private void addDatePickerDialog(){
        date = (view, year, month, dayOfMonth) -> {
            dateButton.setText(dateConverter.YYYYMMDDParser(year,month,dayOfMonth));
        };
    }
    private void addDateButtonListener(){
        dateButton.setOnClickListener(v -> {
            datePickerDialog = new DatePickerDialog(context, date, calender
                    .get(Calendar.YEAR), calender.get(Calendar.MONTH),
                    calender.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", datePickerDialog);
            datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Avsluta", datePickerDialog);
            datePickerDialog.show();
        });
    }
    private void addTimeButtonListener(){
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeButton.setText(dateConverter.HHMMParser(hourOfDay,minute));
                    }
                }, calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE), true);

                timePickerDialog.setTitle("Välj tid.");
                timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", timePickerDialog);
                timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Avsluta", timePickerDialog);
                timePickerDialog.show();
            }
        });
    }
    public void setBookingButton(String buttonText, View.OnClickListener listener) {
        bookingButton.setText(buttonText);
        bookingButton.setOnClickListener(listener);
        bookingButton.setVisibility(View.VISIBLE);
    }

}