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
import com.javiertarazaga.instasearch.domain.interactor.GetUserList;
import com.javiertarazaga.instasearch.presentation.mapper.UserModelDataMapper;
import com.javiertarazaga.instasearch.presentation.presenter.MediaListPresenter;
import com.javiertarazaga.instasearch.presentation.view.MediaListView;
import io.reactivex.observers.DisposableObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MediaListPresenterTest {

  private MediaListPresenter mediaListPresenter;

  @Mock private Context mockContext;
  @Mock private MediaListView mockMediaListView;
  @Mock private GetUserList mockGetUserList;
  @Mock private UserModelDataMapper mockUserModelDataMapper;

  @Before
  public void setUp() {
    mediaListPresenter = new MediaListPresenter(mockGetUserList, mockUserModelDataMapper);
    mediaListPresenter.setView(mockMediaListView);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testUserListPresenterInitialize() {
    given(mockMediaListView.context()).willReturn(mockContext);

    mediaListPresenter.initialize();

    verify(mockMediaListView).hideRetry();
    verify(mockMediaListView).showLoading();
    verify(mockGetUserList).execute(any(DisposableObserver.class), any(Void.class));
  }
}
