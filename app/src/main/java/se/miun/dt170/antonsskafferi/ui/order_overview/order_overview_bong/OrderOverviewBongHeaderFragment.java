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
 * Displayed above the bong list in the {@link se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment}.
 */
public class OrderOverviewBongHeaderFragment extends Fragment {

    private OrderOverviewBongHeaderViewModel mViewModel;

    public static OrderOverviewBongHeaderFragment newInstance() {
        return new OrderOverviewBongHeaderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_overview_bong_header_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderOverviewBongHeaderViewModel.class);
        // TODO: Use the ViewModel
    }

}
