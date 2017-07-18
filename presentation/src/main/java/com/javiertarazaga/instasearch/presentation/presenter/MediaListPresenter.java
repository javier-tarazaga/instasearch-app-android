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
package com.javiertarazaga.instasearch.presentation.presenter;

import android.support.annotation.NonNull;
import com.javiertarazaga.instasearch.domain.User;
import com.javiertarazaga.instasearch.domain.exception.DefaultErrorBundle;
import com.javiertarazaga.instasearch.domain.exception.ErrorBundle;
import com.javiertarazaga.instasearch.domain.interactor.DefaultObserver;
import com.javiertarazaga.instasearch.domain.interactor.GetMediaList;
import com.javiertarazaga.instasearch.presentation.exception.ErrorMessageFactory;
import com.javiertarazaga.instasearch.presentation.internal.di.PerActivity;
import com.javiertarazaga.instasearch.presentation.mapper.UserModelDataMapper;
import com.javiertarazaga.instasearch.presentation.model.UserModel;
import com.javiertarazaga.instasearch.presentation.view.MediaListView;
import java.util.Collection;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class MediaListPresenter implements Presenter {

  private MediaListView viewListView;

  private final GetMediaList getMediaList;
  private final UserModelDataMapper userModelDataMapper;

  @Inject
  public MediaListPresenter(GetMediaList getMediaList,
      UserModelDataMapper userModelDataMapper) {
    this.getMediaList = getMediaList;
    this.userModelDataMapper = userModelDataMapper;
  }

  public void setView(@NonNull MediaListView view) {
    this.viewListView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getMediaList.dispose();
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the user list.
   */
  public void initialize() {
    this.loadUserList();
  }

  /**
   * Loads all users.
   */
  private void loadUserList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUserList();
  }

  public void onUserClicked(UserModel userModel) {
    this.viewListView.viewUser(userModel);
  }

  private void showViewLoading() {
    this.viewListView.showLoading();
  }

  private void hideViewLoading() {
    this.viewListView.hideLoading();
  }

  private void showViewRetry() {
    this.viewListView.showRetry();
  }

  private void hideViewRetry() {
    this.viewListView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
                                                     errorBundle.getException());
    this.viewListView.showError(errorMessage);
  }

  private void showUsersCollectionInView(Collection<User> usersCollection) {
    final Collection<UserModel> userModelsCollection =
        this.userModelDataMapper.transform(usersCollection);
    this.viewListView.renderUserList(userModelsCollection);
  }

  private void getUserList() {
    this.getMediaList.execute(new MediaListObserver(), null);
  }

  private final class MediaListObserver extends DefaultObserver<User> {

    @Override public void onComplete() {
      MediaListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      MediaListPresenter.this.hideViewLoading();
      MediaListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      MediaListPresenter.this.showViewRetry();
    }

    @Override public void onNext(User users) {
      //MediaListPresenter.this.showUsersCollectionInView(users);
    }
  }
}
