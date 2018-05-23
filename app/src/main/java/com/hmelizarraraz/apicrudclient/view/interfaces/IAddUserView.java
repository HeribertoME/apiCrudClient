package com.hmelizarraraz.apicrudclient.view.interfaces;

import android.content.Context;

public interface IAddUserView {

    void showNameError();
    void showEmailError();

    void showPasswordError();

    void showPasswordConfirmationError();

    void showRegisterError(String error, Context context);

    void showLoadingIndicator(boolean show);

    void signUp(String name, String email, String password,
                String password_confirmation, String gender);

    void navigateToMain();

    void setUpPresenterRegister();
}
