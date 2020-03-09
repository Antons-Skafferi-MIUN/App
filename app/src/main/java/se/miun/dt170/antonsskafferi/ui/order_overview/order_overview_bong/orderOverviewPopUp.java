package se.miun.dt170.antonsskafferi.ui.order_overview.order_overview_bong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import se.miun.dt170.antonsskafferi.R;

public class orderOverviewPopUp extends Activity {

    private CheckBox extrapotatis, allergigluten;
    private Button confirmbutton;
    private TextView testresultat;
    private ArrayList<String> resultat;
    private EditText edit;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.order_bong_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        //test
        extrapotatis = findViewById(R.id.extraPotatis);
        allergigluten = findViewById(R.id.allergiGluten);
        edit = findViewById(R.id.editText);


        confirmbutton = findViewById(R.id.confirm);
        testresultat = findViewById(R.id.resultat);


        resultat = new ArrayList<>();
        testresultat.setEnabled(false);



        extrapotatis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(extrapotatis.isChecked())
                    resultat.add("- Extra potatis");
                else
                    resultat.remove("- Extra potatis");
            }
        });

       allergigluten.setOnClickListener(new View.OnClickListener() {
        @Override
           public void onClick(View v) {
                if (allergigluten.isChecked())
                   resultat.add("- Allergi/Gluten");
                else
                    resultat.remove("- Allergi/Gluten");
            }
        });

        confirmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String extratext1 = "";
                extratext1 = resulttostring();
                //testresultat.setText(extratext1);
                //testresultat.setEnabled(false);

                Intent i = new Intent();
                i.putExtra("EXTRA", extratext1);
                setResult(RESULT_OK,i);
                finish();

            }
        });
    }

    public String resulttostring() {
        String descpr = edit.getText().toString();
        if (!descpr.isEmpty())
            resultat.add("- " + descpr);
        StringBuilder stringBuilder = new StringBuilder();
        for(String s: resultat)
            stringBuilder.append(s).append("\n");
        String extratext;
        extratext = stringBuilder.toString();

        return extratext;
    }

}
