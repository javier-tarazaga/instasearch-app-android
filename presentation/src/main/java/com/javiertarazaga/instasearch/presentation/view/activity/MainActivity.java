package com.javiertarazaga.instasearch.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.javiertarazaga.instasearch.presentation.R;
import com.javiertarazaga.instasearch.presentation.internal.di.HasComponent;
import com.javiertarazaga.instasearch.presentation.internal.di.components.DaggerMainComponent;
import com.javiertarazaga.instasearch.presentation.internal.di.components.MainComponent;
import com.javiertarazaga.instasearch.presentation.view.fragment.BaseFragment;
import com.javiertarazaga.instasearch.presentation.view.fragment.MediaListFragment;
import com.javiertarazaga.instasearch.presentation.view.fragment.PreferencesFragment;

/**
 * Main application screen. This is the main interaction view of the app.
 */
public class MainActivity extends BaseActivity implements HasComponent<MainComponent>,
    PreferencesFragment.PreferenceFragmentListener {

  @Bind(R.id.bottom_navigation) BottomNavigationView bottom_nav;

  private MainComponent mainComponent;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, MainActivity.class);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    this.initializeInjector();
    setupBottomNav();
  }

  private void initializeInjector() {
    this.mainComponent = DaggerMainComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  private void setupBottomNav() {
    bottom_nav.setOnNavigationItemSelectedListener(
        new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
              case R.id.action_home:
                switchContent(new MediaListFragment());
                break;
              case R.id.action_settings:
                switchContent(new PreferencesFragment());
                break;
            }
            return true;
          }
        });

    // To do the trick of selecting the first tab when opening the view for the first time
    bottom_nav.setSelectedItemId(R.id.action_home);
  }

  private void switchContent(final BaseFragment fragment) {
    if (!this.isFinishing()) {
      // WORKAROUND_FOR_BUG_19917
      // http://code.google.com/p/android/issues/detail?id=19917
      // Need to add commitAllowingStateLoss instead of commit.
      // Like commit() but allows the commit to be executed after an activity's state is saved.
      getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
  }

  @Override public MainComponent getComponent() {
    return this.mainComponent;
  }

  @Override public void logoutSuccessful() {
    this.navigator.navigateToLogin(this);
  }
}
