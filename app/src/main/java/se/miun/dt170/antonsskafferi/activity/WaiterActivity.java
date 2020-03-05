package se.miun.dt170.antonsskafferi.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import se.miun.dt170.antonsskafferi.R;

/**
 * Waiter activity is the root for the kitchen navigation graph
 */
public class WaiterActivity extends AppCompatActivity {
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);

        name = findViewById(R.id.nameText);
        String test = getIntent().getStringExtra("DISPLAY_NAME");
        name.setText(String.format("Hello %s", test));
    }
}


