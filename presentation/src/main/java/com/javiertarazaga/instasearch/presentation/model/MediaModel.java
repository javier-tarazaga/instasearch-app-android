/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
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

/**
 * Class that represents an Instagram Media in the presentation layer.
 */
public class MediaModel {

  private final int mediaId;
  private final UserModel userModel;

  private ImagesModel imagesModel;

  public MediaModel(int mediaId, UserModel userModel) {
    this.mediaId= mediaId;
    this.userModel= userModel;
  }

  public int getMediaId() {
    return mediaId;
  }

  public UserModel getUserModel() {
    return userModel;
  }

  public ImagesModel getImagesModel() {
    return imagesModel;
  }

  public void setImagesModel(ImagesModel imagesModel) {
    this.imagesModel = imagesModel;
  }

  private static class ImagesModel {
    private ImageModel lowResolution;
    private ImageModel thumbnail;
    private ImageModel standardResolution;

    public ImageModel getLowResolution() {
      return lowResolution;
    }

    public void setLowResolution(ImageModel lowResolution) {
      this.lowResolution = lowResolution;
    }

    public ImageModel getThumbnail() {
      return thumbnail;
    }

    public void setThumbnail(ImageModel thumbnail) {
      this.thumbnail = thumbnail;
    }

    public ImageModel getStandardResolution() {
      return standardResolution;
    }

    public void setStandardResolution(ImageModel standardResolution) {
      this.standardResolution = standardResolution;
    }
  }

  private static class ImageModel {

    private final String url;
    private final int width;
    private final int height;

    private ImageModel(String url, int width, int height) {
      this.url = url;
      this.width = width;
      this.height = height;
    }

    public String getUrl() {
      return url;
    }

    public int getWidth() {
      return width;
    }

    public int getHeight() {
      return height;
    }
  }
}
