package com.javiertarazaga.instasearch.data.entity.mapper;

import com.javiertarazaga.instasearch.data.entity.CaptionEntity;
import com.javiertarazaga.instasearch.data.entity.ImagesEntity;
import com.javiertarazaga.instasearch.data.entity.MediaEntity;
import com.javiertarazaga.instasearch.data.entity.UserEntity;

/**
 * Class with helper methods common to all mappers (per example create fake objects), so we don't
 * have to copy paste a trillion times.
 */
class MapperHelper {

  static final String FAKE_USER_ID = "123";
  static final String FAKE_USERNAME = "TonyStark";
  static final String FAKE_FULLNAME = "Tony Stark";
  static final String FAKE_PROFILE_PICTURE = "https://somecooldomain.com";
  static final String FAKE_BIO = "I am the best! Part. 2";

  static final int FAKE_HEIGHT = 100;
  static final int FAKE_WIDTH = 100;
  static final String FAKE_URL = "https://somecooldomain.com";

  static final String FAKE_CAPTION_ID = "123";
  static final String FAKE_CAPTION_TEXT = "Hola";

  static UserEntity createFakeUserEntity() {
    UserEntity userEntity = new UserEntity();
    userEntity.setUserId(FAKE_USER_ID);
    userEntity.setUsername(FAKE_USERNAME);
    userEntity.setFullname(FAKE_FULLNAME);
    userEntity.setProfilePicture(FAKE_PROFILE_PICTURE);
    userEntity.setBio(FAKE_BIO);

    return userEntity;
  }

  static MediaEntity createFakeMediaEntity() {
    MediaEntity mediaEntity = new MediaEntity();
    mediaEntity.setMediaId(FAKE_USER_ID);
    mediaEntity.setCaption(createFakeCaptionEntity());
    mediaEntity.setUser(createFakeUserEntity());
    mediaEntity.setImages(createFakeImagesEntity());

    return mediaEntity;
  }

  static ImagesEntity createFakeImagesEntity() {
    ImagesEntity imagesEntity = new ImagesEntity();
    imagesEntity.setLowResolution(createFakeImageEntity());
    imagesEntity.setStandardResolution(createFakeImageEntity());
    imagesEntity.setThumbnail(createFakeImageEntity());

    return imagesEntity;
  }

  static ImagesEntity.ImageEntity createFakeImageEntity() {
    ImagesEntity.ImageEntity imageEntity = new ImagesEntity.ImageEntity();
    imageEntity.setHeight(FAKE_HEIGHT);
    imageEntity.setWidth(FAKE_WIDTH);
    imageEntity.setUrl(FAKE_URL);

    return imageEntity;
  }

  static CaptionEntity createFakeCaptionEntity() {
    CaptionEntity captionEntity = new CaptionEntity();
    captionEntity.setCaptionId(FAKE_CAPTION_ID);
    captionEntity.setText(FAKE_CAPTION_TEXT);

    return captionEntity;
  }
}
