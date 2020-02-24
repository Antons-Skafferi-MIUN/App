package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_bong;

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
 * This is the containing fragment for bong displayment in the {@link se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment}.
 * Contains a {@link OrderOverviewBongHeaderFragment} above the bong list to the right.
 * Also container a {@link se.miun.dt170.antonsskafferi.ui.bong.BongListFragment} with added courses and drinks.
 */
public class OrderOverviewBongFragment extends Fragment {

    private OrderOverviewBongViewModel mViewModel;

    public static OrderOverviewBongFragment newInstance() {
        return new OrderOverviewBongFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_overview_bong_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderOverviewBongViewModel.class);
        // TODO: Use the ViewModel

    }

}
