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
package com.javiertarazaga.instasearch.data.repository.media.datasource;

import com.javiertarazaga.instasearch.data.net.RestApi;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class) public class MediaCloudDataStoreTest {

  private static final double FAKE_LAT = 0.0;
  private static final double FAKE_LNG = 3.0;
  private static final int FAKE_MAX_DISTANCE = 1000;

  private MediaCloudDataStore mediaCloudDataStore;

  @Mock private RestApi mockRestApi;

  @Before public void setUp() {
    mediaCloudDataStore = new MediaCloudDataStore(mockRestApi);
  }

  @Test public void testGetMediaEntityListFromApi() {
    mediaCloudDataStore.searchByArea(FAKE_LAT, FAKE_LNG, FAKE_MAX_DISTANCE);
    verify(mockRestApi).searchMediaByArea(FAKE_LAT, FAKE_LNG, FAKE_MAX_DISTANCE);
  }
}
