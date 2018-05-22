package com.hmelizarraraz.apicrudclient.restAPI.adapter;

import com.hmelizarraraz.apicrudclient.restAPI.EndpointsApi;
import com.hmelizarraraz.apicrudclient.restAPI.RestApiConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    /**
     * Establecer la conexion con el servidor API
     */
    public EndpointsApi establecerConexion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApiConstants.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndpointsApi.class);
    }
}
