package se.miun.dt170.antonsskafferi.ui.bong;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.miun.dt170.antonsskafferi.R;

public class BongKitchenHeaderFragment extends Fragment {

    private BongKitchenHeaderViewModel mViewModel;

    public static BongKitchenHeaderFragment newInstance() {
        return new BongKitchenHeaderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bong_kitchen_header_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BongKitchenHeaderViewModel.class);
        // TODO: Use the ViewModel
    }

}
