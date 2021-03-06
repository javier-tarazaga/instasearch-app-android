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

import android.support.annotation.NonNull;
import com.javiertarazaga.instasearch.domain.exception.DefaultErrorBundle;
import com.javiertarazaga.instasearch.domain.exception.ErrorBundle;
import com.javiertarazaga.instasearch.domain.exception.user.LogoutNotPossibleException;
import com.javiertarazaga.instasearch.domain.interactor.DefaultObserver;
import com.javiertarazaga.instasearch.domain.interactor.GetMaxDistance;
import com.javiertarazaga.instasearch.domain.interactor.Logout;
import com.javiertarazaga.instasearch.domain.interactor.SaveMaxDistance;
import com.javiertarazaga.instasearch.presentation.exception.ErrorMessageFactory;
import com.javiertarazaga.instasearch.presentation.view.PreferencesView;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
public class PreferencesPresenter implements Presenter {

  private PreferencesView preferencesView;

  private final GetMaxDistance getMaxDistance;
  private final SaveMaxDistance saveMaxDistance;
  private final Logout logout;

  @Inject public PreferencesPresenter(GetMaxDistance getMaxDistance, Logout logout,
      SaveMaxDistance saveMaxDistance) {
    this.getMaxDistance = getMaxDistance;
    this.logout = logout;
    this.saveMaxDistance = saveMaxDistance;
  }

  public void setView(@NonNull PreferencesView view) {
    this.preferencesView = view;
  }

  @Override public void resume() {
  }

  @Override public void pause() {
  }

  @Override public void destroy() {
    this.getMaxDistance.dispose();
    this.saveMaxDistance.dispose();
    this.logout.dispose();
    this.preferencesView = null;
  }

  /**
   * Initializes the presenter. Empty for the moment
   */
  public void initialize() {
    this.getDistance();
  }

  public void saveDistance(int distance) {
    this.saveMaxDistance.execute(new DistanceObserver(),
        SaveMaxDistance.Params.forDistance(distance));
  }

  private void getDistance() {
    this.getMaxDistance.execute(new DistanceObserver(), null);
  }

  public void setDistance(int distance) {
    if (distance >= 1000) {
      float distanceKm = (float) distance / 1000;
      this.preferencesView.setDistanceInKm(distanceKm);
    } else {
      this.preferencesView.setDistanceInM(distance);
    }
  }

  public void logoutClicked() {
    this.logout.execute(new LogoutObserver(), null);
  }

  private void updateDistance(int distance) {
    this.preferencesView.updateDistance(distance);
  }

  private void logoutSuccessful() {
    this.preferencesView.logoutSuccessful();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage =
        ErrorMessageFactory.create(this.preferencesView.context(), errorBundle.getException());
    this.preferencesView.showError(errorMessage);
  }

  private final class DistanceObserver extends DefaultObserver<Integer> {

    @Override public void onNext(Integer distance) {
      PreferencesPresenter.this.updateDistance(distance);
    }
  }

  private final class LogoutObserver extends DefaultObserver<Boolean> {

    @Override public void onError(Throwable exception) {
      super.onError(exception);

      PreferencesPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) exception));
    }

    @Override public void onNext(Boolean loggedOut) {
      if (loggedOut) {
        PreferencesPresenter.this.logoutSuccessful();
      } else {
        PreferencesPresenter.this.showErrorMessage(
            new DefaultErrorBundle(new LogoutNotPossibleException()));
      }
    }
  }
}
