package se.miun.dt170.antonsskafferi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import se.miun.dt170.antonsskafferi.R;

public class WaiterActivity extends AppCompatActivity {
    private TextView name;
    private Intent intent;
    private String test = "mufffinbman";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);
//        intent = getIntent();
//        name = findViewById(R.id.displayName);
//        name.setText("Hello " + intent.getStringExtra("DISPLAY_NAME"));
    }


    public String getTest(){
        return test;

    }

}


