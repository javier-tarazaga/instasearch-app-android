package com.javiertarazaga.instasearch.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.javiertarazaga.instasearch.presentation.R;
import com.javiertarazaga.instasearch.presentation.internal.di.HasComponent;
import com.javiertarazaga.instasearch.presentation.internal.di.components.DaggerPreferencesComponent;
import com.javiertarazaga.instasearch.presentation.internal.di.components.PreferencesComponent;
import com.javiertarazaga.instasearch.presentation.view.fragment.LoginFragment;

/**
 * Login screen. This is the app login entry point to the user's Instagram.
 */
public class PreferencesActivity extends BaseActivity implements HasComponent<PreferencesComponent> {

  private PreferencesComponent preferencesComponent;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, PreferencesActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_layout);

    this.initializeInjector();
    this.initializeActivity(savedInstanceState);
  }

  /**
   * Initializes this activity.
   */
  private void initializeActivity(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, new LoginFragment());
    }
  }

  private void initializeInjector() {
    this.preferencesComponent = DaggerPreferencesComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public PreferencesComponent getComponent() {
    return this.preferencesComponent;
  }
}
