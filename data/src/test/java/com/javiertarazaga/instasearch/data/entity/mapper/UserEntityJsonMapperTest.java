/**
 * Copyright (C) 2017 Javier Tarazaga Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.javiertarazaga.instasearch.data.entity.mapper;

import com.google.gson.JsonSyntaxException;
import com.javiertarazaga.instasearch.data.entity.api.UserApiResponseEntity;
import com.javiertarazaga.instasearch.data.entity.mapper.json.UserEntityJsonMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class) public class UserEntityJsonMapperTest {

  private static final String JSON_RESPONSE = "{\n"
      + "    \"data\": {\n"
      + "    \"id\": 1,\n"
      + "    \"username\": \"jatago\",\n"
      + "    \"full_name\": \"Simon Hill\",\n"
      + "    \"profile_picture\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/19984598_110325572952662_1760547274049454080_a.jpg\",\n"
      + "    \"bio\": \"I am the best!\"\n"
      + "}"
      + "}";

  private UserEntityJsonMapper userEntityJsonMapper;

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Before public void setUp() {
    userEntityJsonMapper = new UserEntityJsonMapper();
  }

  @Test public void testTransformUserEntityHappyCase() {
    UserApiResponseEntity apiResponse =
        userEntityJsonMapper.transformUserApiResponse(JSON_RESPONSE);

    assertThat(apiResponse.getData().getUserId(), is("1"));
    assertThat(apiResponse.getData().getFullname(), is(equalTo("Simon Hill")));
    assertThat(apiResponse.getData().getUsername(), is(equalTo("jatago")));
    assertThat(apiResponse.getData().getProfilePicture(), is(equalTo(
        "https://scontent.cdninstagram.com/t51.2885-19/s150x150/19984598_110325572952662_1760547274049454080_a.jpg")));
    assertThat(apiResponse.getData().getBio(), is(equalTo("I am the best!")));
  }

  @Test public void testTransformUserEntityNotValidResponse() {
    expectedException.expect(JsonSyntaxException.class);
    userEntityJsonMapper.transformUserApiResponse("ironman");
  }
}
