package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import se.miun.dt170.antonsskafferi.R;

/**
 * Contains available courses in the middle of the {@link se.miun.dt170.antonsskafferi.ui.order_overview.OrderOverviewFragment}.
 */
public class OrderOverviewCoursesFragment extends Fragment {

    private OrderOverviewCoursesViewModel mViewModel;

    public static OrderOverviewCoursesFragment newInstance() {
        return new OrderOverviewCoursesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_overview_courses_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderOverviewCoursesViewModel.class);
        // TODO: Use the ViewModel
    }

}
