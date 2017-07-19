/**
 * Copyright (C) 2017 Javier Tarazaga Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.javiertarazaga.instasearch.presentation.presenter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.javiertarazaga.instasearch.domain.User;
import com.javiertarazaga.instasearch.domain.exception.DefaultErrorBundle;
import com.javiertarazaga.instasearch.domain.exception.ErrorBundle;
import com.javiertarazaga.instasearch.domain.exception.user.InstagramAuthErrorException;
import com.javiertarazaga.instasearch.domain.interactor.DefaultObserver;
import com.javiertarazaga.instasearch.domain.interactor.GetUser;
import com.javiertarazaga.instasearch.presentation.exception.ErrorMessageFactory;
import com.javiertarazaga.instasearch.presentation.internal.di.PerActivity;
import com.javiertarazaga.instasearch.presentation.view.LoginView;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity public class LoginPresenter implements Presenter {

  private static final String CLIENT_ID = "974bc375f6ac4f0b883484e72d786e24";
  private static final String REDIRECT_URI = "http://instasearchapp.com/auth/instagram/callback";
  private static final String FAILURE_URL = "http://instasearchapp.com/auth/failure";
  private static final String AUTH_URI = "https://instagram.com/oauth/authorize/?client_id="
      + CLIENT_ID
      + "&redirect_uri="
      + REDIRECT_URI
      + "&response_type=token&scope=public_content";

  private LoginView loginView;

  private final GetUser getUser;
  private final SharedPreferences sharedPreferences;

  @Inject public LoginPresenter(GetUser getUser, SharedPreferences sharedPreferences) {
    this.getUser = getUser;
    this.sharedPreferences = sharedPreferences;
  }

  public void setView(@NonNull LoginView view) {
    this.loginView = view;
  }

  @Override public void resume() {
  }

  @Override public void pause() {
  }

  @Override public void destroy() {
    this.getUser.dispose();
    this.loginView = null;
  }

  /**
   * Initializes the presenter. Empty for the moment
   */
  public void initialize() {
    this.loginView.loadUrl(AUTH_URI);
  }

  public boolean shouldOverrideUrlLoading(String url) {
    if (url.startsWith(REDIRECT_URI)) {
      if (url.contains("access_token")) {
        String accessToken = this.extractAccessToken(url);

        // TODO - change for StringPreference!
        this.sharedPreferences.edit().putString("access_token", accessToken).apply();

        this.loadUserData();
        // Save the token and notify the view
        this.loginView.loginSuccessful();
      } else if (url.contains("error_reason")) {
        String error = url.contains("user_denied") ? "User denied access" : "Authentication failed";

        this.showErrorMessage(new DefaultErrorBundle(new InstagramAuthErrorException(error)));
      }

      return true;
    } else if (url.startsWith(FAILURE_URL)) {

      this.showErrorMessage(new DefaultErrorBundle(new InstagramAuthErrorException("Auth Failed")));
      return true;
    }

    return false;
  }

  public void loadUserData() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUser.execute(new UserObserver(), null);
  }

  private String extractAccessToken(String url) {
    return url.split("#access_token=")[1];
  }

  private void showViewLoading() {
    this.loginView.showLoading();
  }

  private void hideViewLoading() {
    this.loginView.hideLoading();
  }

  private void showViewRetry() {
    this.loginView.showRetry();
  }

  private void hideViewRetry() {
    this.loginView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage =
        ErrorMessageFactory.create(this.loginView.context(), errorBundle.getException());
    this.loginView.showError(errorMessage);
  }

  private void loginSuccessful() {
    this.loginView.loginSuccessful();
  }

  private final class UserObserver extends DefaultObserver<User> {

    @Override public void onComplete() {
      LoginPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      LoginPresenter.this.hideViewLoading();
      LoginPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      LoginPresenter.this.showViewRetry();
    }

    @Override public void onNext(User user) {
      LoginPresenter.this.loginSuccessful();
    }
  }
}
