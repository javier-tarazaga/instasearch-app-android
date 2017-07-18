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

import android.content.SharedPreferences;
import com.javiertarazaga.instasearch.data.ApplicationTestCase;
import com.javiertarazaga.instasearch.data.cache.UserCache;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserDataStoreFactoryTest extends ApplicationTestCase {

  private UserDataStoreFactory userDataStoreFactory;

  @Mock private UserCache mockUserCache;
  @Mock private SharedPreferences mockSharedPreferences;

  @Before public void setUp() {
    userDataStoreFactory = new UserDataStoreFactory(RuntimeEnvironment.application, mockUserCache,
        mockSharedPreferences);
  }

  @Test public void testCreateMemoryataStore() {
    given(mockUserCache.isCached()).willReturn(true);
    given(mockUserCache.isExpired()).willReturn(false);

    UserDataStore userDataStore = userDataStoreFactory.create();

    assertThat(userDataStore, is(notNullValue()));
    assertThat(userDataStore, is(instanceOf(UserMemoryDataStore.class)));

    verify(mockUserCache).isCached();
    verify(mockUserCache).isExpired();
  }

  @Test public void testCreateCloudDataStore() {
    given(mockUserCache.isExpired()).willReturn(true);
    given(mockUserCache.isCached()).willReturn(false);

    UserDataStore userDataStore = userDataStoreFactory.create();

    assertThat(userDataStore, is(notNullValue()));
    assertThat(userDataStore, is(instanceOf(UserCloudDataStore.class)));

    verify(mockUserCache).isExpired();
  }
}
