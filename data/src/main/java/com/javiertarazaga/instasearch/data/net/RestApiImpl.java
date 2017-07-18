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
package com.javiertarazaga.instasearch.data.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.fernandocejas.arrow.checks.Preconditions;
import com.javiertarazaga.instasearch.data.entity.MediaEntity;
import com.javiertarazaga.instasearch.data.entity.UserEntity;
import com.javiertarazaga.instasearch.data.entity.api.MediasApiResponseEntity;
import com.javiertarazaga.instasearch.data.entity.api.UserApiResponseEntity;
import com.javiertarazaga.instasearch.data.entity.mapper.json.MediaEntityJsonMapper;
import com.javiertarazaga.instasearch.data.entity.mapper.json.UserEntityJsonMapper;
import com.javiertarazaga.instasearch.data.exception.NetworkConnectionException;
import com.javiertarazaga.instasearch.domain.exception.user.UserException;
import com.squareup.okhttp.Response;
import io.reactivex.Observable;
import java.net.MalformedURLException;
import java.util.List;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {

  private final Context context;
  private final UserEntityJsonMapper userEntityJsonMapper;
  private final SharedPreferences sharedPreferences;
  private final MediaEntityJsonMapper mediaEntityJsonMapper;

  /**
   * Constructor of the class
   *
   * @param context {@link android.content.Context}.
   * @param userEntityJsonMapper {@link UserEntityJsonMapper}.
   */
  public RestApiImpl(Context context, UserEntityJsonMapper userEntityJsonMapper,
      MediaEntityJsonMapper mediaEntityJsonMapper, SharedPreferences sharedPreferences) {
    if (context == null
        || userEntityJsonMapper == null
        || mediaEntityJsonMapper == null
        || sharedPreferences == null) {
      throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
    }
    this.context = context.getApplicationContext();
    this.userEntityJsonMapper = userEntityJsonMapper;
    this.mediaEntityJsonMapper = mediaEntityJsonMapper;
    this.sharedPreferences = sharedPreferences;
  }

  @Override public Observable<UserEntity> user() {
    return Observable.create(emitter -> {
      if (isThereInternetConnection()) {
        try {
          Response responseUser = getUserFromApi();
          if (responseUser.isSuccessful()) {
            UserApiResponseEntity apiResponse =
                userEntityJsonMapper.transformUserApiResponse(responseUser.body().string());
            emitter.onNext(apiResponse.getData());
            emitter.onComplete();
          } else {
            emitter.onError(new UserException());
          }
        } catch (Exception e) {
          emitter.onError(new NetworkConnectionException(e.getCause()));
        }
      } else {
        emitter.onError(new NetworkConnectionException());
      }
    });
  }

  @Override public Observable<List<MediaEntity>> searchMediaByArea(double lat, double lng, int maxDistance) {
    Preconditions.checkNotNull(lat);
    Preconditions.checkNotNull(lng);

    return Observable.create(emitter -> {
      if (isThereInternetConnection()) {
        try {
          Response responseUser = searchMediasByArea(lat, lng, maxDistance);
          if (responseUser.isSuccessful()) {

            MediasApiResponseEntity apiResponse =
                this.mediaEntityJsonMapper.transformMediaApiResponse(responseUser.body().string());
            emitter.onNext(apiResponse.getData());
            emitter.onComplete();
          } else {
            emitter.onError(new UserException());
          }
        } catch (Exception e) {
          emitter.onError(new NetworkConnectionException(e.getCause()));
        }
      } else {
        emitter.onError(new NetworkConnectionException());
      }
    });
  }

  private Response getUserFromApi() throws MalformedURLException {
    String apiUrl =
        API_URL_GET_USER + "?access_token=" + this.sharedPreferences.getString("access_token", "");
    return ApiConnection.createGET(apiUrl).requestSyncCall();
  }

  private Response searchMediasByArea(double lat, double lng, int maxDistance) throws MalformedURLException {
    String apiUrl =
        API_URL_SEARCH_MEDIA_AREA
            + "?lat=" + String.valueOf(lat)
            + "&lng=" + String.valueOf(lng)
            + "&distance=" + String.valueOf(maxDistance)
            + "&access_token=" + this.sharedPreferences.getString("access_token", "");
    return ApiConnection.createGET(apiUrl).requestSyncCall();
  }

  /**
   * Checks if the device has any active internet connection.
   *
   * @return true device with internet connection, otherwise false.
   */
  private boolean isThereInternetConnection() {
    boolean isConnected;

    ConnectivityManager connectivityManager =
        (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

    return isConnected;
  }
}
