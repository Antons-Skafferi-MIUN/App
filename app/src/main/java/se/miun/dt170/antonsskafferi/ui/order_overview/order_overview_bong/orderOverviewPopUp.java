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

    private CheckBox extraPotatis, extraSalad, stektPotatis, pommesFrittes, allergiGluten, ejSos, ejLingonsylt, ejSalt;
    private Button confirmButton;
    private TextView testResultat;
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

        //checkboxes
        extraPotatis = findViewById(R.id.extraPotatis);
        extraSalad = findViewById(R.id.extraSalad);
        stektPotatis = findViewById(R.id.stektPotatis);
        pommesFrittes = findViewById(R.id.pommesFrittes);
        allergiGluten = findViewById(R.id.allergiGluten);
        ejSos = findViewById(R.id.ejSos);
        ejLingonsylt = findViewById(R.id.ejLingonsylt);
        ejSalt = findViewById(R.id.ejSalt);

        edit = findViewById(R.id.editText);
        confirmButton = findViewById(R.id.confirm);
        testResultat = findViewById(R.id.resultat);

        resultat = new ArrayList<>();
        testResultat.setEnabled(false);

        //checkboxs listeners
        extraPotatis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(extraPotatis.isChecked())
                    resultat.add("- Extra potatis");
                else
                    resultat.remove("- Extra potatis");
            }
        });

        extraSalad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(extraSalad.isChecked())
                    resultat.add("- Extra salad");
                else
                    resultat.remove("- Extra salad");
            }
        });

        stektPotatis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stektPotatis.isChecked())
                    resultat.add("- Stekt potatis");
                else
                    resultat.remove("- Stekt potatis");
            }
        });

        pommesFrittes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pommesFrittes.isChecked())
                    resultat.add("- Pommes frittes");
                else
                    resultat.remove("- Pommes frittes");
            }
        });

        allergiGluten.setOnClickListener(new View.OnClickListener() {
        @Override
           public void onClick(View v) {
                if (allergiGluten.isChecked())
                   resultat.add("- Allergi/Gluten");
                else
                    resultat.remove("- Allergi/Gluten");
            }
        });

        ejSos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ejSos.isChecked())
                    resultat.add("- Ej sås");
                else
                    resultat.remove("- Ej sås");
            }
        });

        ejLingonsylt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ejLingonsylt.isChecked())
                    resultat.add("- Ej lingonsylt");
                else
                    resultat.remove("- Ej lingonsylt");
            }
        });

        ejSalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ejSalt.isChecked())
                    resultat.add("- Ej salt");
                else
                    resultat.remove("- Ej salt");
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String extraTextFinal = "";
                extraTextFinal = resulttostring();
                //testresultat.setText(extratext1);
                //testresultat.setEnabled(false);

                Intent i = new Intent();
                i.putExtra("EXTRA", extraTextFinal);
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
        String extraText;
        extraText = stringBuilder.toString();

        return extraText;
    }

}
