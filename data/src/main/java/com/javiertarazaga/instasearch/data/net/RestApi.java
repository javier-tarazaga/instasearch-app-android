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
package com.javiertarazaga.instasearch.data.net;

import com.javiertarazaga.instasearch.data.entity.MediaEntity;
import com.javiertarazaga.instasearch.data.entity.UserEntity;
import io.reactivex.Observable;
import java.util.List;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
  String API_BASE_URL = "https://api.instagram.com/v1/";

  /** Api url for getting self */
  String API_URL_GET_USER = API_BASE_URL + "users/self";

  /** Api url for searching for medias given a lat and lng (Area) */
  String API_URL_SEARCH_MEDIA_AREA = API_BASE_URL + "media/search";

  /**
   * Retrieves an {@link Observable} which will emit a {@link UserEntity}.
   */
  Observable<UserEntity> user();

  /**
   * Get an {@link Observable} which will emit a {@link List<MediaEntity>} given a certain area in space
   * and a maxDistance radius.
   *  @param lat Latitude of the center search coordinate. If used, lng is required.
   * @param lng  Longitude of the center search coordinate. If used, lat is required.
   * @param maxDistance Max distance for radius
   */
  Observable<List<MediaEntity>> searchMediaByArea(double lat, double lng, int maxDistance);
}
