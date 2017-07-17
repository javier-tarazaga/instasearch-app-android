/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
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
package com.javiertarazaga.instasearch.domain.repository;

import com.javiertarazaga.instasearch.domain.User;
import io.reactivex.Observable;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 */
public interface PreferencesRepository {

  /**
   * Save the radio distance to be used in order to search for the medias uploaded by other users
   * within that range.
   *
   * @param distance The distance to be saved
   */
  Observable<Integer> saveDistance(final int distance);


  /**
   * Get an {@link Observable} which will emit a {@link Integer} representing the distance between
   * the user and the medias available.
   */
  Observable<Integer> getDistance();

}
