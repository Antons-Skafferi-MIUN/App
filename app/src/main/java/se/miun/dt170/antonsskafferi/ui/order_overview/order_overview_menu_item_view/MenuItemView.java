package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_item_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.model.Drink;
import se.miun.dt170.antonsskafferi.data.model.Food;

public class MenuItemView extends CardView {
    private Drink drink;
    private Food food;

    public MenuItemView(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MenuItemView(@NonNull Context context, Food food) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_item_view, this, true);

        this.food = food;

        TextView menuItemNameTextView = this.findViewById(R.id.menuItemName);
        menuItemNameTextView.setText(food.getFoodName());

        TextView menuItemPriceTextView = this.findViewById(R.id.menuItemPrice);
        menuItemPriceTextView.setText(food.getFoodPrice());

        setId(R.id.menuItemView);
    }

    public MenuItemView(@NonNull Context context, Drink drink) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_item_view, this, true);

        this.drink = drink;

        TextView menuItemNameTextView = this.findViewById(R.id.menuItemName);
        menuItemNameTextView.setText(drink.getDrinkName());

        TextView menuItemPriceTextView = this.findViewById(R.id.menuItemPrice);
        menuItemPriceTextView.setText(drink.getDrinkPrice());

        setId(R.id.menuItemView);
    }

    public Food getFood() {
        return food;
    }

    public Drink getDrink() {
        return drink;
    }
}
