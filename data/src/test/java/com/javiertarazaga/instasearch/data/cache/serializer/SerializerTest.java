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
package com.javiertarazaga.instasearch.data.cache.serializer;

import com.javiertarazaga.instasearch.data.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class) public class SerializerTest {

  private static final String JSON_RESPONSE = "{\n"
      + "    \"id\": 1,\n"
      + "    \"username\": \"jatago\",\n"
      + "    \"full_name\": \"Simon Hill\",\n"
      + "    \"profile_picture\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/19984598_110325572952662_1760547274049454080_a.jpg\",\n"
      + "    \"bio\": \"I am the best!\"\n"
      + "}";

  private Serializer serializer;

  @Before public void setUp() {
    serializer = new Serializer();
  }

  @Test public void testSerializeHappyCase() {
    final UserEntity userEntityOne = serializer.deserialize(JSON_RESPONSE, UserEntity.class);
    final String jsonString = serializer.serialize(userEntityOne, UserEntity.class);
    final UserEntity userEntityTwo = serializer.deserialize(jsonString, UserEntity.class);

    assertThat(userEntityOne.getUserId(), is(userEntityTwo.getUserId()));
    assertThat(userEntityOne.getUsername(), is(equalTo(userEntityTwo.getUsername())));
    assertThat(userEntityOne.getFullname(), is(equalTo(userEntityTwo.getFullname())));
    assertThat(userEntityOne.getProfilePicture(), is(userEntityTwo.getProfilePicture()));
    assertThat(userEntityOne.getBio(), is(userEntityTwo.getBio()));
  }

  @Test public void testDeserializeHappyCase() {
    final UserEntity userEntity = serializer.deserialize(JSON_RESPONSE, UserEntity.class);

    assertThat(userEntity.getUserId(), is("1"));
    assertThat(userEntity.getUsername(), is("jatago"));
    assertThat(userEntity.getFullname(), is("Simon Hill"));
    assertThat(userEntity.getProfilePicture(),
        is("https://scontent.cdninstagram.com/t51.2885-19/s150x150/19984598_110325572952662_1760547274049454080_a.jpg"));
    assertThat(userEntity.getBio(), is("I am the best!"));
  }
}
