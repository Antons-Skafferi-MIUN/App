package se.miun.dt170.antonsskafferi.ui.bong;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import se.miun.dt170.antonsskafferi.R;

public class BongKitchenListFragment extends Fragment implements View.OnClickListener {

    private BongKitchenListViewModel mViewModel;
    private View bongItemView;
    private TextView foodNameText;
    private TextView amountText;
    private TextView changeText;
    private CheckBox checkBox;
    private boolean itemClicked = false;

    public static BongKitchenListFragment newInstance() {
        return new BongKitchenListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bong_kitchen_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bongItemView = view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(BongKitchenListViewModel.class);

        foodNameText = bongItemView.findViewById(R.id.foodNameText);
        amountText = bongItemView.findViewById(R.id.amountText);
        changeText = bongItemView.findViewById(R.id.changesText);

        checkBox = bongItemView.findViewById(R.id.checkBox);

        checkBox.setOnClickListener(this);

        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
