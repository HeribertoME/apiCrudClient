package com.hmelizarraraz.apicrudclient.view;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hmelizarraraz.apicrudclient.R;
import com.hmelizarraraz.apicrudclient.interactor.AddUserInteractor;
import com.hmelizarraraz.apicrudclient.presenter.AddUserPresenter;
import com.hmelizarraraz.apicrudclient.presenter.interfaces.IAddUserPresenter;
import com.hmelizarraraz.apicrudclient.utils.TextWatcherLabel;
import com.hmelizarraraz.apicrudclient.view.interfaces.IAddUserView;

public class AddUserActivity extends AppCompatActivity implements IAddUserView {

    // Elementos UI
    private TextInputLayout tilName, tilEmail, tilPassword, tilConfirmPassword;
    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private Button btnSend;
    private RadioGroup rgGender;
    private ProgressBar progressBar;

    private String gender;

    private Toolbar toolbar;

    private IAddUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        // Referencias UI
        toolbar = findViewById(R.id.toolbar);
        etName              = findViewById(R.id.tiet_name);
        tilName             = findViewById(R.id.til_name);
        etEmail             = findViewById(R.id.tiet_email);
        tilEmail            = findViewById(R.id.til_email);
        etPassword          = findViewById(R.id.tiet_password);
        tilPassword         = findViewById(R.id.til_password);
        etConfirmPassword   = findViewById(R.id.tiet_confirm_password);
        tilConfirmPassword  = findViewById(R.id.til_confirm_password);

        btnSend             = findViewById(R.id.btnRegister);

        rgGender            = findViewById(R.id.gender_radio_group);

        progressBar         = findViewById(R.id.progressBar);

        // Watchers
        etName.addTextChangedListener(new TextWatcherLabel(tilName));
        etEmail.addTextChangedListener(new TextWatcherLabel(tilEmail));
        etPassword.addTextChangedListener(new TextWatcherLabel(tilPassword));
        etConfirmPassword.addTextChangedListener(new TextWatcherLabel(tilConfirmPassword));

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedGender = rgGender.getCheckedRadioButtonId();
                if (selectedGender == R.id.female_radio_btn)
                    gender = getString(R.string.form_register_gender_woman_value);
                else
                    gender = getString(R.string.form_register_gender_man_value);

                signUp(
                        etName.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString(),
                        etConfirmPassword.getText().toString(),
                        gender
                );

            }
        });

        setUpPresenterRegister();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public void showNameError() {
        tilName.setError(getString(R.string.error_field_required));
    }

    @Override
    public void showEmailError() {
        tilEmail.setError(getString(R.string.error_invalid_email));
    }

    @Override
    public void showPasswordError() {
        tilPassword.setError(getString(R.string.error_invalid_password));
    }

    @Override
    public void showPasswordConfirmationError() {
        tilConfirmPassword.setError(getString(R.string.error_password_confirmation));
    }

    @Override
    public void showRegisterError(String error, Context context) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void signUp(String name, String email, String password, String password_confirmation, String gender) {
        presenter.validateRegister(name, email, password, password_confirmation, gender);
    }

    @Override
    public void navigateToMain(int id) {
        Toast.makeText(this, "New user add. Id: " + id, Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public void setUpPresenterRegister() {
        presenter = new AddUserPresenter(this, new AddUserInteractor(), getApplicationContext());
    }
}
