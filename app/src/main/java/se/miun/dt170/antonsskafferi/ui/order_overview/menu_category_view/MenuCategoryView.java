package se.miun.dt170.antonsskafferi.ui.order_overview.menu_category_view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.MenuItem;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MenuCategoryView extends LinearLayout {

    private TextView categoryNameText;


    public MenuCategoryView(Context context, MenuItem menuitem) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.menu_category_view, this, true);

        categoryNameText = findViewById(R.id.categorynameTextView);

        categoryNameText.setText(menuitem.getCategory());

        Log.d("ZZZZZZZZZZZZZ", menuitem.getCategory());


    }

}