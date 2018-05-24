package com.hmelizarraraz.apicrudclient.interactor.interfaces;

import android.content.Context;

import com.hmelizarraraz.apicrudclient.pojo.User;

import java.util.ArrayList;

public interface IMainInteractor {

    void getUsersFromAPI(Context context, OnUserListener listener);
    void getUsersFromDB();
    void getUsersFromDummy();

    interface OnUserListener {
        void onGetUsersSuccess(ArrayList<User> users);
        void onRegisterSuccess(int id);

        void onNameError();
        void onEmailError();
        void onPasswordError();
        void onPasswordConfirmationError();

        void onError(String err);
    }
}
