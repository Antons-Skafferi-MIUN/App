package com.example.myapplication.API;

public class ApiUtils {
    //  public static final String BASE_URL = "http://192.168.0.161:8080/AntonAPI/webresources/";
    public static final String BASE_URL = "http://10.250.120.152:8080/AntonAPI/webresources/";


    private ApiUtils() {
    }

    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
