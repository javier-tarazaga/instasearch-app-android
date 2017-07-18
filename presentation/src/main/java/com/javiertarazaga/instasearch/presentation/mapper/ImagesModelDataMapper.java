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
package com.javiertarazaga.instasearch.presentation.mapper;

import com.javiertarazaga.instasearch.domain.Images;
import com.javiertarazaga.instasearch.presentation.internal.di.PerActivity;
import com.javiertarazaga.instasearch.presentation.model.ImagesModel;
import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Images} (in the data layer) to {@link ImagesModel} in the
 * domain layer.
 */
@PerActivity
public class ImagesModelDataMapper {

  @Inject public ImagesModelDataMapper() {}

  /**
   * Transform a {@link Images} into an {@link ImagesModel}.
   *
   * @param imagesEntity Object to be transformed.
   * @return {@link ImagesModel} if valid {@link Images} otherwise null.
   */
  public ImagesModel transform(Images imagesEntity) {
    ImagesModel images = null;
    if (imagesEntity != null) {
      images = new ImagesModel();
      images.setThumbnail(transform(imagesEntity.getThumbnail()));
      images.setLowResolution(transform(imagesEntity.getLowResolution()));
      images.setStandardResolution(transform(imagesEntity.getStandardResolution()));
    }
    return images;
  }

  /**
   * Transform a {@link Images.Image} into an {@link ImagesModel.Image}.
   *
   * @param imageEntity Object to be transformed.
   * @return {@link ImagesModel.Image} if valid {@link Images.Image} otherwise null.
   */
  public ImagesModel.Image transform(Images.Image imageEntity) {
    ImagesModel.Image image = null;
    if (imageEntity != null) {
      image = new ImagesModel.Image();
      image.setWidth(imageEntity.getWidth());
      image.setHeight(imageEntity.getHeight());
      image.setUrl(imageEntity.getUrl());
    }

    return image;
  }
}
