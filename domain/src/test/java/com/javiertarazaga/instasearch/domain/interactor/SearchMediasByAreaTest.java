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
package com.javiertarazaga.instasearch.domain.interactor;

import com.javiertarazaga.instasearch.domain.executor.PostExecutionThread;
import com.javiertarazaga.instasearch.domain.executor.ThreadExecutor;
import com.javiertarazaga.instasearch.domain.repository.MediaRepository;
import com.javiertarazaga.instasearch.domain.repository.PreferencesRepository;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class) public class SearchMediasByAreaTest {

  private static final int FAKE_MAX_DISTANCE = 1000;

  private SearchMediasByArea searchMediasByArea;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;
  @Mock private PreferencesRepository mockPreferencesRepository;
  @Mock private MediaRepository mockMediaRepository;

  @Before public void setUp() {
    this.searchMediasByArea =
        new SearchMediasByArea(this.mockPreferencesRepository, this.mockMediaRepository, this.mockThreadExecutor,
            this.mockPostExecutionThread);
  }

  @Test public void testGetUserListUseCaseObservableHappyCase() {
    given(this.mockPreferencesRepository.getDistance()).willReturn(Observable.just(FAKE_MAX_DISTANCE));

    this.searchMediasByArea.buildUseCaseObservable(null);

    verify(this.mockPreferencesRepository).getDistance();

    // TODO - Fix this verification, as it is called later in the road, small workaround is needed
    // verify(this.mockMediaRepository).searchByArea(LAT_HOME, LNG_HOME, FAKE_MAX_DISTANCE);
    verifyNoMoreInteractions(this.mockMediaRepository);
    verifyNoMoreInteractions(this.mockPreferencesRepository);
    verifyZeroInteractions(this.mockThreadExecutor);
    verifyZeroInteractions(this.mockPostExecutionThread);
  }
}
