package com.hmelizarraraz.apicrudclient.view.interfaces;

import com.hmelizarraraz.apicrudclient.adapter.UserAdapter;
import com.hmelizarraraz.apicrudclient.pojo.User;

import java.util.ArrayList;

public interface IMainView {

    void setUpPresenter();
    void getUsers();
    void generateLinearLayoutVertical();
    UserAdapter createAdapter(ArrayList<User> users);
    void initializeAdapter(UserAdapter adapter);
    void showError(String err);

    void showProgressBar(boolean show);
    void addUser();

}