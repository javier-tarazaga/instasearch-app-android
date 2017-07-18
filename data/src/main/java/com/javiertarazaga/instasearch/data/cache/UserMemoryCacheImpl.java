package com.javiertarazaga.instasearch.data.cache;

import com.javiertarazaga.instasearch.data.entity.UserEntity;
import com.javiertarazaga.instasearch.domain.User;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Memory cache for {@link User}.
 */
@Singleton
public class UserMemoryCacheImpl implements UserCache {

  private UserEntity cachedItem;

  @Inject UserMemoryCacheImpl() {
  }

  /**
   * {@inheritDoc}
   */
  @Override public Observable<UserEntity> get() {
    return Observable.just(cachedItem);
  }

  /**
   * {@inheritDoc}
   */
  @Override public void put(UserEntity item) {
    cachedItem = item;
  }

  /**
   * {@inheritDoc}
   */
  @Override public boolean isCached() {
    return cachedItem != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override public boolean isExpired() {
    return cachedItem != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override public void evictAll() {
    cachedItem = null;
  }
}
