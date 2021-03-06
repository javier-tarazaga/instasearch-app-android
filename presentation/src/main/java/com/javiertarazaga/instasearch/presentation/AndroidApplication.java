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
package com.javiertarazaga.instasearch.presentation;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.javiertarazaga.instasearch.presentation.internal.di.components.ApplicationComponent;
import com.javiertarazaga.instasearch.presentation.internal.di.components.DaggerApplicationComponent;
import com.javiertarazaga.instasearch.presentation.internal.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;
import io.fabric.sdk.android.Fabric;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application {

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();

    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }

    this.initializeCrashReporting();
    this.initializeInjector();
    this.initializeLeakDetection();
  }

  private void initializeCrashReporting() {
    Fabric.with(this, new Crashlytics());
  }

  private void initializeInjector() {
    this.applicationComponent = DaggerApplicationComponent.builder()
                                                          .applicationModule(new ApplicationModule(this))
                                                          .build();
  }

  public ApplicationComponent getApplicationComponent() {
    return this.applicationComponent;
  }

  private void initializeLeakDetection() {
    LeakCanary.install(this);
  }
}
