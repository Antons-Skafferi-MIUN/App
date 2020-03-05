package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_bong;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

import se.miun.dt170.antonsskafferi.R;

public class orderOverviewPopUp extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.order_bong_popup);

        //storleken p popup:n

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));


        //f[ bort s[ att h;rnorna 'r runda

        


        //View v = getWindow().getDecorView();
        //v.setBackgroundResource(android.R.color.transparent);



    }
}
