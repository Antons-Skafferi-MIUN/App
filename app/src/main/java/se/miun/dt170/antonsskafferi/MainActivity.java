package se.miun.dt170.antonsskafferi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import se.miun.dt170.antonsskafferi.activity.KitchenActivity;
import se.miun.dt170.antonsskafferi.activity.WaiterActivity;

/**
 * Main activity that creates either {@link KitchenActivity} or {@link WaiterActivity} depending on choice.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
        waiterCheckBox.setOnClickListener(this);
        kitchenCheckBox.setOnClickListener(this);
        enterButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.kitchenCheckBox:
                if (waiterCheckBox.isChecked()){
                    waiterCheckBox.setTextColor(Color.parseColor("#a9a9a9"));
                    waiterCheckBox.setPaintFlags(0);
                    waiterCheckBox.setChecked(false);
                }
                if (!kitchenCheckBox.isChecked()) {
                    kitchenCheckBox.setTextColor(Color.parseColor("#a9a9a9"));
                    kitchenCheckBox.setPaintFlags(0);
                } else {
                    kitchenCheckBox.setTextColor(Color.parseColor("#FFFFFF"));
                    kitchenCheckBox.setPaintFlags(waiterCheckBox.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    inputEditText.setVisibility(View.GONE);
                }
                break;
            case R.id.waiterCheckBox:
                if(kitchenCheckBox.isChecked()){
                    kitchenCheckBox.setTextColor(Color.parseColor("#a9a9a9"));
                    kitchenCheckBox.setPaintFlags(0);
                    kitchenCheckBox.setChecked(false);
                }
                if (!waiterCheckBox.isChecked()) {
                    waiterCheckBox.setTextColor(Color.parseColor("#a9a9a9"));
                    waiterCheckBox.setPaintFlags(0);
                } else {
                    waiterCheckBox.setTextColor(Color.parseColor("#FFFFFF"));
                    waiterCheckBox.setPaintFlags(waiterCheckBox.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    inputEditText.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.enterButton:
                if (waiterCheckBox.isChecked()) {
                    waiterCheckBox.setTextColor(Color.parseColor("#FFFFFF"));
                    Intent waiterActivity = new Intent(self, WaiterActivity.class);
//                    if(TextUtils.isEmpty(inputEditText.getText().toString())) {
//                        inputEditText.setError("Fältet får inte vara tomt");
//                        return;
//                    }
                    waiterActivity.putExtra("DISPLAY_NAME", inputEditText.getText().toString());
                    startActivity(waiterActivity);

                } else if (kitchenCheckBox.isChecked()) {
                    kitchenCheckBox.setTextColor(Color.parseColor("#FFFFFF"));
                    startActivity(new Intent(self, KitchenActivity.class));
                }
                break;
        }
    }
}
