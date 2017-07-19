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

import com.javiertarazaga.instasearch.data.cache.UserCache;
import com.javiertarazaga.instasearch.data.entity.UserEntity;
import io.reactivex.Observable;

/**
 * {@link UserDataStore} implementations that uses a memory implementation of {@link UserCache}
 */
class UserMemoryDataStore implements UserDataStore {

  private final UserCache userCache;

  /**
   * Construct a {@link UserDataStore} based memory data store.
   *
   * @param userCache A {@link UserCache} to cache data retrieved from the api.
   */
  UserMemoryDataStore(UserCache userCache) {
    this.userCache = userCache;
  }

  @Override public Observable<UserEntity> user() {
    return userCache.get();
  }

  @Override public Observable<Boolean> logout() {
    throw new RuntimeException("Not implemented!");
  }
}
