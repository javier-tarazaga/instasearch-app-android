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
package com.javiertarazaga.instasearch.data.repository.media.datasource;

import com.javiertarazaga.instasearch.data.entity.MediaEntity;
import com.javiertarazaga.instasearch.data.net.RestApi;
import io.reactivex.Observable;
import java.util.List;

/**
 * {@link MediaDataStore} implementation based on connections to the api (Cloud).
 */
class MediaCloudDataStore implements MediaDataStore {

  private final RestApi restApi;

  /**
   * Construct a {@link MediaDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   */
  MediaCloudDataStore(RestApi restApi) {
    this.restApi = restApi;
  }

  @Override public Observable<List<MediaEntity>> searchByArea(double lat, double lng, int maxDistance) {
    return this.restApi.searchMediaByArea(lat, lng, maxDistance);
  }
}
