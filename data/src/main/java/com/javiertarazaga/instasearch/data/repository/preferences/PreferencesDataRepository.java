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
package com.javiertarazaga.instasearch.data.repository.preferences;

import com.javiertarazaga.instasearch.data.repository.preferences.datasource.PreferencesDataStore;
import com.javiertarazaga.instasearch.data.repository.preferences.datasource.PreferencesDataStoreFactory;
import com.javiertarazaga.instasearch.domain.repository.PreferencesRepository;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link PreferencesRepository} for retrieving preferences data.
 */
@Singleton public class PreferencesDataRepository implements PreferencesRepository {

  private final PreferencesDataStoreFactory preferencesDataStoreFactory;

  /**
   * Constructs a {@link PreferencesRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   */
  @Inject PreferencesDataRepository(PreferencesDataStoreFactory dataStoreFactory) {
    this.preferencesDataStoreFactory = dataStoreFactory;
  }

  @Override public Observable<Integer> saveDistance(int distance) {
    final PreferencesDataStore preferencesDataStore = this.preferencesDataStoreFactory.create();
    return preferencesDataStore.saveDistance(distance);
  }

  @Override public Observable<Integer> getDistance() {
    final PreferencesDataStore preferencesDataStore = this.preferencesDataStoreFactory.create();
    return preferencesDataStore.getDistance();
  }
}
