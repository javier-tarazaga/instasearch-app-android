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
import com.javiertarazaga.instasearch.domain.exception.user.UserNeedsAuthenticationException;
import com.javiertarazaga.instasearch.domain.interactor.DefaultObserver;
import com.javiertarazaga.instasearch.domain.interactor.GetUser;
import com.javiertarazaga.instasearch.presentation.exception.ErrorMessageFactory;
import com.javiertarazaga.instasearch.presentation.internal.di.PerActivity;
import com.javiertarazaga.instasearch.presentation.view.SplashScreenView;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class SplashScreenPresenter implements Presenter {

  private SplashScreenView splashScreenView;

  private final GetUser getUser;

  @Inject
  public SplashScreenPresenter(GetUser getUser) {
    this.getUser = getUser;
  }

  public void setView(@NonNull SplashScreenView view) {
    this.splashScreenView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getUser.dispose();
    this.splashScreenView = null;
  }

  /**
   * Initializes the presenter.
   */
  public void initialize() {
    this.initApp();
  }

  private void showViewLoading() {
    this.splashScreenView.showLoading();
  }

  private void hideViewLoading() {
    this.splashScreenView.hideLoading();
  }

  private void showViewRetry() {
    this.splashScreenView.showRetry();
  }

  private void hideViewRetry() {
    this.splashScreenView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.splashScreenView.context(),
                                                     errorBundle.getException());
    this.splashScreenView.showError(errorMessage);
  }

  private void initApp() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUser.execute(new IsUserAuthenticatedObserver(), null);
  }

  private void goToMainView() {
    this.splashScreenView.goToMainView();
  }

  private void goToLoginView() {
    this.splashScreenView.goToLoginView();
  }

  private final class IsUserAuthenticatedObserver extends DefaultObserver<User> {

    @Override public void onComplete() {
      SplashScreenPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      SplashScreenPresenter.this.hideViewLoading();

      if (e instanceof UserNeedsAuthenticationException) {
        SplashScreenPresenter.this.goToLoginView();
      } else {
        SplashScreenPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
        SplashScreenPresenter.this.showViewRetry();
      }
    }

    @Override public void onNext(User user) {
      SplashScreenPresenter.this.goToMainView();
    }
  }
}
