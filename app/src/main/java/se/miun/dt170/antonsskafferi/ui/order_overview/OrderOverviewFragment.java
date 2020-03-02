package se.miun.dt170.antonsskafferi.ui.order_overview;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.Item;
import se.miun.dt170.antonsskafferi.data.ItemRepository;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_container.MenuContainerView;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_navbar.NavbarView;

/**
 * This is the fullscreen fragment for taking a order
 * Contains a {@link OrderOverviewBongHeaderFragment} above the bong list to the right.
 * A {@link se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_courses.OrderOverviewCoursesFragment} containing available courses in the middle.
 * A {@link se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_drinks.OrderOverviewDrinksFragment} containing available drinks in the middle.
 * A {@link se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_navbar.OrderOverviewNavbarFragment} for the navigation top bar.
 */
public class OrderOverviewFragment extends Fragment implements View.OnClickListener {

    private ItemRepository itemRepository;
    private OrderOverviewViewModel mViewModel;
    private Button laCarteButton;
    private Button drinkButton;
    private MenuContainerView menuContainerView;
    private NavbarView navbarView;

    public static OrderOverviewFragment newInstance() {
        return new OrderOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View orderOverviewFragmentView = inflater.inflate(R.layout.order_overview_fragment, container, false);
        itemRepository = new ItemRepository();
        navbarView = orderOverviewFragmentView.findViewById(R.id.navbarView);
        laCarteButton = navbarView.findViewById(R.id.laCarteButton);
        drinkButton = navbarView.findViewById(R.id.drinkButton);
        laCarteButton.setOnClickListener(this);
        drinkButton.setOnClickListener(this);
        return orderOverviewFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OrderOverviewViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.laCarteButton:
                //fill food
                break;
            case R.id.drinkButton:
                //fill drinks
                break;
            // TODO: Add cases for edit/remove/send and add to bong
        }

    }
}
