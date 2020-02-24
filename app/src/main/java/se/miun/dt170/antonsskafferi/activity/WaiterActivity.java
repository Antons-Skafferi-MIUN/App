package se.miun.dt170.antonsskafferi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import se.miun.dt170.antonsskafferi.R;

/**
 * Waiter activity is the root for the kitchen navigation graph
 */
public class WaiterActivity extends AppCompatActivity {
    private TextView name;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);
        intent = getIntent();
        name = findViewById(R.id.nameText);
        name.setText(String.format("Hello %s", intent.getStringExtra("DISPLAY_NAME")));
    }
}


