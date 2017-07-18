package com.javiertarazaga.instasearch.presentation.model;

public class ImagesModel {

  private Image thumbnail;
  private Image lowResolution;
  private Image standardResolution;

  public ImagesModel() {
  }

  public Image getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(Image thumbnail) {
    this.thumbnail = thumbnail;
  }

  public Image getLowResolution() {
    return lowResolution;
  }

  public void setLowResolution(Image lowResolution) {
    this.lowResolution = lowResolution;
  }

  public Image getStandardResolution() {
    return standardResolution;
  }

  public void setStandardResolution(Image standardResolution) {
    this.standardResolution = standardResolution;
  }

  public static class Image {

    private int width;
    private int height;
    private String url;

    public Image() {
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
