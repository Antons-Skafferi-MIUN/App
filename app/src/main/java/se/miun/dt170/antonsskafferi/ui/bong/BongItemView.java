package se.miun.dt170.antonsskafferi.ui.bong;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
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

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.model.Food;
import se.miun.dt170.antonsskafferi.data.model.MenuItem;
import se.miun.dt170.antonsskafferi.ui.kitchen.KitchenBongContainerView;

public class BongItemView extends ConstraintLayout implements View.OnClickListener {
    private boolean itemClicked = false;
    private TextView foodNameText;
    private TextView extraText;
    private CheckBox checkBox;
    private BongListView bongListView;
    private KitchenBongContainerView grandParent;

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

        foodNameText.setText(menuItem.getName());

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
        bongListView = (BongListView) this.getParent();

        if (!itemClicked) {
            this.setBackgroundColor(Color.parseColor("#a0f4a0")); //light green
            foodNameText.setTextColor(Color.parseColor("#00cc00")); //green
            extraText.setTextColor(Color.parseColor("#00cc00"));
            itemClicked = true;
            bongListView.raiseCheckedItems();
        } else {
            this.setBackgroundColor(Color.parseColor("#FFFFFF"));
            foodNameText.setTextColor(Color.parseColor("#808080")); //grey
            extraText.setTextColor(Color.parseColor("#808080"));
            itemClicked = false;
            bongListView.reduceCheckedItems();
        }

        if (bongListView.getNumberOfItems() == bongListView.getCheckedItems()) {
            grandParent = (KitchenBongContainerView) bongListView.getParent();
            grandParent.setVisibility(View.GONE);
        }
    }
}

