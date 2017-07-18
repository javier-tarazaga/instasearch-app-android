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
package com.javiertarazaga.instasearch.presentation.internal.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import com.javiertarazaga.instasearch.data.cache.UserCache;
import com.javiertarazaga.instasearch.data.cache.UserMemoryCacheImpl;
import com.javiertarazaga.instasearch.data.executor.JobExecutor;
import com.javiertarazaga.instasearch.data.prefs.StringPreference;
import com.javiertarazaga.instasearch.data.repository.preferences.PreferencesDataRepository;
import com.javiertarazaga.instasearch.data.repository.user.UserDataRepository;
import com.javiertarazaga.instasearch.domain.executor.PostExecutionThread;
import com.javiertarazaga.instasearch.domain.executor.ThreadExecutor;
import com.javiertarazaga.instasearch.domain.repository.PreferencesRepository;
import com.javiertarazaga.instasearch.domain.repository.UserRepository;
import com.javiertarazaga.instasearch.presentation.AndroidApplication;
import com.javiertarazaga.instasearch.presentation.UIThread;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import static android.content.Context.MODE_PRIVATE;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module public class ApplicationModule {
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides @Singleton SharedPreferences provideSharedPreferences() {
    return this.application.getSharedPreferences("Instasearch", MODE_PRIVATE);
  }

  @Provides @Singleton UserCache provideUserCache(UserMemoryCacheImpl userCache) {
    return userCache;
  }

  @Provides @Singleton UserRepository provideUserRepository(UserDataRepository userDataRepository) {
    return userDataRepository;
  }

  @Provides @Singleton PreferencesRepository providesPreferencesRepository(
      PreferencesDataRepository preferencesDataRepository) {
    return preferencesDataRepository;
  }

  @Provides @Singleton StringPreference providesAuthToken(
      SharedPreferences preferences) {
    return new StringPreference(preferences, "auth_token");
  }
}
