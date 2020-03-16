package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_menu_item_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.util.HashMap;
import java.util.Map;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.model.MenuItem;

import static android.content.ContentValues.TAG;

public class MenuItemView extends CardView implements Cloneable {

    private MenuItem menuItem;
    private Map<String, String>map;
    private int internalcounter = 0;


    public MenuItemView(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        Log.d(TAG, "MenuItemView: CTOR 1 ");
        String idnr = String.valueOf(internalcounter);
        //menuItem.setId(idnr);

    }

    public MenuItemView(@NonNull Context context, MenuItem menuItem) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_item_view, this, true);

        this.menuItem = menuItem;
        TextView menuItemNameTextView = this.findViewById(R.id.menuItemName);
        menuItemNameTextView.setText(menuItem.getName());

        TextView menuItemPriceTextView = this.findViewById(R.id.menuItemPrice);
        menuItemPriceTextView.setText(menuItem.getPrice());

        setId(R.id.menuItemView);

        String idnr = String.valueOf(internalcounter);
        menuItem.setId(idnr);
        Log.d(TAG, "MenuItemView: CTOR 2 ");
    }


    public MenuItemView (@NonNull Context context, MenuItem menuItem, String id){
        super(context);

        this.menuItem.setId(id);
        this.map = new HashMap<String,String>(){
            {put("dfdf", id);}
        };

    }

    public MenuItemView clone() throws CloneNotSupportedException {
        MenuItemView iv = (MenuItemView) super.clone();

        iv.map = new HashMap<>(map);

        return iv;  //return deep copy

    }


    public MenuItem getMenuItem() {
        return menuItem;

    }

}

