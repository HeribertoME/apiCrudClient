package com.hmelizarraraz.apicrudclient.presenter;

import android.content.Context;

import com.hmelizarraraz.apicrudclient.interactor.interfaces.IMainInteractor;
import com.hmelizarraraz.apicrudclient.pojo.User;
import com.hmelizarraraz.apicrudclient.presenter.interfaces.IMainPresenter;
import com.hmelizarraraz.apicrudclient.view.interfaces.IMainView;

import java.util.ArrayList;

public class MainPresenter implements IMainPresenter, IMainInteractor.OnUserListener {

    public static final String TAG = MainPresenter.class.getSimpleName();

    private IMainView view;
    private IMainInteractor interactor;
    private Context context;

    public MainPresenter(IMainView view, IMainInteractor interactor, Context context) {
        this.view = view;
        this.interactor = interactor;
        this.context = context;
    }

    @Override
    public void getUsersList() {
        interactor.getUsersFromAPI(context, this);
    }

    @Override
    public void onGetUsersSuccess(ArrayList<User> users) {
        if (view != null){
            view.generateLinearLayoutVertical();
            view.initializeAdapter(view.createAdapter(users));
        }
    }

    @Override
    public void onError(String err) {
        if (view != null) {
            view.showError(err);
        }
    }
}
