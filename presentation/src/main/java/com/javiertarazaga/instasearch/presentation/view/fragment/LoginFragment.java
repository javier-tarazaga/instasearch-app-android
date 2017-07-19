/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.javiertarazaga.instasearch.presentation.view.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.wv_login) WebView wv_login;

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

    if (savedInstanceState == null) {
      this.initialize();
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

  @Override public void onDetach() {
    super.onDetach();
    this.loginFragmentListener = null;
  }

  @Override public void loadUrl(String url) {
    wv_login.loadUrl(url);
  }

  @Override public void loginSuccessful() {
    // Make sure we don't get any info cached here, otherwise the app does not manage to logout
    // correctly. We will manage the access_token ourselves.
    this.clearWebView();

    if (this.loginFragmentListener != null) {
      LoginFragment.this.loginFragmentListener.loginSuccessful();
    }
  }

  @Override public void showLoading() {
    rl_progress.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    rl_progress.setVisibility(View.GONE);
  }

  @Override public void showRetry() {
    this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void initWebView() {
    wv_login.getSettings().setAppCacheEnabled(false);
    wv_login.setWebViewClient(new WebViewClient() {

      @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return LoginFragment.this.loginPresenter.shouldOverrideUrlLoading(url)
            && super.shouldOverrideUrlLoading(view, url);
      }
    });
  }

  private void initialize() {
    this.loginPresenter.initialize();
  }

  private void clearWebView() {

    // For some weird reason seems url loading gets called twice after login. This will fix this
    // issue for now.
    wv_login.setWebViewClient(new WebViewClient() {
      @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
      }
    });
    wv_login.clearCache(true);
    wv_login.clearFormData();
    wv_login.clearHistory();

    this.clearCookies();
  }

  private void clearCookies() {
    // This one seems to do the trick ¯\_(ツ)_/¯ to not hold the login state in the wv
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      CookieManager.getInstance().removeAllCookies(null);
    } else {
      CookieSyncManager.createInstance(context());
      CookieManager cookieManager = CookieManager.getInstance();
      if (cookieManager != null) {
        cookieManager.removeAllCookie();
      }
      CookieSyncManager.getInstance().sync();
    }
  }

  @OnClick(R.id.bt_retry) void onButtonRetryClick() {
    // This would only be in the case that we get the token and there has been an issue fetching
    // the user
    this.loginPresenter.loadUserData();
  }
}
