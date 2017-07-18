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
package com.javiertarazaga.instasearch.domain.interactor;

import com.javiertarazaga.instasearch.domain.Media;
import com.javiertarazaga.instasearch.domain.executor.PostExecutionThread;
import com.javiertarazaga.instasearch.domain.executor.ThreadExecutor;
import com.javiertarazaga.instasearch.domain.repository.MediaRepository;
import com.javiertarazaga.instasearch.domain.repository.PreferencesRepository;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.List;
import javax.inject.Inject;

/**
 * Interactor which will perform a search for {@link List<Media>} in a given area. This area will be
 * determined by the lat,lng passed as well as the maxDistance to be used as radius.
 */
public class SearchMediasByArea extends UseCase<List<Media>, Void> {

  static final double LAT_HOME = 39.470574;
  static final double LNG_HOME = -0.365920;

  private final PreferencesRepository preferencesRepository;
  private final MediaRepository mediaRepository;

  @Inject SearchMediasByArea(PreferencesRepository preferencesRepository,
      MediaRepository mediaRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.preferencesRepository = preferencesRepository;
    this.mediaRepository = mediaRepository;
  }

  @Override Observable<List<Media>> buildUseCaseObservable(Void aVoid) {
    return this.preferencesRepository.getDistance()
        .flatMap(new Function<Integer, ObservableSource<List<Media>>>() {
          @Override public ObservableSource<List<Media>> apply(Integer maxDistance)
              throws Exception {
            return SearchMediasByArea.this.mediaRepository.searchByArea(LAT_HOME, LNG_HOME,
                maxDistance);
          }
        });
  }
}
