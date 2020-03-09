package se.miun.dt170.antonsskafferi.data.remote;

public class ApiUtils {

   //public static final String BASE_URL = "http://192.168.1.2:8080/AntonAPI/webresources/";
   public static final String BASE_URL = "http://10.250.120.152:8080/AntonAPI/webresources/";


    private ApiUtils() {
    }

    /**
     * Get the Retrofit client used to make database connections.
     *
     * @return a singleton Retrofit client
     */
    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
