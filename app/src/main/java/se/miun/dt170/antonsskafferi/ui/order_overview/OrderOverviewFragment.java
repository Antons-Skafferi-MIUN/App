package se.miun.dt170.antonsskafferi.ui.order_overview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.ItemRepository;
import se.miun.dt170.antonsskafferi.data.model.Drinks;
import se.miun.dt170.antonsskafferi.data.model.Food;
import se.miun.dt170.antonsskafferi.data.model.Foods;
import se.miun.dt170.antonsskafferi.data.model.MenuItem;
import se.miun.dt170.antonsskafferi.data.model.Order;
import se.miun.dt170.antonsskafferi.data.model.OrderRow;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.ui.bong.BongItemView;
import se.miun.dt170.antonsskafferi.ui.order_overview.menu_category_view.MenuCategoryView;
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
    private MenuCategoryView menuCategoryView;
    private LinearLayout menuContainerLayout;
    private NavbarView navbarView;
    private OrderBongContainerView orderBongContainerView;
    private OrderBongListView orderBongListView;
    private OrderBongButtonsView orderBongButtonsView;
    private ApiService mAPIService;
    ArrayList<String> categorylist;
    List<MenuItem> menuItemList;
    private OrderBongHeaderView orderBongHeaderView;
    private LinearLayout orderBongListLinearLayout;
    private int tableID;


    public static OrderOverviewFragment newInstance() {
        return new OrderOverviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mAPIService = ApiUtils.getAPIService();
        menuItemList = new ArrayList<>();
        View orderOverviewFragmentView = inflater.inflate(R.layout.order_overview_fragment, container, false);
        menuContainerView = orderOverviewFragmentView.findViewById(R.id.menuContainerView);
        menuContainerLayout = orderOverviewFragmentView.findViewById(R.id.menuContainerLayout);
        orderBongContainerView = orderOverviewFragmentView.findViewById(R.id.orderBongContainerView);
        orderBongHeaderView = orderBongContainerView.findViewById(R.id.orderBongHeaderView);
        navbarView = orderOverviewFragmentView.findViewById(R.id.navbarView);
        laCarteButton = navbarView.findViewById(R.id.laCarteButton);
        drinkButton = navbarView.findViewById(R.id.drinkButton);
        orderBongListView = orderBongContainerView.findViewById(R.id.orderBongListView);
        orderBongListLinearLayout = orderBongListView.findViewById(R.id.orderBongListLinearLayout);
        orderBongButtonsView = orderBongContainerView.findViewById(R.id.orderbongbuttons);
        sendButton = orderBongButtonsView.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);
        editButton = orderBongButtonsView.findViewById(R.id.editButton);
        editButton.setOnClickListener(this);
        deleteButton = orderBongButtonsView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);
        setNavbarListener();
        categorylist = new ArrayList<String>();

        getFoods();

        return orderOverviewFragmentView;
    }

    private void setMenuItemListener() {
        ViewGroup menuContainer = menuContainerLayout;


        for (int categoryIndex = 0; categoryIndex < menuContainer.getChildCount(); categoryIndex++) {
            ViewGroup menuCategory = menuContainer.getChildAt(categoryIndex).findViewById(R.id.menuCategoryFlexbox);

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tableID = OrderOverviewFragmentArgs.fromBundle(getArguments()).getTableID();
        String amountString = Integer.toString(tableID);
        TextView textView = orderBongHeaderView.findViewById(R.id.tableNumber);
        textView.setText("Bord " + amountString);
        fillBongWithOrders();
    }

    private void fillBongWithOrders() {
        getOrderRows();
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
                addMenuItemToBong((MenuItemView) v);
                break;
            case R.id.laCarteButton:
                Toast.makeText(getActivity(), "A LA CARTE", Toast.LENGTH_SHORT).show();
                //fill food
                menuContainerLayout.removeAllViews();
                getFoods();
                break;
            case R.id.drinkButton:
                Toast.makeText(getActivity(), "DRINKS", Toast.LENGTH_SHORT).show();
                //fill drinks
                menuContainerLayout.removeAllViews();
                getDrinks();
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

    private void addMenuItemToBong(MenuItemView menuItemView) {
        orderBongListLinearLayout = orderBongListView.findViewById(R.id.orderBongListLinearLayout);
        BongItemView bongItemView = new BongItemView(getContext(), menuItemView.getMenuItem(), null);
        orderBongListLinearLayout.addView(bongItemView, 0);
        menuItemList.add(menuItemView.getMenuItem());
    }


    // Temporary location for getting food from database
    public void getFoods() {
        mAPIService.getFoods().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Foods>() {
                    @Override
                    public void onCompleted() {
                        setMenuItemListener();
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("error", "onError: " + e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(Foods response) {
                        Log.i("Retrofit RxJava", "get submitted to API." + response.toString());

                        ArrayList<MenuItem> menuItemResponsList = new ArrayList<>(response.getFoods());

                        //Start sorting the foodobjects
                        menuContainerView.createMenuCategories(menuItemResponsList);
                    }

                });

    }


    public void getDrinks() {
        mAPIService.getDrinks().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Drinks>() {
                    @Override
                    public void onCompleted() {
                        setMenuItemListener();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Retrofit RxJava", e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(Drinks response) {
                        Log.i("Retrofit RxJava", "get submitted to API." + response.toString());
                        ArrayList<MenuItem> menuItemResponsList = new ArrayList<>(response.getDrinks());

                        //Start sorting the foodobjects
                        menuContainerView.createMenuCategories(menuItemResponsList);
                    }
                });
    }


    private void sendOrder(View v) {
        OrderBongHeaderView orderBongHeaderView = orderBongContainerView.findViewById(R.id.orderBongHeaderView);
        TextView waiterName = orderBongHeaderView.findViewById(R.id.waiterName);
        TextView tableNumber = orderBongHeaderView.findViewById(R.id.tableNumber);
        TextView orderNumber = orderBongHeaderView.findViewById(R.id.orderNumber);
        TextView time = orderBongHeaderView.findViewById(R.id.time);

        OrderRows orderRows = new OrderRows();

        menuItemList.forEach(menuItem -> {
            OrderRow orderRow = new OrderRow();
        });

        Order order = new Order();
    }

    //remove all items from bong list
    private void removeAllItemFromBong(View v) {
        LinearLayout orderBongListLinearLayout = v.findViewById(R.id.orderBongListLinearLayout);
        orderBongListLinearLayout.removeAllViews();

    }

    private void popupWindow(View v) {
        //startActivity(new Intent(OrderOverviewFragment.this,orderOverviewPopUp.class));
        Intent intent = new Intent(OrderOverviewFragment.this.getContext(), orderOverviewPopUp.class);
        startActivity(intent);
    }

    public void getOrderRows() {
        mAPIService.getOrderRows().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OrderRows>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Retrofit RxJava", e.toString());
                    }

                    // Called on every new observed item
                    @Override
                    public void onNext(OrderRows response) {
                        response.getOrderRows().forEach(orderRow -> {
                            String orderTableID = orderRow.getOrderId().getTableId().getTableId();

                            if (Integer.parseInt(orderTableID) == tableID) {
                                addOrderRowToBong(orderRow);
                            }
                        });
                        Log.i("Retrofit RxJava", "get submitted to API." + response.toString());
                    }
                });
    }

    private void addOrderRowToBong(OrderRow orderRow) {
        BongItemView bongItemView = new BongItemView(getContext(), orderRow.getFoodId(), orderRow.getOrderChange());
        orderBongListLinearLayout.addView(bongItemView, 0);
    }

    private void postOrder(Order order) {
        mAPIService.postOrder(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit POST", response.body().toString());
                    Log.i("Retrofit POST", "order post submitted to API.");

                    // Post a new OrderRow using the new OrderID
                    OrderRow orderRow = new OrderRow(response.body(), null, new Food("3"), null);
                    postOrderRow(orderRow);
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e("Retrofit POST", "Unable to submit order post to API." + t.toString());
                t.printStackTrace();
            }
        });
    }

    private void postOrderRow(OrderRow orderRow) {
        mAPIService.postOrderRow(orderRow).enqueue(new Callback<OrderRow>() {
            @Override
            public void onResponse(Call<OrderRow> call, Response<OrderRow> response) {

                if (response.isSuccessful()) {
                    // TODO: Show success message
                    Log.i("Retrofit POST", response.body().toString());
                    Log.i("Retrofit POST", "order post submitted to API.");
                }
            }

            @Override
            public void onFailure(Call<OrderRow> call, Throwable t) {
                Log.e("Retrofit POST", "Unable to submit order post to API." + t.toString());
                t.printStackTrace();
            }
        });
    }

}
