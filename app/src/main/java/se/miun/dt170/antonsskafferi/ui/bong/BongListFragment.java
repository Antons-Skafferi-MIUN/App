package se.miun.dt170.antonsskafferi.ui.bong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import se.miun.dt170.antonsskafferi.R;

/**
 * This is the container for the list of {@link BongItemView}.
 * Displayed to the right in the order {@link se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment}.
 * Displayed in the {@link se.miun.dt170.antonsskafferi.activity.KitchenActivity}.
 */

public class BongListFragment extends Fragment {

    private BongListViewModel mViewModel;

    public static BongListFragment newInstance() {
        return new BongListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return  inflater.inflate(R.layout.bong_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BongListViewModel.class);
        // TODO: Use the ViewModel



    }

}
