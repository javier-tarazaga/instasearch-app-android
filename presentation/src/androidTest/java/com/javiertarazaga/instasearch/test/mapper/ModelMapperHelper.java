package com.javiertarazaga.instasearch.test.mapper;

import com.javiertarazaga.instasearch.domain.Caption;
import com.javiertarazaga.instasearch.domain.Images;
import com.javiertarazaga.instasearch.domain.Media;
import com.javiertarazaga.instasearch.domain.User;

/**
 * Class with helper methods common to all mappers (per example create fake objects), so we don't
 * have to copy paste a trillion times.
 */
class ModelMapperHelper {

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

  static User createFakeUser() {
    User user = new User(FAKE_USER_ID);
    user.setUsername(FAKE_USERNAME);
    user.setFullname(FAKE_FULLNAME);
    user.setProfilePicture(FAKE_PROFILE_PICTURE);
    user.setBio(FAKE_BIO);

    return user;
  }

  static Media createFakeMedia() {
    Media mediaEntity = new Media(FAKE_USER_ID);
    mediaEntity.setCaption(createFakeCaption());
    mediaEntity.setUser(createFakeUser());
    mediaEntity.setImages(createFakeImages());

    return mediaEntity;
  }

  static Images createFakeImages() {
    Images images = new Images();
    images.setLowResolution(createFakeImage());
    images.setStandardResolution(createFakeImage());
    images.setThumbnail(createFakeImage());

    return images;
  }

  static Images.Image createFakeImage() {
    Images.Image mage = new Images.Image();
    mage.setHeight(FAKE_HEIGHT);
    mage.setWidth(FAKE_WIDTH);
    mage.setUrl(FAKE_URL);

    return mage;
  }

  static Caption createFakeCaption() {
    Caption caption = new Caption(FAKE_CAPTION_ID);
    caption.setText(FAKE_CAPTION_TEXT);

    return caption;
  }
}
