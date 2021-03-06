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
package com.javiertarazaga.instasearch.data.repository.user;

import com.javiertarazaga.instasearch.data.entity.mapper.UserEntityDataMapper;
import com.javiertarazaga.instasearch.data.repository.user.datasource.UserDataStore;
import com.javiertarazaga.instasearch.data.repository.user.datasource.UserDataStoreFactory;
import com.javiertarazaga.instasearch.domain.User;
import com.javiertarazaga.instasearch.domain.repository.UserRepository;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements UserRepository {

  private final UserDataStoreFactory userDataStoreFactory;
  private final UserEntityDataMapper userEntityDataMapper;

  /**
   * Constructs a {@link UserRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param userEntityDataMapper {@link UserEntityDataMapper}.
   */
  @Inject public UserDataRepository(UserDataStoreFactory dataStoreFactory,
      UserEntityDataMapper userEntityDataMapper) {
    this.userDataStoreFactory = dataStoreFactory;
    this.userEntityDataMapper = userEntityDataMapper;
  }

  @Override public Observable<User> user() {
    final UserDataStore userDataStore = this.userDataStoreFactory.create();
    return userDataStore.user().map(this.userEntityDataMapper::transform);
  }

  @Override public Observable<Boolean> logout() {
    final UserDataStore userDataStore = this.userDataStoreFactory.createSharedDataStore();
    return userDataStore.logout();
  }
}
