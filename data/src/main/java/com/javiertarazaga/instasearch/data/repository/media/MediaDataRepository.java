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
package com.javiertarazaga.instasearch.data.repository.media;

import com.javiertarazaga.instasearch.data.entity.mapper.MediaEntityDataMapper;
import com.javiertarazaga.instasearch.data.entity.mapper.UserEntityDataMapper;
import com.javiertarazaga.instasearch.data.repository.media.datasource.MediaDataStore;
import com.javiertarazaga.instasearch.data.repository.media.datasource.MediaDataStoreFactory;
import com.javiertarazaga.instasearch.domain.Media;
import com.javiertarazaga.instasearch.domain.repository.MediaRepository;
import com.javiertarazaga.instasearch.domain.repository.UserRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton
public class MediaDataRepository implements MediaRepository {

  private final MediaDataStoreFactory mediaDataStoreFactory;
  private final MediaEntityDataMapper mediaEntityDataMapper;

  /**
   * Constructs a {@link UserRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param mediaEntityDataMapper {@link UserEntityDataMapper}.
   */
  @Inject public MediaDataRepository(MediaDataStoreFactory dataStoreFactory,
      MediaEntityDataMapper mediaEntityDataMapper) {
    this.mediaDataStoreFactory = dataStoreFactory;
    this.mediaEntityDataMapper = mediaEntityDataMapper;
  }

  @Override public Observable<List<Media>> searchByArea(double lat, double lng, int maxDistance) {
    final MediaDataStore mediaDataStore = this.mediaDataStoreFactory.create();
    return mediaDataStore.searchByArea(lat, lng, maxDistance).map(this.mediaEntityDataMapper::transform);
  }
}
