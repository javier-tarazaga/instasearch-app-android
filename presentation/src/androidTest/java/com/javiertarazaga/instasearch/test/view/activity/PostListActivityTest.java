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
package com.javiertarazaga.instasearch.test.view.activity;

import android.app.Fragment;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import com.javiertarazaga.instasearch.presentation.view.activity.PostListActivity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostListActivityTest extends ActivityInstrumentationTestCase2<PostListActivity> {

  private PostListActivity postListActivity;

  public PostListActivityTest() {
    super(PostListActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();
    this.setActivityIntent(createTargetIntent());
    postListActivity = getActivity();
  }

  @Override protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testContainsUserListFragment() {
    Fragment userListFragment =
        postListActivity.getFragmentManager().findFragmentById(com.javiertarazaga.instasearch.presentation.R.id.fragmentContainer);
    assertThat(userListFragment, is(notNullValue()));
  }

  public void testContainsProperTitle() {
    String actualTitle = this.postListActivity.getTitle().toString().trim();

    assertThat(actualTitle, is("Users List"));
  }

  private Intent createTargetIntent() {
    Intent intentLaunchActivity =
        PostListActivity.getCallingIntent(getInstrumentation().getTargetContext());

    return intentLaunchActivity;
  }
}
