package se.miun.dt170.antonsskafferi.ui.bong;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import se.miun.dt170.antonsskafferi.R;

public class BongItemView extends ConstraintLayout implements View.OnClickListener  {
    private View bongItemView;
    private TextView foodNameText;
    private TextView amountText;
    private TextView changeText;
    private CheckBox checkBox;
    private boolean itemClicked = false;


    public BongItemView(@NonNull Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bong_item, this, true);


        foodNameText = bongItemView.findViewById(R.id.foodNameText);
        amountText = bongItemView.findViewById(R.id.amountText);
        changeText = bongItemView.findViewById(R.id.changesText);

        checkBox = bongItemView.findViewById(R.id.checkBox);

        checkBox.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkBox:
                setItemClicked();
                break;

        }
    }


    private void setItemClicked() {
        if (!itemClicked) {
            foodNameText.setTextColor(Color.parseColor("#00cc00"));
            amountText.setTextColor(Color.parseColor("#00cc00"));
            changeText.setTextColor(Color.parseColor("#00cc00"));
            itemClicked = true;
        }
        else {
            foodNameText.setTextColor(Color.parseColor("#000000"));
            amountText.setTextColor(Color.parseColor("#000000"));
            changeText.setTextColor(Color.parseColor("#000000"));
            itemClicked = false;
        }
    }
}

