package se.miun.dt170.antonsskafferi.ui.bong;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import se.miun.dt170.antonsskafferi.R;

/**
 * Displayed in the {@link se.miun.dt170.antonsskafferi.activity.KitchenActivity}.
 * Shows a header for bongs in the kitchen.
 */
public class BongKitchenHeaderFragment extends Fragment implements View.OnClickListener {

    private BongKitchenHeaderViewModel mViewModel;
    private View bongHeaderView;
    private CheckBox bongHeaderCheckbox;
    private TextView tableNumber;
    private TextView orderTime;
    private boolean headerClicked = false;

    public static BongKitchenHeaderFragment newInstance() {
        return new BongKitchenHeaderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bong_kitchen_header_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bongHeaderView = view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BongKitchenHeaderViewModel.class);

        tableNumber =  bongHeaderView.findViewById(R.id.tableText);
        orderTime  = bongHeaderView.findViewById(R.id.timeText);
        bongHeaderCheckbox = bongHeaderView.findViewById(R.id.foodCheck);

        bongHeaderCheckbox.setOnClickListener(this);

        // TODO: Use the ViewModel
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.foodCheck:
                setHeaderClicked();
                break;

        }
    }

    public void setHeaderClicked() {
        if (!headerClicked) {
            tableNumber.setTextColor(Color.parseColor("#00cc00"));
            orderTime.setTextColor(Color.parseColor("#00cc00"));
            headerClicked = true;
        }
        else {
            tableNumber.setTextColor(Color.parseColor("#000000"));
            orderTime.setTextColor(Color.parseColor("#000000"));
            headerClicked = false;
        }
    }

}
