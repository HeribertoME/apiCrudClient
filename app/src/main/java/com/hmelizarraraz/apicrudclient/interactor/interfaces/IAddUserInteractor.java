package com.hmelizarraraz.apicrudclient.interactor.interfaces;

import android.content.Context;

public interface IAddUserInteractor {

    void register(String name, String email, String password, String password_confirmation,
                String gender, Context context, IMainInteractor.OnUserListener listener);

    boolean isValidEmail(String email);

    boolean isConfirmationPassword(String password, String password_confirmation);
}
