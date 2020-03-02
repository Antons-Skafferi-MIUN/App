package se.miun.dt170.antonsskafferi.ui.order_overview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.ItemRepository;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_container.MenuContainerView;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_item_view.MenuItemView;
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
    private MenuItemView menuItemView;
    private MenuContainerView menuContainerView;
    private NavbarView navbarView;

    public static OrderOverviewFragment newInstance() {
        return new OrderOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View orderOverviewFragmentView = inflater.inflate(R.layout.order_overview_fragment, container, false);
        menuContainerView = orderOverviewFragmentView.findViewById(R.id.menuContainerView);
        menuContainerView.setOnClickListener(this);
        navbarView = orderOverviewFragmentView.findViewById(R.id.navbarView);
        laCarteButton = navbarView.findViewById(R.id.laCarteButton);
        drinkButton = navbarView.findViewById(R.id.drinkButton);
        laCarteButton.setOnClickListener(this);
        drinkButton.setOnClickListener(this);
        
        ViewGroup menuContainer = (ViewGroup) menuContainerView;
        /*for (ViewGroup menuCategory : menuContainer) {

        }*/
        for(int categoryIndex = 0; categoryIndex < menuContainer.getChildCount(); categoryIndex++){ // for each child apply a listener to the childs tableButton
            Log.d("CategoryMessage", "In category");
            ViewGroup menuCategory = (ViewGroup) menuContainer.getChildAt(categoryIndex);

            for (int menuItemIndex = 0; menuItemIndex < menuCategory.getChildCount(); menuItemIndex++) {
                if (menuCategory.getChildAt(menuItemIndex) instanceof MenuItemView) {
                    Log.d("ListenerMessage", "Added listener");
                    MenuItemView menuItemView = (MenuItemView) menuCategory.getChildAt(menuItemIndex);
                    menuItemView.setOnClickListener(this);
                }
            }
        }
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
        if (v instanceof MenuItemView) {
            Toast.makeText(getActivity(), "RÅBIFF", Toast.LENGTH_SHORT).show();
        }

        switch (v.getId()) {
            case R.id.laCarteButton:
                Toast.makeText(getActivity(), "A LA CARTE", Toast.LENGTH_SHORT).show();
                //fill food
                break;
            case R.id.drinkButton:
                Toast.makeText(getActivity(), "DRINKS", Toast.LENGTH_SHORT).show();
                //fill drinks
                break;
            // TODO: Add cases for edit/remove/send and add to bong
        }

    }
}
