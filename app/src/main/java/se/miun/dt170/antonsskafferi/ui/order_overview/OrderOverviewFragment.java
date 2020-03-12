package se.miun.dt170.antonsskafferi.ui.order_overview;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.APIWrappers.PostWrapper;
import se.miun.dt170.antonsskafferi.data.utility.DateConverter;
import se.miun.dt170.antonsskafferi.data.model.Drinks;
import se.miun.dt170.antonsskafferi.data.model.Foods;
import se.miun.dt170.antonsskafferi.data.model.MenuItem;
import se.miun.dt170.antonsskafferi.data.model.Order;
import se.miun.dt170.antonsskafferi.data.model.OrderRow;
import se.miun.dt170.antonsskafferi.data.model.OrderRows;
import se.miun.dt170.antonsskafferi.data.model.RestaurantTable;
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
import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * This is the fullscreen fragment for taking a order
 * Contains a {@link OrderOverviewBongHeaderFragment} above the bong list to the right.
 * A {@link se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_courses.OrderOverviewCoursesFragment} containing available courses in the middle.
 * A {@link se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_drinks.OrderOverviewDrinksFragment} containing available drinks in the middle.
 * A {@link se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_navbar.OrderOverviewNavbarFragment} for the navigation top bar.
 */
public class OrderOverviewFragment extends Fragment implements View.OnClickListener {

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
    private String waiterName;
    List<TextView> textViews = new ArrayList<>();

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

        fillBongWithOrders();


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

        waiterName = this.getActivity().getIntent().getStringExtra("DISPLAY_NAME");
        TextView waitername = orderBongHeaderView.findViewById(R.id.waiterName);
        waitername.setText("Servitör: " + waiterName);

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
                popupWindow(v, null);
                break;
            case R.id.deleteButton:
                Toast.makeText(getActivity(), "DELETE", Toast.LENGTH_SHORT).show();
                removeItemFromBongList(v);
                break;
        }
    }

    private void addMenuItemToBong(MenuItemView menuItemView) {
        orderBongListLinearLayout = orderBongListView.findViewById(R.id.orderBongListLinearLayout);
        BongItemView bongItemView = new BongItemView(getContext(), menuItemView.getMenuItem(), null);
        orderBongListLinearLayout.addView(bongItemView, 0);
        TextView textView = (TextView) bongItemView.findViewById(R.id.extraText);
        textViews.add(textView);
        menuItemList.add(menuItemView.getMenuItem());
        Log.d(TAG, "addMenuItemToBong: ");

        YoYo.with(Techniques.Pulse).duration(75).repeat(0).playOn(menuItemView);
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
        if (menuItemList.size() > 0) {
            DateConverter dateConverter = new DateConverter();
            Order order = new Order(new RestaurantTable(Integer.toString(tableID)), dateConverter.getCurrentTime());

            PostWrapper postWrapper = new PostWrapper();
            postWrapper.postOrder(order, menuItemList);

            NavDirections action = OrderOverviewFragmentDirections.actionOrderOverviewFragmentToTableOverviewFragment();
            Navigation.findNavController(getView()).navigate(action);
        }
    }

    //reverse arrayList items
    static <T> List<T> reverse(final List<T> list) {
        final List<T> result = new ArrayList<>(list);
        Collections.reverse(result);
        return result;
    }

    //remove clicked item from bong list - one at time
    private void removeItemFromBongList(View v) {
        LinearLayout orderBongListLinearLayout = orderBongListView.findViewById(R.id.orderBongListLinearLayout);
        for (int i = 0; i < orderBongListLinearLayout.getChildCount(); i++) {
            View bongView = orderBongListLinearLayout.getChildAt(i);
            if (bongView instanceof BongItemView) {
                String colorCompаre = "597063"; //orange color
                String backgroundColor = "";
                Drawable background = bongView.getBackground();
                if (background instanceof GradientDrawable) {
                    GradientDrawable gradientDrawable = (GradientDrawable) background;
                    try{
                        String colorState = gradientDrawable.getColor().toString();
                        int colorStateLength = colorState.length();
                        backgroundColor = colorState.substring(colorStateLength - 7, colorStateLength - 1);
                        Log.d("Color", backgroundColor);
                    }
                    catch (Exception e){}
                }
                if (colorCompаre.equals(backgroundColor)){
                    try {
                        orderBongListLinearLayout.removeViewAt(i);
                        menuItemList.remove(((BongItemView) bongView).getMenuItem());
                    }
                    catch (Exception e) { }
                }
            }
        }
    }

    private void popupWindow(View v, String extra) {
        LinearLayout orderBongListLinearLayout = orderBongListView.findViewById(R.id.orderBongListLinearLayout);
        for (int i = 0; i < orderBongListLinearLayout.getChildCount(); i++) {
            View bongView = orderBongListLinearLayout.getChildAt(i);
            if (bongView instanceof BongItemView) {
                String colorCompаre = "597063"; //orange color
                String backgroundColor = "";
                Drawable background = bongView.getBackground();
                if (background instanceof GradientDrawable) {
                    GradientDrawable gradientDrawable = (GradientDrawable) background;
                    try{
                        String colorState = gradientDrawable.getColor().toString();
                        int colorStateLength = colorState.length();
                        backgroundColor = colorState.substring(colorStateLength - 7, colorStateLength - 1);
                        Log.d("Color", backgroundColor);
                    }
                    catch (Exception e){}
                }
                if (colorCompаre.equals(backgroundColor)){
                    if (v != null && extra == null){
                        startActivityForResult(new Intent(OrderOverviewFragment.this.getContext(),orderOverviewPopUp.class),999);
                    }
                    try {
                        List<TextView> textViewsReverse = reverse(textViews);
                        Log.d("Text", textViewsReverse.get(i).getText().toString());
                        textViewsReverse.get(i).setText(extra);
                        Log.d("Text", textViewsReverse.get(i).getText().toString());
                        ((BongItemView) bongView).getMenuItem().setOrderChanged(extra);
                    }
                    catch (Exception e){}
                }
            }
        } 
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 999 && resultCode == orderOverviewPopUp.RESULT_OK){
            String extraText = data.getStringExtra("EXTRA");
            popupWindow(null, extraText);
        }
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
        CheckBox checkBox = bongItemView.findViewById(R.id.checkBox);
        checkBox.setVisibility(View.GONE);
        orderBongListLinearLayout.addView(bongItemView, 0);
    }
}
