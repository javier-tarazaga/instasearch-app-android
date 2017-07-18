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
package com.javiertarazaga.instasearch.presentation.internal.di.components;

import com.javiertarazaga.instasearch.presentation.internal.di.PerActivity;
import com.javiertarazaga.instasearch.presentation.internal.di.modules.ActivityModule;
import com.javiertarazaga.instasearch.presentation.internal.di.modules.PreferencesModule;
import com.javiertarazaga.instasearch.presentation.internal.di.modules.UserModule;
import com.javiertarazaga.instasearch.presentation.view.fragment.MediaListFragment;
import com.javiertarazaga.instasearch.presentation.view.fragment.PreferencesFragment;
import com.javiertarazaga.instasearch.presentation.view.fragment.SplashScreenFragment;
import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class,
    PreferencesModule.class})
public interface MainComponent extends ActivityComponent {
  void inject(MediaListFragment mediaListFragment);
  void inject(PreferencesFragment preferencesFragment);
  void inject(SplashScreenFragment splashScreenFragment);
}
