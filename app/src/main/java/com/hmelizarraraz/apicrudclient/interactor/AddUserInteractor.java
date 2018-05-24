package com.hmelizarraraz.apicrudclient.interactor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hmelizarraraz.apicrudclient.interactor.interfaces.IAddUserInteractor;
import com.hmelizarraraz.apicrudclient.interactor.interfaces.IMainInteractor;
import com.hmelizarraraz.apicrudclient.pojo.Base;
import com.hmelizarraraz.apicrudclient.pojo.User;
import com.hmelizarraraz.apicrudclient.restAPI.EndpointsApi;
import com.hmelizarraraz.apicrudclient.restAPI.adapter.RestApiAdapter;
import com.hmelizarraraz.apicrudclient.restAPI.model.ApiError;

import java.io.IOException;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserInteractor implements IAddUserInteractor {

    private static final String TAG = AddUserInteractor.class.getSimpleName();

    @Override
    public void register(String name, String email, String password, String password_confirmation, String gender, Context context, final IMainInteractor.OnUserListener listener) {

        if (TextUtils.isEmpty(name)) {
            listener.onNameError();
            return;
        }

        if (TextUtils.isEmpty(email) || !isValidEmail(email)) {
            listener.onEmailError();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            return;
        }

        if (TextUtils.isEmpty(password_confirmation) || !isConfirmationPassword(password, password_confirmation)) {
            listener.onPasswordConfirmationError();
            return;
        }

        RestApiAdapter apiAdapter = new RestApiAdapter();

        EndpointsApi endpointRegister = apiAdapter.establecerConexion();

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPassword_confirmation(password_confirmation);
        user.setGenre(gender);

        Call<Base<User>> userCall = endpointRegister.createUser(user);

        userCall.enqueue(new Callback<Base<User>>() {
            @Override
            public void onResponse(Call<Base<User>> call, Response<Base<User>> response) {
                // Error process
                if (!response.isSuccessful()) {
                    if (response.errorBody().contentType().type().equals("text")) {
                        try {
                            listener.onError(response.errorBody().string());
                            return;
                        } catch (IOException e) {
                            Log.e("RegisterInteractor", e.getMessage());
                            listener.onError("Algo salió mal, intenta más tarde");
                            return;
                        }
                    } else {
                        ApiError apiError = ApiError.fromResponseBody(response.errorBody());
                        listener.onError(apiError != null ? apiError.getError() : null);
                        return;
                    }
                }

                Log.d(TAG, response.body().getData().toString());
                listener.onRegisterSuccess(response.body().getData().getId());
            }

            @Override
            public void onFailure(Call<Base<User>> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                t.printStackTrace();
                listener.onError(t.getMessage());
            }
        });
    }

    @Override
    public boolean isValidEmail(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    @Override
    public boolean isConfirmationPassword(String password, String password_confirmation) {
        return password.equals(password_confirmation);
    }
}
