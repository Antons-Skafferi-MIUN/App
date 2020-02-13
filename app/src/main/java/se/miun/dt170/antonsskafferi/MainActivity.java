package se.miun.dt170.antonsskafferi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import se.miun.dt170.antonsskafferi.activity.KitchenActivity;
import se.miun.dt170.antonsskafferi.activity.WaiterActivity;

public class MainActivity extends AppCompatActivity {

    AppCompatActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button kitchenButton = findViewById(R.id.kitchenButton);
        Button waiterButton = findViewById(R.id.waiterButton);

        kitchenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(self , KitchenActivity.class));
            }
        });
        waiterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(self , WaiterActivity.class));
            }
        });
    }
}
