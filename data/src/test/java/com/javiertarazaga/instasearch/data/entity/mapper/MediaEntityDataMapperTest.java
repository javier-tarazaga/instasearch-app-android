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
package com.javiertarazaga.instasearch.data.entity.mapper;

import com.javiertarazaga.instasearch.data.entity.MediaEntity;
import com.javiertarazaga.instasearch.domain.Media;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_BIO;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_CAPTION_ID;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_CAPTION_TEXT;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_FULLNAME;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_HEIGHT;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_PROFILE_PICTURE;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_URL;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_USERNAME;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_USER_ID;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.FAKE_WIDTH;
import static com.javiertarazaga.instasearch.data.entity.mapper.MapperHelper.createFakeMediaEntity;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class) public class MediaEntityDataMapperTest {

  private MediaEntityDataMapper mediaEntityDataMapper;

  @Before public void setUp() throws Exception {
    mediaEntityDataMapper =
        new MediaEntityDataMapper(new UserEntityDataMapper(), new ImagesEntityDataMapper(),
            new CaptionEntityDataMapper());
  }

  @Test public void testTransformMediaEntity() {
    MediaEntity mediaEntity = createFakeMediaEntity();
    Media media = mediaEntityDataMapper.transform(mediaEntity);

    assertThat(media, is(instanceOf(Media.class)));
    assertThat(media.getMediaId(), is(FAKE_USER_ID));
    assertThat(media.getCaption().getCaptionId(), is(FAKE_CAPTION_ID));
    assertThat(media.getCaption().getText(), is(FAKE_CAPTION_TEXT));
    assertThat(media.getImages().getLowResolution().getHeight(), is(FAKE_HEIGHT));
    assertThat(media.getImages().getLowResolution().getWidth(), is(FAKE_WIDTH));
    assertThat(media.getImages().getLowResolution().getUrl(), is(FAKE_URL));
    assertThat(media.getUser().getUserId(), is(FAKE_USER_ID));
    assertThat(media.getUser().getBio(), is(FAKE_BIO));
    assertThat(media.getUser().getProfilePicture(), is(FAKE_PROFILE_PICTURE));
    assertThat(media.getUser().getUsername(), is(FAKE_USERNAME));
    assertThat(media.getUser().getFullname(), is(FAKE_FULLNAME));
  }

  @Test public void testTransformMediaEntityCollection() {
    MediaEntity mockMediaEntityOne = mock(MediaEntity.class);
    MediaEntity mockMediaEntityTwo = mock(MediaEntity.class);

    List<MediaEntity> mediaEntityList = new ArrayList<>(5);
    mediaEntityList.add(mockMediaEntityOne);
    mediaEntityList.add(mockMediaEntityTwo);

    Collection<Media> mediaCollection = mediaEntityDataMapper.transform(mediaEntityList);

    assertThat(mediaCollection.toArray()[0], is(instanceOf(Media.class)));
    assertThat(mediaCollection.toArray()[1], is(instanceOf(Media.class)));
    assertThat(mediaCollection.size(), is(2));
  }
}
