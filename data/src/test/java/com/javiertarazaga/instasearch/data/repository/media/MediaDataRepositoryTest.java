package com.javiertarazaga.instasearch.data.repository.media;

import com.javiertarazaga.instasearch.data.entity.MediaEntity;
import com.javiertarazaga.instasearch.data.entity.mapper.MediaEntityDataMapper;
import com.javiertarazaga.instasearch.data.repository.media.datasource.MediaDataStore;
import com.javiertarazaga.instasearch.data.repository.media.datasource.MediaDataStoreFactory;
import com.javiertarazaga.instasearch.domain.Media;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class) public class MediaDataRepositoryTest {

  private static final double FAKE_LAT = 0.0;
  private static final double FAKE_LNG = 3.0;
  private static final int FAKE_MAX_DISTANCE = 1000;

  private MediaDataRepository mediaDataRepository;

  @Mock private MediaDataStoreFactory mockMediaDataStoreFactory;
  @Mock private MediaEntityDataMapper mockMediaEntityDataMapper;
  @Mock private MediaDataStore mockMediaDataStore;
  @Mock private MediaEntity mockMediaEntity;
  @Mock private Media mockMedia;

  @Before public void setUp() {
    mediaDataRepository =
        new MediaDataRepository(mockMediaDataStoreFactory, mockMediaEntityDataMapper);
    given(mockMediaDataStoreFactory.create()).willReturn(mockMediaDataStore);
    given(mockMediaDataStoreFactory.createCloudDataStore()).willReturn(mockMediaDataStore);
  }

  @Test public void testSearchMediaByAreaHappyCase() {
    List<MediaEntity> mediasList = new ArrayList<>();
    mediasList.add(new MediaEntity());
    given(mockMediaDataStore.searchByArea(FAKE_LAT, FAKE_LNG, FAKE_MAX_DISTANCE)).willReturn(
        Observable.just(mediasList));

    mediaDataRepository.searchByArea(FAKE_LAT, FAKE_LNG, FAKE_MAX_DISTANCE);

    verify(mockMediaDataStoreFactory).create();
    verify(mockMediaDataStore).searchByArea(FAKE_LAT, FAKE_LNG, FAKE_MAX_DISTANCE);
  }
}
