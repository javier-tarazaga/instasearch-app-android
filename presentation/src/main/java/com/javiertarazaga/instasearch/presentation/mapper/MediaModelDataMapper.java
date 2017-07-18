package com.javiertarazaga.instasearch.presentation.mapper;

import com.javiertarazaga.instasearch.domain.Media;
import com.javiertarazaga.instasearch.presentation.internal.di.PerActivity;
import com.javiertarazaga.instasearch.presentation.model.MediaModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Media} (in the data layer) to {@link MediaModel} in the
 * presentation layer.
 */
@PerActivity
public class MediaModelDataMapper {

  private final UserModelDataMapper userModelDataMapper;
  private final ImagesModelDataMapper imagesModelDataMapper;
  private final CaptionModelDataMapper captionModelDataMapper;

  @Inject public MediaModelDataMapper(UserModelDataMapper userModelDataMapper,
      ImagesModelDataMapper imagesModelDataMapper,
      CaptionModelDataMapper captionModelDataMapper) {
    this.userModelDataMapper = userModelDataMapper;
    this.imagesModelDataMapper = imagesModelDataMapper;
    this.captionModelDataMapper = captionModelDataMapper;
  }

  /**
   * Transform a {@link Media} into an {@link MediaModel}.
   *
   * @param media Object to be transformed.
   * @return {@link MediaModel} if valid {@link Media} otherwise null.
   */
  public MediaModel transform(Media media) {
    MediaModel mediaModel = null;
    if (media != null) {
      mediaModel = new MediaModel(media.getMediaId());
      mediaModel.setUser(this.userModelDataMapper.transform(media.getUser()));
      mediaModel.setImages(this.imagesModelDataMapper.transform(media.getImages()));
      mediaModel.setCaption(this.captionModelDataMapper.transform(media.getCaption()));
    }
    return mediaModel;
  }

  /**
   * Transform a Collection of {@link Media} into a Collection of {@link MediaModel}.
   *
   * @param mediaCollection Objects to be transformed.
   * @return List of {@link MediaModel}.
   */
  public Collection<MediaModel> transform(Collection<Media> mediaCollection) {
    Collection<MediaModel> mediaModelCollection;

    if (mediaCollection != null && !mediaCollection.isEmpty()) {
      mediaModelCollection = new ArrayList<>();
      for (Media media : mediaCollection) {
        mediaModelCollection.add(transform(media));
      }
    } else {
      mediaModelCollection = Collections.emptyList();
    }

    return mediaModelCollection;
  }
}
