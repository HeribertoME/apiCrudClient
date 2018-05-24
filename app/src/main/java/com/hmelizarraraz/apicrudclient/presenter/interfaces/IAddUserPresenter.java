package com.hmelizarraraz.apicrudclient.presenter.interfaces;

public interface IAddUserPresenter {

    void validateRegister(String name, String email, String password, String password_confirmation, String gender);
}
