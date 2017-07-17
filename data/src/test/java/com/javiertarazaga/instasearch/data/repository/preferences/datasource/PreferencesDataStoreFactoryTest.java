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
package com.javiertarazaga.instasearch.data.repository.preferences.datasource;

import android.content.SharedPreferences;
import com.javiertarazaga.instasearch.data.ApplicationTestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PreferencesDataStoreFactoryTest extends ApplicationTestCase {

  private PreferencesDataStoreFactory preferencesDataStoreFactory;

  @Mock private SharedPreferences mockSharedPreferences;

  @Before public void setUp() {
    preferencesDataStoreFactory =
        new PreferencesDataStoreFactory(RuntimeEnvironment.application, mockSharedPreferences);
  }

  @Test public void testCreateSharedPreferencesDataStore() {
    PreferencesDataStore preferencesDataStore = preferencesDataStoreFactory.create();

    assertThat(preferencesDataStore, is(notNullValue()));
    assertThat(preferencesDataStore, is(instanceOf(SharedPreferencesDataStore.class)));
  }
}
