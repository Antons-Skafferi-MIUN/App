package se.miun.dt170.antonsskafferi.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import se.miun.dt170.antonsskafferi.data.model.Foodss;

public interface ApiService {
    @GET("entities.foods")
    Call<Foodss> getFoods();
}