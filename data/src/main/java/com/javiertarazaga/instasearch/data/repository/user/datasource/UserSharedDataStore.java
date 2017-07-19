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
package com.javiertarazaga.instasearch.data.repository.user.datasource;

import android.content.SharedPreferences;
import com.javiertarazaga.instasearch.data.entity.UserEntity;
import io.reactivex.Observable;

/**
 * {@link UserDataStore} based on connections to the shared preferences
 */
class UserSharedDataStore implements UserDataStore {

  private final SharedPreferences sharedPreferences;

  /**
   * Construct a {@link UserDataStore} based shared preferences
   */
  UserSharedDataStore(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  @Override public Observable<UserEntity> user() {
    throw new RuntimeException("Not implemented!");
  }

  @Override public Observable<Boolean> logout() {
    return Observable.just(this.sharedPreferences.edit().remove("access_token").commit());
  }
}
