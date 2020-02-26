package se.miun.dt170.antonsskafferi.ui.bong;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.stream.Collectors;

import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.Item;

public class BongItemView extends ConstraintLayout {

    private TextView itemText;
    private TextView extraText;

    public BongItemView(@NonNull Context context, Item item) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bong_item_view, this, true);

        itemText = findViewById(R.id.foodNameText);
        extraText = findViewById(R.id.extraText);

        itemText.setText(item.getName());

        if (item.getExtras() != null) {
            String extraString = item.getExtras().stream().collect(Collectors.joining("\n"));
            extraText.setVisibility(VISIBLE);
            extraText.setText(extraString);
        }
    }
}
