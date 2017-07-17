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
package com.javiertarazaga.instasearch.data.repository.preferences.datasource;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link PreferencesDataStore}.
 */
@Singleton public class PreferencesDataStoreFactory {

  private final Context context;
  private final SharedPreferences sharedPreferences;

  @Inject PreferencesDataStoreFactory(@NonNull Context context,
      @NonNull SharedPreferences sharedPreferences) {
    this.context = context.getApplicationContext();
    this.sharedPreferences = sharedPreferences;
  }

  /**
   * Create {@link PreferencesDataStore}.
   */
  public PreferencesDataStore create() {
    return createSharedPreferencesDataStore();
  }

  /**
   * Create {@link PreferencesDataStore} to retrieve data from the Shared Preferences.
   */
  public PreferencesDataStore createSharedPreferencesDataStore() {
    return new SharedPreferencesDataStore(this.sharedPreferences);
  }
}
