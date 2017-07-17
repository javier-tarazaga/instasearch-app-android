/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.javiertarazaga.instasearch.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.javiertarazaga.instasearch.presentation.R;
import com.javiertarazaga.instasearch.presentation.internal.di.components.MainComponent;
import com.javiertarazaga.instasearch.presentation.presenter.LoginPresenter;
import com.javiertarazaga.instasearch.presentation.view.LoginView;
import javax.inject.Inject;

/**
 * Fragment that shows a list of Users.
 */
public class LoginFragment extends BaseFragment implements LoginView {

  @Inject LoginPresenter loginPresenter;

  @Bind(R.id.et_username) EditText et_username;
  @Bind(R.id.et_password) EditText et_password;
  @Bind(R.id.bt_login) Button bt_login;

  public LoginFragment() {
    setRetainInstance(true);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(MainComponent.class).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
    ButterKnife.bind(this, fragmentView);
    initEditTexts();
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.loginPresenter.setView(this);
    if (savedInstanceState == null) {
      // TODO
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.loginPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.loginPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.loginPresenter.destroy();
  }

  @Override public void showLoading() {

  }

  @Override public void hideLoading() {

  }

  @Override public void showRetry() {

  }

  @Override public void hideRetry() {

  }

  @Override public void showError(String message) {

  }

  @Override public void showInvalidUserName(String errorMessage) {

  }

  @Override public void showInvalidPassword(String errorMessage) {

  }

  @Override public void loginSuccessful() {

  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void initEditTexts() {
    //et_username.addTextChangedListener(this.presenter.getTextWatcher());
    //et_password.addTextChangedListener(this.presenter.getTextWatcher());

    ////EditText hint text disappears by touching. In other word by getting focused hint text disappears
    //et_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    //  @Override
    //  public void onFocusChange(View v, boolean hasFocus) {
    //    LoginFragment.this.presenter.onUserNameEditTextFocusChanged(
    //        ((EditText) v).getText().toString(), hasFocus);
    //  }
    //});
    //et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    //  @Override
    //  public void onFocusChange(View v, boolean hasFocus) {
    //    LoginFragment.this.presenter.onPasswordEditTextFocusChanged(
    //        ((EditText) v).getText().toString(), hasFocus);
    //  }
    //});
  }


  @OnClick(R.id.bt_login) void onButtonLoginClick() {

    // Reset errors
    et_username.setError(null);
    et_password.setError(null);

    // Check if the username or password are not empty and check if the email it is a valid email.
    String username = et_username.getText().toString();
    String password = et_password.getText().toString();

    this.loginPresenter.login(username, password);
  }
}
