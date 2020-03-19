package se.miun.dt170.antonsskafferi;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import se.miun.dt170.antonsskafferi.activity.KitchenActivity;
import se.miun.dt170.antonsskafferi.activity.WaiterActivity;

/**
 * Main activity that creates either {@link KitchenActivity} or {@link WaiterActivity} depending on choice.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatActivity self = this;
    private CheckBox waiterCheckBox;
    private CheckBox kitchenCheckBox;
    private Button enterButton;
    private EditText inputEditText;

    private int deselected;
    private int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deselected = ContextCompat.getColor(this, R.color.deselected_faded_gray);
        selected = ContextCompat.getColor(this, R.color.selected_white);

        enterButton = findViewById(R.id.enterButton);
        waiterCheckBox = findViewById(R.id.waiterCheckBox);
        kitchenCheckBox = findViewById(R.id.kitchenCheckBox);
        inputEditText = findViewById(R.id.inputBox);
        waiterCheckBox.setChecked(true);
        waiterCheckBox.setTextColor(selected);
        waiterCheckBox.setPaintFlags(waiterCheckBox.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        waiterCheckBox.setOnClickListener(this);
        kitchenCheckBox.setOnClickListener(this);
        enterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kitchenCheckBox:
                if (waiterCheckBox.isChecked()) {
                    waiterCheckBox.setTextColor(deselected); //fades waiter checkbox
                    waiterCheckBox.setPaintFlags(0);
                    waiterCheckBox.setChecked(false);
                }
                if (!kitchenCheckBox.isChecked()) {
                    kitchenCheckBox.setTextColor(deselected);
                    kitchenCheckBox.setPaintFlags(0);
                } else {
                    kitchenCheckBox.setTextColor(selected);
                    kitchenCheckBox.setPaintFlags(waiterCheckBox.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    inputEditText.setVisibility(View.GONE);
                }
                break;
            case R.id.waiterCheckBox:
                if (kitchenCheckBox.isChecked()) {
                    kitchenCheckBox.setTextColor(deselected);
                    kitchenCheckBox.setPaintFlags(0);
                    kitchenCheckBox.setChecked(false);
                }
                if (!waiterCheckBox.isChecked()) {
                    waiterCheckBox.setTextColor(deselected);
                    waiterCheckBox.setPaintFlags(0);
                } else {
                    waiterCheckBox.setTextColor(selected);
                    waiterCheckBox.setPaintFlags(waiterCheckBox.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    inputEditText.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.enterButton:
                if (waiterCheckBox.isChecked()) {
                    waiterCheckBox.setTextColor(selected);
                    Intent waiterActivity = new Intent(self, WaiterActivity.class);
//                    if(TextUtils.isEmpty(inputEditText.getText().toString())) {
//                        inputEditText.setError("Fältet får inte vara tomt");
//                        return;
//                    }
                    waiterActivity.putExtra("DISPLAY_NAME", inputEditText.getText().toString());
                    startActivity(waiterActivity);

                } else if (kitchenCheckBox.isChecked()) {
                    kitchenCheckBox.setTextColor(selected);
                    startActivity(new Intent(self, KitchenActivity.class));
                }
                break;
        }
    }
}
