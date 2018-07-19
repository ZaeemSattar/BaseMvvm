package com.ctandem.basemvvm.service.retrofit;

import com.ctandem.basemvvm.helpers.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zaeem Sattar on 7/6/2018.
 */
public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        return retrofit;
    }

}
