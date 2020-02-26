package se.miun.dt170.antonsskafferi.ui.bong;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import se.miun.dt170.antonsskafferi.R;

public class BongItemView extends ConstraintLayout {

    public BongItemView(@NonNull Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bong_item, this, true);
    }
}
