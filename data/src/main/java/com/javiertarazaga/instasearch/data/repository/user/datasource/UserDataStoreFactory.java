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
package com.javiertarazaga.instasearch.data.repository.user.datasource;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.javiertarazaga.instasearch.data.cache.UserCache;
import com.javiertarazaga.instasearch.data.entity.mapper.json.MediaEntityJsonMapper;
import com.javiertarazaga.instasearch.data.entity.mapper.json.UserEntityJsonMapper;
import com.javiertarazaga.instasearch.data.net.RestApi;
import com.javiertarazaga.instasearch.data.net.RestApiImpl;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserDataStore}.
 */
@Singleton public class UserDataStoreFactory {

  private final Context context;
  private final UserCache userCache;
  private final SharedPreferences sharedPreferences;

  @Inject UserDataStoreFactory(@NonNull Context context, @NonNull UserCache userCache,
      @NonNull SharedPreferences sharedPreferences) {
    this.context = context.getApplicationContext();
    this.userCache = userCache;
    this.sharedPreferences = sharedPreferences;
  }

  /**
   * Create {@link UserDataStore}
   */
  public UserDataStore create() {
    UserDataStore userDataStore;

    if (!this.userCache.isExpired() && this.userCache.isCached()) {
      userDataStore = new UserMemoryDataStore(this.userCache);
    } else {
      userDataStore = createCloudDataStore();
    }

    return userDataStore;
  }

  /**
   * Create {@link UserDataStore} to retrieve data from the Cloud.
   */
  public UserDataStore createCloudDataStore() {
    final UserEntityJsonMapper userEntityJsonMapper = new UserEntityJsonMapper();
    final MediaEntityJsonMapper mediaEntityJsonMapper = new MediaEntityJsonMapper();
    final RestApi restApi =
        new RestApiImpl(this.context, userEntityJsonMapper, mediaEntityJsonMapper,
            this.sharedPreferences);

    return new UserCloudDataStore(restApi, this.userCache);
  }

  /**
   * Create {@link UserDataStore} to handle data from the SharedPrefs.
   */
  public UserDataStore createSharedDataStore() {
    return new UserSharedDataStore(this.sharedPreferences, this.userCache);
  }
}
