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
package com.javiertarazaga.instasearch.domain.interactor;

import com.javiertarazaga.instasearch.domain.exception.preferences.PreferenceException;
import com.javiertarazaga.instasearch.domain.executor.PostExecutionThread;
import com.javiertarazaga.instasearch.domain.executor.ThreadExecutor;
import com.javiertarazaga.instasearch.domain.repository.PreferencesRepository;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * saving the distance to be used while searching for medias.
 */
public class SaveDistance extends UseCase<Integer, Void> {

  private final PreferencesRepository preferencesRepository;
  private int distance;

  @Inject SaveDistance(PreferencesRepository preferencesRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.preferencesRepository = preferencesRepository;
  }

  /**
   * Initializes the interactor with the distance to use be used while searching medias.
   *
   * @param distance - the distance to take into account when performing a media search
   */
  public SaveDistance init(int distance) {
    this.distance = distance;

    return this;
  }

  /**
   * Builds the {@link UseCase} observable @return an {@link Observable} that will emit the maximum
   * distance to be used while searching for medias nearby.
   * <li>{@link PreferenceException} if an unknown error occurs</li>
   * </ul>
   */
  @Override Observable<Integer> buildUseCaseObservable(Void unused) {
    return this.preferencesRepository.saveDistance(this.distance);
  }
}
