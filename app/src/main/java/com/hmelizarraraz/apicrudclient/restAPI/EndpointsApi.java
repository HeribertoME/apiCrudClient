package com.hmelizarraraz.apicrudclient.restAPI;

import com.hmelizarraraz.apicrudclient.pojo.Base;
import com.hmelizarraraz.apicrudclient.pojo.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointsApi {

    @GET(RestApiConstants.URL_GET_USERS)
    Call<Base<ArrayList<User>>> getUsers();

}
