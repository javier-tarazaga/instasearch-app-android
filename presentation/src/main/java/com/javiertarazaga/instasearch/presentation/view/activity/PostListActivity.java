/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.javiertarazaga.instasearch.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import com.javiertarazaga.instasearch.presentation.R;
import com.javiertarazaga.instasearch.presentation.internal.di.HasComponent;
import com.javiertarazaga.instasearch.presentation.internal.di.components.DaggerUserComponent;
import com.javiertarazaga.instasearch.presentation.internal.di.components.UserComponent;
import com.javiertarazaga.instasearch.presentation.model.UserModel;
import com.javiertarazaga.instasearch.presentation.view.fragment.MediaListFragment;

/**
 * Activity that shows a list of Users.
 */
public class PostListActivity extends BaseActivity implements HasComponent<UserComponent>,
    MediaListFragment.UserListListener {

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, PostListActivity.class);
  }

  private UserComponent userComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_layout);

    this.initializeInjector();
    if (savedInstanceState == null) {
      addFragment(R.id.fragment_container, new MediaListFragment());
    }
  }

  private void initializeInjector() {
    this.userComponent = DaggerUserComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public UserComponent getComponent() {
    return userComponent;
  }

  @Override public void onUserClicked(UserModel userModel) {
    this.navigator.navigateToUserDetails(this, userModel.getUserId());
  }
}
