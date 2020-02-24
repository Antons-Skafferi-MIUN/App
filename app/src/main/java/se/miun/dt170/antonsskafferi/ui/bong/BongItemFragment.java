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

/**
 * A fragment that holds a order. Is displayed in BongListFragment and KitchenActivity
 */

public class BongItemFragment extends Fragment {

    private BongItemViewModel mViewModel;

    public static BongItemFragment newInstance() {
        return new BongItemFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bong_item_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BongItemViewModel.class);

        mViewModel.getItems().observe(getViewLifecycleOwner(), items -> {
            // Update UI on item changes

        });
    }
}
