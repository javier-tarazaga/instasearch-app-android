/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.javiertarazaga.instasearch.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.javiertarazaga.instasearch.presentation.R;
import com.javiertarazaga.instasearch.presentation.internal.di.components.UserComponent;
import com.javiertarazaga.instasearch.presentation.model.MediaModel;
import com.javiertarazaga.instasearch.presentation.presenter.MediaListPresenter;
import com.javiertarazaga.instasearch.presentation.view.MediaListView;
import com.javiertarazaga.instasearch.presentation.view.adapter.MediasAdapter;
import com.javiertarazaga.instasearch.presentation.view.adapter.MediasLayoutManager;
import java.util.Collection;
import javax.inject.Inject;

/**
 * Fragment that shows a list of Users.
 */
public class MediaListFragment extends BaseFragment implements MediaListView {

  /**
   * Interface for listening media list events.
   */
  public interface MediaListListener {
    void onMediaClicked(final MediaModel mediaModel);
  }

  @Inject MediaListPresenter mediaListPresenter;
  @Inject MediasAdapter mediasAdapter;

  @Bind(R.id.rv_medias) RecyclerView rv_medias;
  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.bt_retry) Button bt_retry;

  private MediaListListener mediaListListener;

  public MediaListFragment() {
    setRetainInstance(true);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof MediaListListener) {
      this.mediaListListener = (MediaListListener) activity;
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(gsavedInstanceState);
    this.getComponent(UserComponent.class).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(com.javiertarazaga.instasearch.presentation.R.layout.fragment_media_list, container, false);
    ButterKnife.bind(this, fragmentView);
    setupRecyclerView();
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.mediaListPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadUserList();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.mediaListPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.mediaListPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    rv_medias.setAdapter(null);
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.mediaListPresenter.destroy();
  }

  @Override public void onDetach() {
    super.onDetach();
    this.mediaListListener = null;
  }

  @Override public void showLoading() {
    this.rl_progress.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    this.rl_progress.setVisibility(View.GONE);
  }

  @Override public void showRetry() {
    this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void renderMediaList(Collection<MediaModel> mediaModelCollection) {
    if (mediaModelCollection != null) {
      this.mediasAdapter.setMediaCollection(mediaModelCollection);
    }
  }

  @Override public void viewMedia(MediaModel mediaModel) {
    if (this.mediaListListener != null) {
      this.mediaListListener.onMediaClicked(mediaModel);
    }
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void setupRecyclerView() {
    this.mediasAdapter.setOnItemClickListener(onItemClickListener);
    this.rv_medias.setLayoutManager(new MediasLayoutManager(context()));
    this.rv_medias.setAdapter(mediasAdapter);
  }

  /**
   * Loads all medias.
   */
  private void loadUserList() {
    this.mediaListPresenter.initialize();
  }

  @OnClick(R.id.bt_retry) void onButtonRetryClick() {
    MediaListFragment.this.loadUserList();
  }

  private MediasAdapter.OnItemClickListener onItemClickListener =
      new MediasAdapter.OnItemClickListener() {
        @Override public void onMediaItemClicked(MediaModel mediaModel) {
          if (MediaListFragment.this.mediaListPresenter != null && mediaModel != null) {
            MediaListFragment.this.mediaListPresenter.onUserClicked(mediaModel);
          }
        }
      };
}
