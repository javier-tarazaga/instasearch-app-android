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
package com.javiertarazaga.instasearch.test.mapper;

import com.javiertarazaga.instasearch.domain.Media;
import com.javiertarazaga.instasearch.presentation.mapper.CaptionModelDataMapper;
import com.javiertarazaga.instasearch.presentation.mapper.ImagesModelDataMapper;
import com.javiertarazaga.instasearch.presentation.mapper.MediaModelDataMapper;
import com.javiertarazaga.instasearch.presentation.mapper.UserModelDataMapper;
import com.javiertarazaga.instasearch.presentation.model.MediaModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_BIO;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_CAPTION_ID;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_CAPTION_TEXT;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_FULLNAME;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_HEIGHT;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_PROFILE_PICTURE;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_URL;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_USERNAME;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_USER_ID;
import static com.javiertarazaga.instasearch.test.mapper.ModelMapperHelper.FAKE_WIDTH;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class) public class MediaEntityDataMapperTest {

  private MediaModelDataMapper mediaModelDataMapper;

  @Before public void setUp() throws Exception {
    mediaModelDataMapper =
        new MediaModelDataMapper(new UserModelDataMapper(), new ImagesModelDataMapper(),
            new CaptionModelDataMapper());
  }

  @Test public void testTransformMediaEntity() {
    Media media = ModelMapperHelper.createFakeMedia();
    MediaModel mediaModel = mediaModelDataMapper.transform(media);

    assertThat(mediaModel, is(instanceOf(MediaModel.class)));
    assertThat(mediaModel.getMediaId(), is(FAKE_USER_ID));
    assertThat(mediaModel.getCaption().getCaptionId(), is(FAKE_CAPTION_ID));
    assertThat(mediaModel.getCaption().getText(), is(FAKE_CAPTION_TEXT));
    assertThat(mediaModel.getImages().getLowResolution().getHeight(), is(FAKE_HEIGHT));
    assertThat(mediaModel.getImages().getLowResolution().getWidth(), is(FAKE_WIDTH));
    assertThat(mediaModel.getImages().getLowResolution().getUrl(), is(FAKE_URL));
    assertThat(mediaModel.getUser().getUserId(), is(FAKE_USER_ID));
    assertThat(mediaModel.getUser().getBio(), is(FAKE_BIO));
    assertThat(mediaModel.getUser().getProfilePicture(), is(FAKE_PROFILE_PICTURE));
    assertThat(mediaModel.getUser().getUsername(), is(FAKE_USERNAME));
    assertThat(mediaModel.getUser().getfullname(), is(FAKE_FULLNAME));
  }

  @Test public void testTransformMediaEntityCollection() {
    Media mockMediaOne = ModelMapperHelper.createFakeMedia();
    Media mockMediaTwo = ModelMapperHelper.createFakeMedia();

    List<Media> mediaList = new ArrayList<>(5);
    mediaList.add(mockMediaOne);
    mediaList.add(mockMediaTwo);

    Collection<MediaModel> mediaCollection = mediaModelDataMapper.transform(mediaList);

    assertThat(mediaCollection.toArray()[0], is(instanceOf(MediaModel.class)));
    assertThat(mediaCollection.toArray()[1], is(instanceOf(MediaModel.class)));
    assertThat(mediaCollection.size(), is(2));
  }
}
