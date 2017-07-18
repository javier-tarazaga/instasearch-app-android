package com.javiertarazaga.instasearch.presentation.view.activity;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.javiertarazaga.instasearch.presentation.R;
import com.javiertarazaga.instasearch.presentation.internal.di.HasComponent;
import com.javiertarazaga.instasearch.presentation.internal.di.components.DaggerMainComponent;
import com.javiertarazaga.instasearch.presentation.internal.di.components.MainComponent;
import com.javiertarazaga.instasearch.presentation.view.fragment.SplashScreenFragment;

/**
 * Splash screen. This is the app entry point.
 */
public class SplashScreenActivity extends BaseActivity implements HasComponent<MainComponent>,
    SplashScreenFragment.SplashFragmentListener {

  private MainComponent mainComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_layout);
    ButterKnife.bind(this);

    this.initializeInjector();
    this.initializeActivity(savedInstanceState);
  }

  private void initializeInjector() {
    this.mainComponent = DaggerMainComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  private void initializeActivity(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      addFragment(R.id.fragment_container, new SplashScreenFragment());
    }
  }

  @Override public MainComponent getComponent() {
    return this.mainComponent;
  }

  @Override public void goToLoginView() {
    this.navigator.navigateToLoginView(this);
  }

  @Override public void goToMainView() {
    this.navigator.navigateToMainView(this);
  }
}
