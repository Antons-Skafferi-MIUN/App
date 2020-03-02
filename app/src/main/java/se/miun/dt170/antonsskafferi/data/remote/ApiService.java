package se.miun.dt170.antonsskafferi.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import se.miun.dt170.antonsskafferi.data.model.Drinks;
import se.miun.dt170.antonsskafferi.data.model.Foods;

public interface ApiService {

    @GET("entities.foods")
    Call<Foods> getFoods();

    @GET("entities.drinks")
    Call<Drinks> getDrinks();
}