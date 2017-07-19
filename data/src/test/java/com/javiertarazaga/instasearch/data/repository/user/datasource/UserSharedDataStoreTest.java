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

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.javiertarazaga.instasearch.data.cache.UserCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class) public class UserSharedDataStoreTest {

  private UserSharedDataStore userSharedDataStore;

  @Mock private UserCache mockUserCache;
  @Mock private SharedPreferences mockSharedPreferences;
  @Mock private SharedPreferences.Editor mockSharedPreferencesEditor;

  @Before public void setUp() {
    userSharedDataStore = new UserSharedDataStore(mockSharedPreferences, mockUserCache);
  }

  @SuppressLint("CommitPrefEdits") @Test public void testRemoveTokenFromSharedPrefs() {
    given(this.mockSharedPreferences.edit()).willReturn(this.mockSharedPreferencesEditor);
    given(this.mockSharedPreferencesEditor.remove(anyString())).willReturn(
        this.mockSharedPreferencesEditor);

    this.userSharedDataStore.logout();
    verify(mockSharedPreferencesEditor).remove("access_token");
    verify(mockSharedPreferencesEditor).commit();
  }
}
