package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_container;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.miun.dt170.antonsskafferi.R;

public class OrderOverviewMenuContainerFragment extends Fragment {

    private OrderOverviewMenuContainerViewModel mViewModel;

    public static OrderOverviewMenuContainerFragment newInstance() {
        return new OrderOverviewMenuContainerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_overview_menu_container_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderOverviewMenuContainerViewModel.class);
        // TODO: Use the ViewModel
    }

}
