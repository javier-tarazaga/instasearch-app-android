package com.javiertarazaga.instasearch.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.javiertarazaga.instasearch.presentation.R;
import com.javiertarazaga.instasearch.presentation.internal.di.HasComponent;
import com.javiertarazaga.instasearch.presentation.internal.di.components.DaggerUserComponent;
import com.javiertarazaga.instasearch.presentation.internal.di.components.UserComponent;
import com.javiertarazaga.instasearch.presentation.view.fragment.LoginFragment;

/**
 * Login screen. This is the app login entry point to the user's Instagram.
 */
public class LoginActivity extends BaseActivity implements HasComponent<UserComponent> {

  private UserComponent userComponent;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, LoginActivity.class);
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
      addFragment(R.id.fragment_container, new LoginFragment());
    }
  }

  private void initializeInjector() {
    this.userComponent = DaggerUserComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public UserComponent getComponent() {
    return this.userComponent;
  }
}
