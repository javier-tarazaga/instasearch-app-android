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
import com.javiertarazaga.instasearch.data.entity.MediaEntity;
import com.javiertarazaga.instasearch.data.entity.UserEntity;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class MediaEntityJsonMapper {

  private final Gson gson;

  @Inject
  public MediaEntityJsonMapper() {
    this.gson = new Gson();
  }

  /**
   * Transform from valid json string to {@link MediaEntity}.
   *
   * @param mediaJsonResponse A json representing a user profile.
   * @return {@link MediaEntity}.
   * @throws JsonSyntaxException if the json string is not a valid json structure.
   */
  public MediaEntity transformMediaEntity(String mediaJsonResponse) throws JsonSyntaxException {
    final Type mediaEntityType = new TypeToken<UserEntity>() {}.getType();
    return this.gson.fromJson(mediaJsonResponse, mediaEntityType);
  }

  /**
   * Transform from valid json string to List of {@link MediaEntity}.
   *
   * @param mediaListJsonResponse A json representing a collection of medias.
   * @return List of {@link MediaEntity}.
   * @throws JsonSyntaxException if the json string is not a valid json structure.
   */
  public List<UserEntity> transformMediaEntityCollection(String mediaListJsonResponse)
      throws JsonSyntaxException {
    final Type listOfUserEntityType = new TypeToken<List<MediaEntity>>() {}.getType();
    return this.gson.fromJson(mediaListJsonResponse, listOfUserEntityType);
  }
}
