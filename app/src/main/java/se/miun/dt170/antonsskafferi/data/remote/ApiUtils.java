package se.miun.dt170.antonsskafferi.data.remote;

public class ApiUtils {
    public static final String BASE_URL = "http://10.250.120.152:8080/antondb/webresources/";

    private ApiUtils() {
    }

    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
