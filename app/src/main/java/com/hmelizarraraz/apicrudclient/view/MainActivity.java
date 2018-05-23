package com.hmelizarraraz.apicrudclient.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
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
    private FloatingActionButton fabAdd;
    private ProgressBar progressBar;

    private IMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI
        rvUsers     = findViewById(R.id.rvUsers);
        fabAdd      = findViewById(R.id.fabAdd);
        progressBar = findViewById(R.id.progressBar);
        
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

        setUpPresenter();
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

    @Override
    public void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void addUser() {
        startActivity(new Intent(MainActivity.this, AddUserActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUsers();
    }
}
