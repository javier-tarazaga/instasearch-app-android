/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.javiertarazaga.instasearch.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.javiertarazaga.instasearch.presentation.R;
import com.javiertarazaga.instasearch.presentation.internal.di.components.MainComponent;
import com.javiertarazaga.instasearch.presentation.presenter.SplashScreenPresenter;
import com.javiertarazaga.instasearch.presentation.view.SplashScreenView;
import javax.inject.Inject;

/**
 * Fragment that shows the splash screen of the app in order to determine the initial flow of the app.
 */
public class SplashScreenFragment extends BaseFragment implements SplashScreenView {

  /**
   * Interface for listening splash screen events.
   */
  public interface SplashFragmentListener {
    void goToLoginView();
    void goToMainView();
  }

  @Inject SplashScreenPresenter presenter;

  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.bt_retry) Button bt_retry;

  private SplashFragmentListener splashFragmentListener;

  public SplashScreenFragment() {
    setRetainInstance(true);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    if (context instanceof SplashFragmentListener) {
      this.splashFragmentListener = (SplashFragmentListener) context;
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(MainComponent.class).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_splash_screen, container, false);
    ButterKnife.bind(this, fragmentView);
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.presenter.setView(this);
    if (savedInstanceState == null) {
      this.initApplication();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.presenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.presenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.presenter.destroy();
  }

  @Override public void onDetach() {
    super.onDetach();
    this.splashFragmentListener = null;
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

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  @Override public void goToLoginView() {
    if (this.splashFragmentListener != null) {
      this.splashFragmentListener.goToLoginView();
    }
  }

  @Override public void goToMainView() {
    if (this.splashFragmentListener != null) {
      this.splashFragmentListener.goToMainView();
    }
  }

  /**
   * Load all the require info to start the application.
   */
  private void initApplication() {
    this.presenter.initialize();
  }

  @OnClick(R.id.bt_retry) void onButtonRetryClick() {
    this.initApplication();

    //// TODO - Obviously this does not go here!!
    //if (this.splashFragmentListener != null) {
    //  this.splashFragmentListener.goToLoginView();
    //}
  }
}
