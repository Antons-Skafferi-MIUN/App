package se.miun.dt170.antonsskafferi.ui.bong;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.List;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.model.Food;
import se.miun.dt170.antonsskafferi.data.model.MenuItem;
import se.miun.dt170.antonsskafferi.ui.kitchen.KitchenBongContainerView;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_bong.OrderBongListView;

import static android.content.Context.ACTIVITY_SERVICE;

public class BongItemView extends ConstraintLayout implements View.OnClickListener {
    private boolean itemClicked = false;
    private TextView foodNameText;
    private TextView extraText;
    private CheckBox checkBox;
    private BongListView bongListView;
    private OrderBongListView orderBongListView;
    private KitchenBongContainerView grandParent;
    private String currentActivity;

    public MenuItem getMenuItem() {
        return menuItem;
    }

    private MenuItem menuItem;


    public BongItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BongItemView(@NonNull Context context, MenuItem menuItem, @Nullable String orderChange) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bong_item_view, this, true);

        checkBox = findViewById(R.id.checkBox);
        foodNameText = findViewById(R.id.foodNameText);
        extraText = findViewById(R.id.extraText);
        this.menuItem = menuItem;

        foodNameText.setText(menuItem.getName());
        this.setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.bg_shadow));



        // Populate extra text
        if (orderChange != null) {

            extraText.setText(orderChange);
        }
        checkBox.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.checkBox) {
            try {
                //BongListView bongListView = (BongListView) view.getParent().getParent().getParent();
                setItemClicked();
            }
            catch (Exception e) { }

        }
        else if (view.getId() == R.id.foodCheck) {
            Toast.makeText(getContext(), "foodcheck", Toast.LENGTH_SHORT).show();
            bongListView = (BongListView) this.getParent();
            grandParent = (KitchenBongContainerView) bongListView.getParent();
            grandParent.setVisibility(View.GONE);
        }
    }

    private void setItemClicked() {

        if (!itemClicked) {
            this.setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.bg_bong_item_selected));
            //this.setBackgroundColor(Color.parseColor("#f6e3b9"));
            foodNameText.setTextColor(Color.parseColor("#c18a0b")); //orange
            extraText.setTextColor(Color.parseColor("#c18a0b"));
            itemClicked = true;
            bongListView.raiseCheckedItems();
        } else {
            this.setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.bg_bong_item));
            //this.setBackgroundColor(Color.parseColor("#fafafa"));
            foodNameText.setTextColor(Color.parseColor("#808080")); //grey
            extraText.setTextColor(Color.parseColor("#808080"));
            itemClicked = false;
            bongListView.reduceCheckedItems();
        }

        bongListView = (BongListView) this.getParent();
        if (!itemClicked) {
            bongListView.raiseCheckedItems();
        } else {
            bongListView.reduceCheckedItems();
        }

        if (bongListView.getNumberOfItems() == bongListView.getCheckedItems()) {
            grandParent = (KitchenBongContainerView) bongListView.getParent();
            grandParent.setVisibility(View.GONE);
        }
    }
}

