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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.javiertarazaga.instasearch.data.entity.UserEntity;
import com.javiertarazaga.instasearch.data.entity.mapper.UserEntityJsonMapper;
import com.javiertarazaga.instasearch.data.exception.NetworkConnectionException;
import io.reactivex.Observable;
import java.net.MalformedURLException;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {

  private final Context context;
  private final UserEntityJsonMapper userEntityJsonMapper;

  /**
   * Constructor of the class
   *
   * @param context {@link android.content.Context}.
   * @param userEntityJsonMapper {@link UserEntityJsonMapper}.
   */
  public RestApiImpl(Context context, UserEntityJsonMapper userEntityJsonMapper) {
    if (context == null || userEntityJsonMapper == null) {
      throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
    }
    this.context = context.getApplicationContext();
    this.userEntityJsonMapper = userEntityJsonMapper;
  }

  @Override public Observable<UserEntity> user() {
    return Observable.create(emitter -> {
      if (isThereInternetConnection()) {
        try {
          String responseUserDetails = getUserFromApi();
          if (responseUserDetails != null) {
            emitter.onNext(userEntityJsonMapper.transformUserEntity(responseUserDetails));
            emitter.onComplete();
          } else {
            emitter.onError(new NetworkConnectionException());
          }
        } catch (Exception e) {
          emitter.onError(new NetworkConnectionException(e.getCause()));
        }
      } else {
        emitter.onError(new NetworkConnectionException());
      }
    });
  }

  private String getUserFromApi() throws MalformedURLException {
    String apiUrl = API_URL_GET_USER  + "?access_token=18754436.974bc37.cbfa1756cf6c444db653af92a2771647";
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
