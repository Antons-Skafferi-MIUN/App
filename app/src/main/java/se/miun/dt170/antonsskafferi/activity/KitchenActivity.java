package se.miun.dt170.antonsskafferi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import se.miun.dt170.antonsskafferi.R;

/**
 * Kitchen activity is the root for the kitchen navigation graph
 */
public class KitchenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
    }
}
