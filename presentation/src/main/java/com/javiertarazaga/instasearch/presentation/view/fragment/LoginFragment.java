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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.javiertarazaga.instasearch.presentation.R;
import com.javiertarazaga.instasearch.presentation.internal.di.components.UserComponent;
import com.javiertarazaga.instasearch.presentation.presenter.LoginPresenter;
import com.javiertarazaga.instasearch.presentation.view.LoginView;
import javax.inject.Inject;

/**
 * Fragment that shows a list of Users.
 */
public class LoginFragment extends BaseFragment implements LoginView {

  /**
   * Interface for listening login events.
   */
  public interface LoginFragmentListener {
    void loginSuccessful();
  }

  @Inject LoginPresenter loginPresenter;

  @Bind(R.id.wv_login) WebView wv_login;

  private static final String TAG = "LoginFragment";

  private static final String CLIENT_ID = "974bc375f6ac4f0b883484e72d786e24";
  private static final String REDIRECT_URI = "http://instasearchapp.com/auth/instagram/callback";
  private static final String FAILURE_URL = "http://instasearchapp.com/auth/failure";
  private static final String AUTH_URI = "https://instagram.com/oauth/authorize/?client_id="
      + CLIENT_ID
      + "&redirect_uri="
      + REDIRECT_URI
      + "&response_type=token&scope=public_content";

  private LoginFragmentListener loginFragmentListener;

  public LoginFragment() {
    setRetainInstance(true);
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);

    if (context instanceof LoginFragmentListener) {
      this.loginFragmentListener = (LoginFragmentListener) context;
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(UserComponent.class).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
    ButterKnife.bind(this, fragmentView);
    initWebView();
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.loginPresenter.setView(this);
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

  @Override public void loadUrl(String url) {
    wv_login.loadUrl(url);
  }

  @Override public void loginSuccessful() {
    if (this.loginFragmentListener != null) {
      LoginFragment.this.loginFragmentListener.loginSuccessful();
    }
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

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void initWebView() {
    wv_login.setWebViewClient(new WebViewClient() {

      @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return LoginFragment.this.loginPresenter.shouldOverrideUrlLoading(url)
            && super.shouldOverrideUrlLoading(view, url);
      }
    });
  }
}
