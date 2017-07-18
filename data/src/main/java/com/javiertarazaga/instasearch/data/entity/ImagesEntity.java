package com.javiertarazaga.instasearch.data.entity;

import com.google.gson.annotations.SerializedName;

public class ImagesEntity {

  @SerializedName("thumbnail") private ImageEntity thumbnail;

  @SerializedName("low_resolution") private ImageEntity lowResolution;

  @SerializedName("standard_resolution") private ImageEntity standardResolution;

  public ImagesEntity() {
  }

  public ImageEntity getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(ImageEntity thumbnail) {
    this.thumbnail = thumbnail;
  }

  public ImageEntity getLowResolution() {
    return lowResolution;
  }

  public void setLowResolution(ImageEntity lowResolution) {
    this.lowResolution = lowResolution;
  }

  public ImageEntity getStandardResolution() {
    return standardResolution;
  }

  public void setStandardResolution(ImageEntity standardResolution) {
    this.standardResolution = standardResolution;
  }

  public static class ImageEntity {

    @SerializedName("width") private int width;

    @SerializedName("height") private int height;

    @SerializedName("url") private String url;

    public ImageEntity() {
    }

    public int getWidth() {
      return width;
    }

    public void setWidth(int width) {
      this.width = width;
    }

    public int getHeight() {
      return height;
    }

    public void setHeight(int height) {
      this.height = height;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }
  }
}
