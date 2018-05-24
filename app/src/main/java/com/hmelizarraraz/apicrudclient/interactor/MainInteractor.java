package com.hmelizarraraz.apicrudclient.interactor;

import android.content.Context;
import android.util.Log;

import com.hmelizarraraz.apicrudclient.interactor.interfaces.IMainInteractor;
import com.hmelizarraraz.apicrudclient.pojo.Base;
import com.hmelizarraraz.apicrudclient.pojo.User;
import com.hmelizarraraz.apicrudclient.restAPI.EndpointsApi;
import com.hmelizarraraz.apicrudclient.restAPI.adapter.RestApiAdapter;
import com.hmelizarraraz.apicrudclient.restAPI.model.ApiError;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractor implements IMainInteractor {

    private static final String TAG = MainInteractor.class.getSimpleName();

    @Override
    public void getUsersFromAPI(Context context, final OnUserListener listener) {
        RestApiAdapter adapter = new RestApiAdapter();
        EndpointsApi endpointsApi = adapter.establecerConexion();
        Call<Base<ArrayList<User>>> userResponseCall = endpointsApi.getUsers();

        userResponseCall.enqueue(new Callback<Base<ArrayList<User>>>() {
            @Override
            public void onResponse(Call<Base<ArrayList<User>>> call, Response<Base<ArrayList<User>>> response) {
                if (!response.isSuccessful()) {
                    assert response.errorBody() != null;
                    if (response.errorBody().contentType().type().equals("text")) {
                        try {
                            listener.onError(response.errorBody().string());
                            return;
                        } catch (IOException e) {
                            Log.e(TAG, e.getMessage());
                            e.printStackTrace();
                            listener.onError("Algo salió mal, intenta más tarde");
                            return;
                        }
                    } else {
                        ApiError apiError = ApiError.fromResponseBody(response.errorBody());
                        listener.onError(apiError != null ? apiError.getError(): "Algo salió mal, intenta más tarde");
                        return;
                    }
                }

                listener.onGetUsersSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<Base<ArrayList<User>>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
                listener.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getUsersFromDB(Context context, OnUserListener listener) {
        // TODO Obtener usuarios de base de datos sqlite
    }

    @Override
    public void getUsersFromDummy(Context context, OnUserListener listener) {
        ArrayList<User> users = new ArrayList<>();

        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        User user5 = new User();

        user1.setId(1);
        user1.setName("DUMMY 1");
        user1.setEmail("dummy1@mail.com");
        users.add(user1);

        user2.setId(2);
        user2.setName("DUMMY 2");
        user2.setEmail("dummy2@mail.com");
        users.add(user2);

        user3.setId(3);
        user3.setName("DUMMY 3");
        user3.setEmail("dummy3@mail.com");
        users.add(user3);

        user4.setId(4);
        user4.setName("DUMMY 4");
        user4.setEmail("dummy4@mail.com");
        users.add(user4);

        user5.setId(5);
        user5.setName("DUMMY 5");
        user5.setEmail("dummy5@mail.com");
        users.add(user5);

        listener.onGetUsersSuccess(users);
    }

}
