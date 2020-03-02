package se.miun.dt170.antonsskafferi.ui.order_overview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_container.MenuContainerView;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * This is the fullscreen fragment for taking a order
 * Contains a {@link OrderOverviewBongHeaderFragment} above the bong list to the right.
 * A {@link se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_courses.OrderOverviewCoursesFragment} containing available courses in the middle.
 * A {@link se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_drinks.OrderOverviewDrinksFragment} containing available drinks in the middle.
 * A {@link se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_navbar.OrderOverviewNavbarFragment} for the navigation top bar.
 */
public class OrderOverviewFragment extends Fragment {

    private OrderOverviewViewModel mViewModel;

    public static OrderOverviewFragment newInstance() {
        return new OrderOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "KÖR NU---OrderOverViewFragment: ");


        return inflater.inflate(R.layout.order_overview_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderOverviewViewModel.class);
        // TODO: Use the ViewModel
    }

}
