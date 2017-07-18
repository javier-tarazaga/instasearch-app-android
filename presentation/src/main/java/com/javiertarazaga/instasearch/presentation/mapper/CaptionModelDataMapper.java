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
package com.javiertarazaga.instasearch.presentation.mapper;

import com.javiertarazaga.instasearch.domain.Caption;
import com.javiertarazaga.instasearch.presentation.internal.di.PerActivity;
import com.javiertarazaga.instasearch.presentation.model.CaptionModel;
import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Caption} (in the data layer) to {@link CaptionModel} in the
 * domain layer.
 */
@PerActivity
public class CaptionModelDataMapper {

  @Inject CaptionModelDataMapper() {
  }

  /**
   * Transform a {@link Caption} into an {@link CaptionModel}.
   *
   * @param captionEntity Object to be transformed.
   * @return {@link CaptionModel} if valid {@link Caption} otherwise null.
   */
  public CaptionModel transform(Caption captionEntity) {
    CaptionModel caption = null;
    if (captionEntity != null) {
      caption = new CaptionModel(captionEntity.getCaptionId());
      caption.setText(captionEntity.getText());
    }
    return caption;
  }
}
