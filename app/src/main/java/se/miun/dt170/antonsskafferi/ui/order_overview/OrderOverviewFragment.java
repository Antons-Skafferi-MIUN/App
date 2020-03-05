package se.miun.dt170.antonsskafferi.ui.order_overview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.Item;
import se.miun.dt170.antonsskafferi.data.ItemRepository;
import se.miun.dt170.antonsskafferi.ui.bong.BongItemView;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_bong.OrderBongButtonsView;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_bong.OrderBongContainerView;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_bong.OrderBongHeaderView;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_bong.OrderBongListView;
import se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_bong.orderOverviewPopUp;
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
    private ImageButton sendButton;
    private ImageButton editButton;
    private ImageButton deleteButton;
    private MenuContainerView menuContainerView;
    private LinearLayout menuContainerLayout;
    private NavbarView navbarView;
    private OrderBongContainerView orderBongContainerView;
    private OrderBongButtonsView orderBongButtonsView;
    private OrderBongListView orderBongListView;
    private LinearLayout orderBongListLinearLayout;



    public static OrderOverviewFragment newInstance() {
        return new OrderOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View orderOverviewFragmentView = inflater.inflate(R.layout.order_overview_fragment, container, false);
        orderBongListLinearLayout = orderBongListView.findViewById(R.id.orderBongListLinearLayout);
        menuContainerView = orderOverviewFragmentView.findViewById(R.id.menuContainerView);
        menuContainerLayout = orderOverviewFragmentView.findViewById(R.id.menuContainerLayout);
        orderBongContainerView = orderOverviewFragmentView.findViewById(R.id.orderBongContainerView);
        navbarView = orderOverviewFragmentView.findViewById(R.id.navbarView);
        laCarteButton = navbarView.findViewById(R.id.laCarteButton);
        drinkButton = navbarView.findViewById(R.id.drinkButton);
        orderBongListView = orderBongContainerView.findViewById(R.id.orderBongListView);
        orderBongButtonsView = orderBongContainerView.findViewById(R.id.orderbongbuttons);
        sendButton = orderBongButtonsView.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);
        editButton = orderBongButtonsView.findViewById(R.id.editButton);
        editButton.setOnClickListener(this);
        deleteButton = orderBongButtonsView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);
        setNavbarListener();
        menuContainerView.addCategory("Testkategori");
        setMenuItemListener();

        return orderOverviewFragmentView;
    }

    private void setMenuItemListener() {
        ViewGroup menuContainer = (ViewGroup) menuContainerLayout;

        for(int categoryIndex = 0; categoryIndex < menuContainer.getChildCount(); categoryIndex++){
            ViewGroup menuCategory = (ViewGroup) menuContainer.getChildAt(categoryIndex).findViewById(R.id.menuCategoryFlexbox);

            for (int menuItemIndex = 0; menuItemIndex < menuCategory.getChildCount(); menuItemIndex++) {
                if (menuCategory.getChildAt(menuItemIndex) instanceof MenuItemView) {
                    MenuItemView menuItemView = (MenuItemView) menuCategory.getChildAt(menuItemIndex);
                    menuItemView.setOnClickListener(this);
                }
            }
        }
    }

    private void setNavbarListener() {
        laCarteButton.setOnClickListener(this);
        drinkButton.setOnClickListener(this);
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
            case R.id.menuItemView:
                    addMenuItemToBong(v);
                break;
            case R.id.laCarteButton:
                Toast.makeText(getActivity(), "A LA CARTE", Toast.LENGTH_SHORT).show();
                //fill food
                break;
            case R.id.drinkButton:
                Toast.makeText(getActivity(), "DRINKS", Toast.LENGTH_SHORT).show();
                //fill drinks
                break;
            case R.id.sendButton:
                sendOrder(v);
                break;
                //Add cases for edit/remove/send and add to bong
            case R.id.editButton:
                Toast.makeText(getActivity(), "EDIT", Toast.LENGTH_SHORT).show();
                popupWindow(v);
                break;
            case R.id.deleteButton:
                Toast.makeText(getActivity(), "DELETE", Toast.LENGTH_SHORT).show();
                removeAllItemFromBong(orderBongListView);
                break;

        }
    }

    private void addMenuItemToBong(View v) {
        TextView menuItemNameTextView = v.findViewById(R.id.menuItemName);
        Item item = new Item(menuItemNameTextView.getText().toString(),null);
        BongItemView bongItemView = new BongItemView(getContext(), item);
        orderBongListLinearLayout.addView(bongItemView, 0);
    }

    private void sendOrder(View v) {
        OrderBongHeaderView orderBongHeaderView = orderBongContainerView.findViewById(R.id.orderBongHeaderView);
        TextView waiterName = orderBongHeaderView.findViewById(R.id.waiterName);
        TextView tableNumber = orderBongHeaderView.findViewById(R.id.tableNumber);
        TextView orderNumber = orderBongHeaderView.findViewById(R.id.orderNumber);
        TextView time = orderBongHeaderView.findViewById(R.id.time);

        ViewGroup orderRows = (ViewGroup) orderBongListLinearLayout;

        for (int orderRowIndex = 0; orderRowIndex < orderRows.getChildCount(); orderRowIndex++) {
            //TextView
        }

        Toast.makeText(getActivity(), waiterName.getText(), Toast.LENGTH_SHORT).show();
    }

    //remove all items from bong list
    private void removeAllItemFromBong(View v) {
        LinearLayout orderBongListLinearLayout = v.findViewById(R.id.orderBongListLinearLayout);
        orderBongListLinearLayout.removeAllViews();

    }


    private void popupWindow(View v) {
        //startActivity(new Intent(OrderOverviewFragment.this,orderOverviewPopUp.class));
        Intent intent=new Intent(OrderOverviewFragment.this.getContext(), orderOverviewPopUp.class);
        startActivity(intent);
    }



}
