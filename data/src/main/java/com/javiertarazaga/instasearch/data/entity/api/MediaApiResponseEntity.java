package com.javiertarazaga.instasearch.data.entity.api;

import com.google.gson.annotations.SerializedName;
import com.javiertarazaga.instasearch.data.entity.MediaEntity;

/**
 * Media API Response Entity used in the data layer.
 *
 * NOTE: This shouldn't be structured like this, but lets leave it like this for simplicity. Every
 * response from the API has a data and meta objects which contains the real object (not strongly
 * type languages!) but anyway, this will do the job for now instead of having to deal with
 * poly-serialization and more complex stuff
 */
public class MediaApiResponseEntity {

  @SerializedName("data")
  private MediaEntity data;

  public MediaApiResponseEntity() {
    //empty
  }

  public MediaEntity getData() {
    return data;
  }

  public void setData(MediaEntity data) {
    this.data = data;
  }
}
