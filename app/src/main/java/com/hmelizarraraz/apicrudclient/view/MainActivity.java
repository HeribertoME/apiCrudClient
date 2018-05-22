package com.hmelizarraraz.apicrudclient.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.hmelizarraraz.apicrudclient.R;
import com.hmelizarraraz.apicrudclient.adapter.UserAdapter;
import com.hmelizarraraz.apicrudclient.interactor.MainInteractor;
import com.hmelizarraraz.apicrudclient.pojo.User;
import com.hmelizarraraz.apicrudclient.presenter.MainPresenter;
import com.hmelizarraraz.apicrudclient.presenter.interfaces.IMainPresenter;
import com.hmelizarraraz.apicrudclient.view.interfaces.IMainView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainView {

    // UI Members
    private RecyclerView rvUsers;

    private IMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI
        rvUsers = findViewById(R.id.rvUsers);

        setUpPresenter();
        getUsers();
    }

    @Override
    public void setUpPresenter() {
        presenter = new MainPresenter(this, new MainInteractor(), MainActivity.this);
    }

    @Override
    public void getUsers() {
        presenter.getUsersList();
    }

    @Override
    public void generateLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvUsers.setLayoutManager(llm);
    }

    @Override
    public UserAdapter createAdapter(ArrayList<User> users) {
        return new UserAdapter(users, MainActivity.this);
    }

    @Override
    public void initializeAdapter(UserAdapter adapter) {
        rvUsers.setAdapter(adapter);
    }

    @Override
    public void showError(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }
}
