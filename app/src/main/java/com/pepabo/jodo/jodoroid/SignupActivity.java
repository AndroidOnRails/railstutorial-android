package com.pepabo.jodo.jodoroid;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity {

    @Bind(R.id.email) EditText mEmailView;
    @Bind(R.id.name) private EditText mNameView;
    @Bind(R.id.password) EditText mPasswordView;

    EmailValidator mEmailValidator;
    NameValidator mNameValidator;
    PasswordValidator mPasswordValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mEmailValidator = new EmailValidator(getApplicationContext());
        mNameValidator = new NameValidator(getApplicationContext());
        mPasswordValidator = new PasswordValidator(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.action_signup)
    void attemptSignup() {
        // Reset errors.
        mEmailView.setError(null);
        mNameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String name = mNameView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        mPasswordValidator.validate(password);
        if (mPasswordValidator.hasError()) {
            mPasswordView.setError(mPasswordValidator.getErrorMessage());
            focusView = mPasswordView;
            cancel = true;
        }

        mNameValidator.validate(name);
        if (mNameValidator.hasError()) {
            mNameView.setError(mNameValidator.getErrorMessage());
            focusView = mNameView;
            cancel = true;
        }

        mEmailValidator.validate(email);
        if (mEmailValidator.hasError()) {
            mEmailView.setError(mEmailValidator.getErrorMessage());
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
