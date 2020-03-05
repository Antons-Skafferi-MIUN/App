package se.miun.dt170.antonsskafferi.data.remote;

public class ApiUtils {
    public static final String BASE_URL = "http://192.168.0.161:8080/AntonAPI/webresources/";

    private ApiUtils() {
    }

    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
