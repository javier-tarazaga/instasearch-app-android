/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.javiertarazaga.instasearch.presentation.view;

import com.javiertarazaga.instasearch.presentation.model.MediaModel;
import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link MediaModel}.
 */
public interface MediaListView extends LoadDataView {
  /**
   * Render a media list in the UI.
   *
   * @param mediaModelCollection The collection of {@link MediaModel} that will be shown.
   */
  void renderMediaList(Collection<MediaModel> mediaModelCollection);

  /**
   * View a {@link MediaModel} profile/details.
   *
   * @param mediaModel The media that will be shown.
   */
  void viewMedia(MediaModel mediaModel);
}
