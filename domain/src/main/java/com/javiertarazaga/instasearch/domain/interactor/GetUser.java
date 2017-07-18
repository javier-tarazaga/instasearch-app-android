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

import com.javiertarazaga.instasearch.domain.User;
import com.javiertarazaga.instasearch.domain.exception.user.UserException;
import com.javiertarazaga.instasearch.domain.exception.user.UserNeedsAuthenticationException;
import com.javiertarazaga.instasearch.domain.executor.PostExecutionThread;
import com.javiertarazaga.instasearch.domain.executor.ThreadExecutor;
import com.javiertarazaga.instasearch.domain.repository.UserRepository;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * Interactor that checks if the user is authenticated. If the User has been logged in already it
 * will return the cached user. If there is no valid cache it will use the last sessions token if it
 * is stored to attempt to authenticate the user. In the case where the last session token is
 * invalid it will emit an {@link UserNeedsAuthenticationException}.
 * When an unknown error occurs it will emit an {@link UserException}
 */
public class GetUser extends UseCase<User, Void> {

  private final UserRepository userRepository;

  @Inject GetUser(UserRepository userRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.userRepository = userRepository;
  }

  @Override Observable<User> buildUseCaseObservable(Void unused) {
    return this.userRepository.user();
  }
}
