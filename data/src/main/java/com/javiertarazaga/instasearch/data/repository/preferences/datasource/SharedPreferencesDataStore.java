/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
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
package com.javiertarazaga.instasearch.data.repository.preferences.datasource;

import android.content.SharedPreferences;
import io.reactivex.Observable;

/**
 * {@link PreferencesDataStore} implementation based on the shared preferences data store.
 */
class SharedPreferencesDataStore implements PreferencesDataStore {

  private final SharedPreferences sharedPreferences;

  /**
   * Construct a {@link PreferencesDataStore} based shared preferences data store.
   *
   * @param sharedPreferences A {@link SharedPreferences} to cache data retrieved.
   */
  SharedPreferencesDataStore(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  @Override public Observable<Integer> saveDistance(int distance) {
    // TODO - Revise this if we can make it more functional and handle the case in which fails?
    this.sharedPreferences.edit().putInt("distance", distance).apply();

    return Observable.just(distance);
  }

  @Override public Observable<Integer> getDistance() {
    return Observable.just(this.sharedPreferences.getInt("distance", 500));
  }
}
