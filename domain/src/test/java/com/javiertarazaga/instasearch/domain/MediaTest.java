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
package com.javiertarazaga.instasearch.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MediaTest {

  private static final String FAKE_MEDIA_ID = "123";

  private Media media;

  @Before
  public void setUp() {
    media = new Media(FAKE_MEDIA_ID);
  }

  @Test
  public void testMediaConstructorHappyCase() {
    final String mediaId = media.getMediaId();

    assertThat(mediaId).isEqualTo(FAKE_MEDIA_ID);
  }
}
