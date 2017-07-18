package com.javiertarazaga.instasearch.data.entity.mapper.json;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.javiertarazaga.instasearch.data.entity.MediaEntity;
import com.javiertarazaga.instasearch.data.entity.api.MediasApiResponseEntity;
import java.lang.reflect.Type;
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
   * @param mediaJsonResponse A json representing a media.
   * @return {@link MediasApiResponseEntity}.
   * @throws JsonSyntaxException if the json string is not a valid json structure.
   */
  public MediasApiResponseEntity transformMediaApiResponse(String mediaJsonResponse) throws JsonSyntaxException {
    final Type mediaEntityType = new TypeToken<MediasApiResponseEntity>() {}.getType();
    return this.gson.fromJson(mediaJsonResponse, mediaEntityType);
  }
}
