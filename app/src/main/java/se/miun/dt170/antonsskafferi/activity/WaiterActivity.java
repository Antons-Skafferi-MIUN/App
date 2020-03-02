package se.miun.dt170.antonsskafferi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;

import java.util.HashMap;
import java.util.Map;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_container.MenuContainerView;

/**
 * Waiter activity is the root for the kitchen navigation graph
 */
public class WaiterActivity extends AppCompatActivity {
    private TextView name;

    private Map<String, MenuContainerView> menuContainerViews;   //Should contain Alacarte and beverages



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);

        name = findViewById(R.id.nameText);
        String test = getIntent().getStringExtra("DISPLAY_NAME");
        name.setText(String.format("Hello %s", test));

        MenuContainerView menuContainerView = new MenuContainerView(this,this);


        ////////////////////////////  SKIT  //////////////////////////////////
        //menuContainerViews = new HashMap<>();

        // Create view variables
        //FlexboxLayout menuListLayoutContainer = findViewById(R.id.orderOverviewFragment);

        // Add one bong list
        //menuContainerViews.put("alacarte", new MenuContainerView(this, this));
        //menuContainerViews.put("beverages", new MenuContainerView(this, this));


        //Add two views alacarte and beverages //HOW do i get the right flexboxlayout from order_overview_fragment??
        //need to target menuContainerView
        //menuListLayoutContainer.addView(menuContainerViews.get(alacarte));
        //menuListLayoutContainer.addView(menuContainerViews.get(beverages));
    }
}


