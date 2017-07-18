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

import com.javiertarazaga.instasearch.data.entity.MediaEntity;
import com.javiertarazaga.instasearch.domain.Media;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link MediaEntity} (in the data layer) to {@link Media} in the
 * domain layer.
 */
@Singleton public class MediaEntityDataMapper {

  private final UserEntityDataMapper userEntityDataMapper;
  private final ImagesEntityDataMapper imagesEntityDataMapper;
  private final CaptionEntityDataMapper captionEntityDataMapper;

  @Inject MediaEntityDataMapper(UserEntityDataMapper userEntityDataMapper,
      ImagesEntityDataMapper imagesEntityDataMapper,
      CaptionEntityDataMapper captionEntityDataMapper) {
    this.userEntityDataMapper = userEntityDataMapper;
    this.imagesEntityDataMapper = imagesEntityDataMapper;
    this.captionEntityDataMapper = captionEntityDataMapper;
  }

  /**
   * Transform a {@link MediaEntity} into an {@link Media}.
   *
   * @param mediaEntity Object to be transformed.
   * @return {@link Media} if valid {@link MediaEntity} otherwise null.
   */
  public Media transform(MediaEntity mediaEntity) {
    Media media = null;
    if (mediaEntity != null) {
      media = new Media(mediaEntity.getMediaId());
      media.setUser(this.userEntityDataMapper.transform(mediaEntity.getUser()));
      media.setImages(this.imagesEntityDataMapper.transform(mediaEntity.getImages()));
      media.setCaption(this.captionEntityDataMapper.transform(mediaEntity.getCaption()));
    }
    return media;
  }

  /**
   * Transform a List of {@link MediaEntity} into a Collection of {@link Media}.
   *
   * @param mediaEntityCollection Object Collection to be transformed.
   * @return {@link Media} if valid {@link MediaEntity} otherwise null.
   */
  public List<Media> transform(Collection<MediaEntity> mediaEntityCollection) {
    final List<Media> mediaList = new ArrayList<>(20);
    for (MediaEntity mediaEntity : mediaEntityCollection) {
      final Media media = transform(mediaEntity);
      if (media != null) {
        mediaList.add(media);
      }
    }
    return mediaList;
  }
}
