package com.hmelizarraraz.apicrudclient.presenter;

import android.content.Context;

import com.hmelizarraraz.apicrudclient.interactor.interfaces.IAddUserInteractor;
import com.hmelizarraraz.apicrudclient.interactor.interfaces.IMainInteractor;
import com.hmelizarraraz.apicrudclient.pojo.User;
import com.hmelizarraraz.apicrudclient.presenter.interfaces.IAddUserPresenter;
import com.hmelizarraraz.apicrudclient.view.interfaces.IAddUserView;

import java.util.ArrayList;

public class AddUserPresenter implements IAddUserPresenter, IMainInteractor.OnUserListener {

    private IAddUserView view;
    private IAddUserInteractor interactor;
    private Context context;

    public AddUserPresenter(IAddUserView view, IAddUserInteractor interactor, Context context) {
        this.view = view;
        this.interactor = interactor;
        this.context = context;
    }

    @Override
    public void validateRegister(String name, String email, String password, String password_confirmation, String gender) {
        if (view != null) {
            view.showLoadingIndicator(true);
            interactor.register(name, email, password, password_confirmation, gender, context, this);
        }

    }

    @Override
    public void onGetUsersSuccess(ArrayList<User> users) {}

    @Override
    public void onRegisterSuccess(int id) {
        if (view != null) {
            view.navigateToMain(id);
        }
    }

    @Override
    public void onNameError() {
        if (view != null) {
            view.showNameError();
            view.showLoadingIndicator(false);
        }
    }

    @Override
    public void onEmailError() {
        if (view != null) {
            view.showEmailError();
            view.showLoadingIndicator(false);
        }
    }

    @Override
    public void onPasswordError() {
        if (view != null) {
            view.showPasswordError();
            view.showLoadingIndicator(false);
        }
    }

    @Override
    public void onPasswordConfirmationError() {
        if (view != null) {
            view.showPasswordConfirmationError();
            view.showLoadingIndicator(false);
        }
    }

    @Override
    public void onError(String err) {
        if (view != null) {
            view.showRegisterError(err, context);
            view.showLoadingIndicator(false);
        }
    }
}
