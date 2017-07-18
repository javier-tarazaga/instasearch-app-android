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
package com.javiertarazaga.instasearch.data.entity.mapper.json;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.javiertarazaga.instasearch.data.entity.api.UserApiResponseEntity;
import java.lang.reflect.Type;
import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class UserEntityJsonMapper {

  private final Gson gson;

  @Inject
  public UserEntityJsonMapper() {
    this.gson = new Gson();
  }

  /**
   * Transform from valid json string to {@link UserApiResponseEntity}.
   *
   * @param userJsonResponse A json representing a user profile.
   * @return {@link UserApiResponseEntity}.
   * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
   */
  public UserApiResponseEntity transformUserApiResponse(String userJsonResponse) throws JsonSyntaxException {
    final Type userEntityType = new TypeToken<UserApiResponseEntity>() {}.getType();
    return this.gson.fromJson(userJsonResponse, userEntityType);
  }
}
