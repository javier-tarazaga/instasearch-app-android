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
package com.javiertarazaga.instasearch.data.entity.mapper;

import com.javiertarazaga.instasearch.data.entity.UserEntity;
import com.javiertarazaga.instasearch.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_BIO;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_FULLNAME;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_PROFILE_PICTURE;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_USERNAME;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_USER_ID;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserEntityDataMapperTest {

  private UserEntityDataMapper userEntityDataMapper;

  @Before
  public void setUp() throws Exception {
    userEntityDataMapper = new UserEntityDataMapper();
  }

  @Test
  public void testTransformUserEntity() {
    UserEntity userEntity = MapperHelper.createFakeUserEntity();
    User user = userEntityDataMapper.transform(userEntity);

    assertThat(user, is(instanceOf(User.class)));
    assertThat(user.getUserId(), is(FAKE_USER_ID));
    assertThat(user.getUsername(), is(FAKE_USERNAME));
    assertThat(user.getFullname(), is(FAKE_FULLNAME));
    assertThat(user.getProfilePicture(), is(FAKE_PROFILE_PICTURE));
    assertThat(user.getBio(), is(FAKE_BIO));
  }


}
