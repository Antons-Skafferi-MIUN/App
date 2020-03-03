package se.miun.dt170.antonsskafferi.ui.bong;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.stream.Collectors;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.Item;

public class BongItemView extends ConstraintLayout implements View.OnClickListener {
    private boolean itemClicked = false;
    private TextView foodNameText;
    private TextView extraText;
    private CheckBox checkBox;

    public BongItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BongItemView(@NonNull Context context, Item item) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bong_item_view, this, true);

        checkBox = findViewById(R.id.checkBox);
        foodNameText = findViewById(R.id.foodNameText);
        extraText = findViewById(R.id.extraText);

        foodNameText.setText(item.getName());

        // Populate extra text
        if (item.getExtras() != null) {
            String extraString = item.getExtras().stream().collect(Collectors.joining("\n"));
            extraText.setVisibility(VISIBLE);
            extraText.setText(extraString);
        }

        checkBox.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.checkBox) {
            setItemClicked();
        }
    }

    private void setItemClicked() {
        if (!itemClicked) {
            foodNameText.setTextColor(Color.parseColor("#00cc00"));
            extraText.setTextColor(Color.parseColor("#00cc00"));
            itemClicked = true;
        } else {
            foodNameText.setTextColor(Color.parseColor("#000000"));
            extraText.setTextColor(Color.parseColor("#000000"));
            itemClicked = false;
        }
    }
}

