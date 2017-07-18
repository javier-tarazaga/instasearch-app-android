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
package com.javiertarazaga.instasearch.data.entity.mapper;

import com.javiertarazaga.instasearch.data.entity.ImagesEntity;
import com.javiertarazaga.instasearch.domain.Images;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link ImagesEntity} (in the data layer) to {@link Images} in the
 * domain layer.
 */
@Singleton
public class ImagesEntityDataMapper {

  @Inject ImagesEntityDataMapper() {}

  /**
   * Transform a {@link ImagesEntity} into an {@link Images}.
   *
   * @param imagesEntity Object to be transformed.
   * @return {@link Images} if valid {@link ImagesEntity} otherwise null.
   */
  public Images transform(ImagesEntity imagesEntity) {
    Images images = null;
    if (imagesEntity != null) {
      images = new Images();
      images.setThumbnail(transform(imagesEntity.getThumbnail()));
      images.setLowResolution(transform(imagesEntity.getLowResolution()));
      images.setStandardResolution(transform(imagesEntity.getStandardResolution()));
    }
    return images;
  }

  /**
   * Transform a {@link ImagesEntity.ImageEntity} into an {@link Images.Image}.
   *
   * @param imageEntity Object to be transformed.
   * @return {@link Images.Image} if valid {@link ImagesEntity.ImageEntity} otherwise null.
   */
  public Images.Image transform(ImagesEntity.ImageEntity imageEntity) {
    Images.Image image = null;
    if (imageEntity != null) {
      image = new Images.Image();
      image.setWidth(imageEntity.getWidth());
      image.setHeight(imageEntity.getHeight());
      image.setUrl(imageEntity.getUrl());
    }

    return image;
  }
}
