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
package com.javiertarazaga.instasearch.data.repository.user;

import com.javiertarazaga.instasearch.data.entity.UserEntity;
import com.javiertarazaga.instasearch.data.entity.mapper.UserEntityDataMapper;
import com.javiertarazaga.instasearch.data.repository.user.datasource.UserDataStore;
import com.javiertarazaga.instasearch.data.repository.user.datasource.UserDataStoreFactory;
import com.javiertarazaga.instasearch.domain.User;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserDataRepositoryTest {

  private UserDataRepository userDataRepository;

  @Mock private UserDataStoreFactory mockUserDataStoreFactory;
  @Mock private UserEntityDataMapper mockUserEntityDataMapper;
  @Mock private UserDataStore mockUserDataStore;
  @Mock private UserEntity mockUserEntity;
  @Mock private User mockUser;

  @Before
  public void setUp() {
    userDataRepository = new UserDataRepository(mockUserDataStoreFactory, mockUserEntityDataMapper);
    given(mockUserDataStoreFactory.createSharedDataStore()).willReturn(mockUserDataStore);
  }

  @Test
  public void testLogoutHappyCase() {
    given(mockUserDataStore.logout()).willReturn(Observable.just(true));
    userDataRepository.logout();

    verify(mockUserDataStoreFactory).createSharedDataStore();
    verify(mockUserDataStore).logout();
  }
}
