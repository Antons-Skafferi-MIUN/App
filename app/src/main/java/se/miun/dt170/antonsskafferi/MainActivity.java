package se.miun.dt170.antonsskafferi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import se.miun.dt170.antonsskafferi.activity.KitchenActivity;
import se.miun.dt170.antonsskafferi.activity.WaiterActivity;

public class MainActivity extends AppCompatActivity {

    AppCompatActivity self = this;
    private CheckBox waiterCheckBox;
    private CheckBox kitchenCheckBox;
    private Button enterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterButton = findViewById(R.id.enterButton);
        waiterCheckBox = findViewById(R.id.waiterCheckBox);
        kitchenCheckBox = findViewById(R.id.kitchenCheckBox);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(waiterCheckBox.isChecked() && !kitchenCheckBox.isChecked()){
                    startActivity(new Intent(self , WaiterActivity.class));
                    waiterCheckBox.setChecked(false);
                }
                else if(kitchenCheckBox.isChecked() && !waiterCheckBox.isChecked()){
                    startActivity(new Intent(self , KitchenActivity.class));
                }

            }
        });
    }
}
