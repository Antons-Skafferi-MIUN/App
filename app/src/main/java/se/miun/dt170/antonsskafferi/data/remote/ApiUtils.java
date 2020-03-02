package se.miun.dt170.antonsskafferi.data.remote;

public class ApiUtils {
    public static final String BASE_URL = "http://10.250.121.142:8080/antonapi/webresources/";

    private ApiUtils() {
    }

    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
