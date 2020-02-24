package se.miun.dt170.antonsskafferi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import se.miun.dt170.antonsskafferi.activity.KitchenActivity;
import se.miun.dt170.antonsskafferi.activity.WaiterActivity;

public class MainActivity extends AppCompatActivity {

    AppCompatActivity self = this;
    private CheckBox waiterCheckBox;
    private CheckBox kitchenCheckBox;
    private Button enterButton;
    private EditText inputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterButton = findViewById(R.id.enterButton);
        waiterCheckBox = findViewById(R.id.waiterCheckBox);
        kitchenCheckBox = findViewById(R.id.kitchenCheckBox);
        inputEditText = findViewById(R.id.inputBox);
        waiterCheckBox.setChecked(true);
        waiterCheckBox.setTextColor(Color.parseColor("#FFFFFF"));
        waiterCheckBox.setPaintFlags(waiterCheckBox.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        waiterCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!waiterCheckBox.isChecked()) {
                    waiterCheckBox.setTextColor(Color.parseColor("#a9a9a9"));
                    waiterCheckBox.setPaintFlags(0);
                } else {
                    waiterCheckBox.setTextColor(Color.parseColor("#FFFFFF"));
                    waiterCheckBox.setPaintFlags(waiterCheckBox.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    inputEditText.setVisibility(View.VISIBLE);
                }
            }
        });

        kitchenCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!kitchenCheckBox.isChecked()) {
                    kitchenCheckBox.setTextColor(Color.parseColor("#a9a9a9"));
                    kitchenCheckBox.setPaintFlags(0);
                } else {
                    kitchenCheckBox.setTextColor(Color.parseColor("#FFFFFF"));
                    kitchenCheckBox.setPaintFlags(waiterCheckBox.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    inputEditText.setVisibility(View.GONE);

                }
            }
        });

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (waiterCheckBox.isChecked() && !kitchenCheckBox.isChecked()) {
                    waiterCheckBox.setChecked(false);
                    waiterCheckBox.setTextColor(Color.parseColor("#FFFFFF"));
                    Intent intent = new Intent(self, WaiterActivity.class);
                    intent.putExtra("DISPLAY_NAME", "Asd"); //TODO: NEEDS TO BE ASYNC

                    startActivity(intent);

                } else if (kitchenCheckBox.isChecked() && !waiterCheckBox.isChecked()) {
                    kitchenCheckBox.setChecked(false);
                    kitchenCheckBox.setTextColor(Color.parseColor("#FFFFFF"));
                    startActivity(new Intent(self, KitchenActivity.class));

                }

            }
        });
    }
}
