package se.miun.dt170.antonsskafferi.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import se.miun.dt170.antonsskafferi.R;
import se.miun.dt170.antonsskafferi.data.model.Foodss;
import se.miun.dt170.antonsskafferi.data.remote.ApiService;
import se.miun.dt170.antonsskafferi.data.remote.ApiUtils;
import se.miun.dt170.antonsskafferi.ui.kitchen.KitchenBongContainerView;

/**
 * Kitchen activity is the root for the kitchen navigation graph
 */
public class KitchenActivity extends AppCompatActivity {

    private Map<String, KitchenBongContainerView> KitchenBongContainerViews;
    private ApiService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        KitchenBongContainerViews = new HashMap<>();

        // Create view variables
        FlexboxLayout bongListLayoutContainer = findViewById(R.id.bongListContainer);

        // TEMPORARY CODE
        // Add one bong list
        KitchenBongContainerViews.put("1", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("2", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("3", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("4", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("5", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("6", new KitchenBongContainerView(this, this));
        KitchenBongContainerViews.put("7", new KitchenBongContainerView(this, this));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("1"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("2"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("3"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("4"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("5"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("6"));
        bongListLayoutContainer.addView(KitchenBongContainerViews.get("7"));

        // API testing
        mAPIService = ApiUtils.getAPIService();

        getFoods();
    }

    public void getFoods() {
        mAPIService.getFoods().enqueue(new Callback<Foodss>() {
            @Override
            public void onResponse(Call<Foodss> Foodss, Response<Foodss> response) {
                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i("Retrofit", "get submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Foodss> call, Throwable t) {
                Log.e("Retrofit", "Unable to submit post to API." + t.toString());
            }
        });
    }

    public void showResponse(String response) {
        Log.i("Retrofit", response);

//        if (mResponseTv.getVisibility() == View.GONE) {
//            mResponseTv.setVisibility(View.VISIBLE);
//        }
//        mResponseTv.setText(response);
    }
}
