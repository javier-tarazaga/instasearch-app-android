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
package com.javiertarazaga.instasearch.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Media Entity used in the data layer.
 */
public class MediaEntity {

  @SerializedName("id") private String mediaId;

  @SerializedName("user") private UserEntity user;

  @SerializedName("images") private ImagesEntity images;

  @SerializedName("caption") private CaptionEntity caption;

  public String getMediaId() {
    return mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public ImagesEntity getImages() {
    return images;
  }

  public void setImages(ImagesEntity images) {
    this.images = images;
  }

  public CaptionEntity getCaption() {
    return caption;
  }

  public void setCaption(CaptionEntity caption) {
    this.caption = caption;
  }
}
