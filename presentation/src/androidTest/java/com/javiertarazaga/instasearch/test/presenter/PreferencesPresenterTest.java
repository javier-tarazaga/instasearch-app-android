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
package com.javiertarazaga.instasearch.test.presenter;

import android.content.Context;
import com.javiertarazaga.instasearch.domain.interactor.GetMaxDistance;
import com.javiertarazaga.instasearch.domain.interactor.SaveMaxDistance;
import com.javiertarazaga.instasearch.presentation.presenter.PreferencesPresenter;
import com.javiertarazaga.instasearch.presentation.view.PreferencesView;
import io.reactivex.observers.DisposableObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PreferencesPresenterTest {

  private static final int DISTANCE = 1000;

  private PreferencesPresenter preferencesPresenter;

  @Mock private Context mockContext;
  @Mock private PreferencesView mockPreferencesView;
  @Mock private GetMaxDistance mockGetMaxDistance;
  @Mock private SaveMaxDistance mockSaveMaxDistance;

  @Before
  public void setUp() {
    preferencesPresenter = new PreferencesPresenter(mockGetMaxDistance, mockSaveMaxDistance);
    preferencesPresenter.setView(mockPreferencesView);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testPreferencesPresenterInitialize() {
    preferencesPresenter.initialize();

    verify(mockGetMaxDistance).execute(any(DisposableObserver.class), any(Void.class));
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testPreferencesPresenterSaveMaxDistance() {
    preferencesPresenter.saveDistance(DISTANCE);

    verify(mockSaveMaxDistance).execute(any(DisposableObserver.class), any(SaveMaxDistance.Params.class));
  }
}
