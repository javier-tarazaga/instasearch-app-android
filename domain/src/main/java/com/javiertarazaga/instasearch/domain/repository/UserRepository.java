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
package com.javiertarazaga.instasearch.domain.repository;

import com.javiertarazaga.instasearch.domain.User;
import com.javiertarazaga.instasearch.domain.exception.user.UserNeedsAuthenticationException;
import io.reactivex.Observable;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 */
public interface UserRepository {

  /**
   * Get an {@link Observable} which will emit a {@link User}.
   *
   * @param userName The user name used to perform the login with
   * @param password The user password used to perform the login with.
   */
  Observable<User> login(final String userName, final String password);

  /**
   * Get and observable that will emit a {@link User} or a {@link UserNeedsAuthenticationException}
   * if the last token session is invalid.
   *
   * @return {@link Observable}&lt;{@link User}&gt;
   */
  Observable<User> user();
}
