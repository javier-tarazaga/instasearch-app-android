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

import com.javiertarazaga.instasearch.data.entity.CaptionEntity;
import com.javiertarazaga.instasearch.domain.Caption;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link CaptionEntity} (in the data layer) to {@link Caption} in the
 * domain layer.
 */
@Singleton public class CaptionEntityDataMapper {

  @Inject CaptionEntityDataMapper() {
  }

  /**
   * Transform a {@link CaptionEntity} into an {@link Caption}.
   *
   * @param captionEntity Object to be transformed.
   * @return {@link Caption} if valid {@link CaptionEntity} otherwise null.
   */
  public Caption transform(CaptionEntity captionEntity) {
    Caption caption = null;
    if (captionEntity != null) {
      caption = new Caption(captionEntity.getCaptionId());
      caption.setText(captionEntity.getText());
    }
    return caption;
  }
}
