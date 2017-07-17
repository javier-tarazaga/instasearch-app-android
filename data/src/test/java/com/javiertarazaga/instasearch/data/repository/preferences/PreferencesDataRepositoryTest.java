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
package com.javiertarazaga.instasearch.data.repository.preferences;

import com.javiertarazaga.instasearch.data.repository.preferences.datasource.PreferencesDataStore;
import com.javiertarazaga.instasearch.data.repository.preferences.datasource.PreferencesDataStoreFactory;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PreferencesDataRepositoryTest {

  private static final int FAKE_DISTANCE = 1000;

  private PreferencesDataRepository preferencesDataRepository;

  @Mock private PreferencesDataStoreFactory mockPreferencesDataStoreFactory;
  @Mock private PreferencesDataStore mockPreferencesDataStore;

  @Before
  public void setUp() {
    preferencesDataRepository = new PreferencesDataRepository(mockPreferencesDataStoreFactory);
    given(mockPreferencesDataStoreFactory.create()).willReturn(mockPreferencesDataStore);
    given(mockPreferencesDataStoreFactory.createSharedPreferencesDataStore()).willReturn(
        mockPreferencesDataStore);
  }

  @Test
  public void testGetMaxDistanceHappyCase() {
    given(mockPreferencesDataStore.getDistance()).willReturn(Observable.just(FAKE_DISTANCE));

    preferencesDataRepository.getDistance();

    verify(mockPreferencesDataStoreFactory).create();
    verify(mockPreferencesDataStore).getDistance();
  }

  @Test
  public void testSaveMaxDistanceHappyCase() {
    given(mockPreferencesDataStore.saveDistance(FAKE_DISTANCE)).willReturn(Observable.just(FAKE_DISTANCE));
    preferencesDataRepository.saveDistance(FAKE_DISTANCE);

    verify(mockPreferencesDataStoreFactory).create();
    verify(mockPreferencesDataStore).saveDistance(FAKE_DISTANCE);
  }
}
