/**
 * Copyright (C) 2017 Javier Tarazaga Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.javiertarazaga.instasearch.presentation.presenter;

import android.support.annotation.NonNull;
import com.javiertarazaga.instasearch.domain.User;
import com.javiertarazaga.instasearch.domain.exception.DefaultErrorBundle;
import com.javiertarazaga.instasearch.domain.exception.ErrorBundle;
import com.javiertarazaga.instasearch.domain.interactor.DefaultObserver;
import com.javiertarazaga.instasearch.presentation.exception.ErrorMessageFactory;
import com.javiertarazaga.instasearch.presentation.internal.di.PerActivity;
import com.javiertarazaga.instasearch.presentation.mapper.UserModelDataMapper;
import com.javiertarazaga.instasearch.presentation.view.LoginView;
import java.util.List;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class LoginPresenter implements Presenter {

  private static final String CLIENT_ID       = "974bc375f6ac4f0b883484e72d786e24";
  private static final String REDIRECT_URI    = "http://instasearchapp.com/auth/instagram/callback";
  private static final String FAILURE_URL     = "http://instasearchapp.com/auth/failure";
  private static final String AUTH_URI        = "https://instagram.com/oauth/authorize/?client_id="
      + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&response_type=token&scope=public_content";

  private LoginView loginView;

  private final UserModelDataMapper userModelDataMapper;

  @Inject
  public LoginPresenter(UserModelDataMapper userModelDataMapper) {
    this.userModelDataMapper = userModelDataMapper;
  }

  public void setView(@NonNull LoginView view) {
    this.loginView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.loginView = null;
  }

  /**
   * Initializes the presenter. Empty for the moment
   */
  public void initialize() {}

  public void login(String username, String password) {

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
    String errorMessage = ErrorMessageFactory.create(this.loginView.context(),
                                                     errorBundle.getException());
    this.loginView.showError(errorMessage);
  }

  private final class UserListObserver extends DefaultObserver<List<User>> {

    @Override public void onComplete() {
      LoginPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      LoginPresenter.this.hideViewLoading();
      LoginPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      LoginPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<User> users) {
      // LoginPresenter.this.showUsersCollectionInView(users);
    }
  }
}
