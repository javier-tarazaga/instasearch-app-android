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
package com.javiertarazaga.instasearch.domain.repository;

import com.javiertarazaga.instasearch.domain.Media;
import io.reactivex.Observable;
import java.util.List;

/**
 * Interface that represents a Repository for getting {@link Media} related data.
 */
public interface MediaRepository {

  /**
   * Get an {@link Observable} which will emit a {@link List<Media>} given a certain area in space
   * and a maxDistance radius.
   *  @param lat Latitude of the center search coordinate. If used, lng is required.
   * @param lng  Longitude of the center search coordinate. If used, lat is required.
   * @param maxDistance Max distance for radius
   */
  Observable<List<Media>> searchByArea(double lat, double lng, int maxDistance);
}
