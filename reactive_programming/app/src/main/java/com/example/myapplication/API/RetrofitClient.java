package com.example.myapplication.API;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    private RetrofitClient() {

    }

    /**
     * Constructs a new Retrofit client if it doesn't exist yet.
     * This to prevent multiple clients talking to the database from the same device.
     *
     * @param baseUrl to the database
     * @return a Retrofit client
     */
    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
