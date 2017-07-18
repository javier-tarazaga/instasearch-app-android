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
package com.javiertarazaga.instasearch.test.mapper;

import com.javiertarazaga.instasearch.domain.User;
import com.javiertarazaga.instasearch.presentation.mapper.UserModelDataMapper;
import com.javiertarazaga.instasearch.presentation.model.UserModel;
import junit.framework.TestCase;
import org.junit.Test;

import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_BIO;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_FULLNAME;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_PROFILE_PICTURE;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_USERNAME;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_USER_ID;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.createFakeUser;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModelDataMapperTest extends TestCase {

  private UserModelDataMapper userModelDataMapper;

  @Override protected void setUp() throws Exception {
    super.setUp();
    this.userModelDataMapper = new UserModelDataMapper();
  }

  @Test
  public void testTransformUser() {
    User user = createFakeUser();
    UserModel userModel = this.userModelDataMapper.transform(user);

    assertThat(userModel, is(instanceOf(UserModel.class)));
    assertThat(userModel.getUserId(), is(FAKE_USER_ID));
    assertThat(userModel.getUsername(), is(FAKE_USERNAME));
    assertThat(userModel.getfullname(), is(FAKE_FULLNAME));
    assertThat(userModel.getProfilePicture(), is(FAKE_PROFILE_PICTURE));
    assertThat(userModel.getBio(), is(FAKE_BIO));
  }
}
