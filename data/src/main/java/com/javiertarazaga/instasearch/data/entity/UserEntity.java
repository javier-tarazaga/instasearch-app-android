/**
 * Copyright (C) 2017 Javier Tarazaga Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
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
 * User Entity used in the data layer.
 *
 * NOTE: This shouldn't be structured like this, but lets leave it like this for simplicity. Every
 * response from the API has a data and meta objects which contains the real object (not strongly
 * type languages!) but anyway, this will do the job for now instead of having to deal with
 * poly-serialization and more complex stuff
 */
public class UserEntity {

  @SerializedName("data")
  private Data data;

  public UserEntity() {
    //empty
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public static class Data {

    @SerializedName("id")
    private String userId;

    @SerializedName("username")
    private String username;

    @SerializedName("profile_picture")
    private String profilePicture;

    @SerializedName("full_name")
    private String fullname;

    @SerializedName("bio")
    private String bio;

    public String getUserId() {
      return userId;
    }

    public void setUserId(String userId) {
      this.userId = userId;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getProfilePicture() {
      return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
      this.profilePicture = profilePicture;
    }

    public String getFullname() {
      return fullname;
    }

    public void setFullname(String fullname) {
      this.fullname = fullname;
    }

    public String getBio() {
      return bio;
    }

    public void setBio(String bio) {
      this.bio = bio;
    }
  }


}
