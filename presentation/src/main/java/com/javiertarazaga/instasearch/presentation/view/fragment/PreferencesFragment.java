/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.javiertarazaga.instasearch.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.javiertarazaga.instasearch.presentation.R;
import com.javiertarazaga.instasearch.presentation.internal.di.components.PreferencesComponent;
import com.javiertarazaga.instasearch.presentation.presenter.PreferencesPresenter;
import com.javiertarazaga.instasearch.presentation.view.PreferencesView;
import javax.inject.Inject;

/**
 * Fragment that shows a list of Users.
 */
public class PreferencesFragment extends BaseFragment implements PreferencesView {

  @Inject PreferencesPresenter preferencesPresenter;

  @Bind(R.id.tv_distance) TextView tv_distance;
  @Bind(R.id.sb_distance) SeekBar sb_distance;

  public PreferencesFragment() {
    setRetainInstance(true);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getComponent(PreferencesComponent.class).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_preferences, container, false);
    ButterKnife.bind(this, fragmentView);
    initPreferences();
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.preferencesPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadPreferences();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.preferencesPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.preferencesPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.preferencesPresenter.destroy();
  }

  @Override public void updateDistance(int distance) {
    this.sb_distance.setProgress(distance);
  }

  private void initPreferences() {
    this.sb_distance.setMax(5000);
    this.sb_distance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        PreferencesFragment.this.tv_distance.setText(progress);
      }

      @Override public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override public void onStopTrackingTouch(SeekBar seekBar) {
        PreferencesFragment.this.saveDistance(seekBar.getProgress());
      }
    });
  }

  private void loadPreferences() {
    this.preferencesPresenter.initialize();
  }

  private void saveDistance(int distance) {
    this.preferencesPresenter.saveDistance(distance);
  }
}
