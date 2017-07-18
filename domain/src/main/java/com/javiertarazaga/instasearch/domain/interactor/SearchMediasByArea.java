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

import com.fernandocejas.arrow.checks.Preconditions;
import com.javiertarazaga.instasearch.domain.Media;
import com.javiertarazaga.instasearch.domain.executor.PostExecutionThread;
import com.javiertarazaga.instasearch.domain.executor.ThreadExecutor;
import com.javiertarazaga.instasearch.domain.repository.MediaRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

/**
 * Interactor which will perform a search for {@link List<Media>} in a given area. This area will be
 * determined by the lat,lng passed as well as the maxDistance to be used as radius.
 */
public class SearchMediasByArea extends UseCase<List<Media>, SearchMediasByArea.Params> {

  private final MediaRepository mediaRepository;

  @Inject SearchMediasByArea(MediaRepository mediaRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.mediaRepository = mediaRepository;
  }

  @Override Observable<List<Media>> buildUseCaseObservable(Params params) {
    Preconditions.checkNotNull(params);
    return this.mediaRepository.searchByArea(params.lat, params.lng, params.maxDistance);
  }

  public static final class Params {
    private final double lat;
    private final double lng;
    private final int maxDistance;

    private Params(double lat, double lng, int maxDistance) {
      this.lat = lat;
      this.lng = lng;
      this.maxDistance = maxDistance;
    }

    public static Params forArea(double lat, double lng, int maxDistance) {
      return new Params(lat, lng, maxDistance);
    }
  }

}
