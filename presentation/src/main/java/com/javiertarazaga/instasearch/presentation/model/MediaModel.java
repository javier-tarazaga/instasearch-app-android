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
package com.javiertarazaga.instasearch.presentation.model;

public class MediaModel {

  private final String mediaId;

  private UserModel user;
  private ImagesModel images;
  private CaptionModel caption;

  public MediaModel(String mediaId) {
    this.mediaId = mediaId;
  }

  public String getMediaId() {
    return mediaId;
  }

  public UserModel getUser() {
    return user;
  }

  public void setUser(UserModel user) {
    this.user = user;
  }

  public ImagesModel getImages() {
    return images;
  }

  public void setImages(ImagesModel imagesModel) {
    this.images = imagesModel;
  }

  public CaptionModel getCaption() {
    return caption;
  }

  public void setCaption(CaptionModel captionModel) {
    this.caption = captionModel;
  }
}
