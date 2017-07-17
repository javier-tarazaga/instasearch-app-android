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
import com.javiertarazaga.instasearch.domain.interactor.DefaultObserver;
import com.javiertarazaga.instasearch.domain.interactor.GetDistance;
import com.javiertarazaga.instasearch.domain.interactor.SaveDistance;
import com.javiertarazaga.instasearch.presentation.internal.di.PerActivity;
import com.javiertarazaga.instasearch.presentation.view.PreferencesView;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class PreferencesPresenter implements Presenter {

  private PreferencesView preferencesView;

  private final GetDistance getDistance;
  private final SaveDistance saveDistance;

  @Inject
  public PreferencesPresenter(GetDistance getDistance,
      SaveDistance saveDistance) {
    this.getDistance = getDistance;
    this.saveDistance = saveDistance;
  }

  public void setView(@NonNull PreferencesView view) {
    this.preferencesView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getDistance.dispose();
    this.saveDistance.dispose();
    this.preferencesView = null;
  }

  /**
   * Initializes the presenter. Empty for the moment
   */
  public void initialize() {
    this.getDistance();
  }

  public void saveDistance(int distance) {
    this.saveDistance.execute(new DistanceObserver(), SaveDistance.Params.forDistance(distance));
  }

  private void getDistance() {
    this.getDistance.execute(new DistanceObserver(), null);
  }

  private void updateDistance(int distance) {
    this.preferencesView.updateDistance(distance);
  }

  private final class DistanceObserver extends DefaultObserver<Integer> {

    @Override public void onNext(Integer distance) {
      PreferencesPresenter.this.updateDistance(distance);
    }
  }
}
