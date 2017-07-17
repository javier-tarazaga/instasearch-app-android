/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
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
package com.javiertarazaga.instasearch.domain.interactor;

import com.javiertarazaga.instasearch.domain.User;
import com.javiertarazaga.instasearch.domain.exception.user.InvalidPasswordException;
import com.javiertarazaga.instasearch.domain.exception.user.InvalidUserNameException;
import com.javiertarazaga.instasearch.domain.exception.user.UserException;
import com.javiertarazaga.instasearch.domain.executor.PostExecutionThread;
import com.javiertarazaga.instasearch.domain.executor.ThreadExecutor;
import com.javiertarazaga.instasearch.domain.repository.UserRepository;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * login the {@link User} using username and password.
 */
public class UserLogin extends UseCase<User, Void> {

  private final UserRepository userRepository;
  private String username;
  private String password;

  @Inject UserLogin(UserRepository userRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.userRepository = userRepository;
  }

  /**
   * Initializes the interactor with the username and password to use for the authentication.
   *
   * @param username - username for the user
   * @param password - password for the user.
   */
  public UserLogin init(String username, String password) {
    this.username = username;
    this.password = password;

    return this;
  }

  /**
   * Builds the {@link UseCase} observable @return an {@link} Observable that will emit the logged in {@link User}.
   * The UseCase will through the following exceptions in case something went wrong:
   * <ul>
   * <li>{@link InvalidUserNameException} if the email it is either empty or not valid.
   * <li>{@link InvalidPasswordException} if the password is either empty or not valid</li>
   * <li>{@link UserException} if an unknown error occurs</li>
   * </ul>
   */
  @Override Observable<User> buildUseCaseObservable(Void unused) {
    return this.userRepository.login(this.username, this.password);
  }

  //private Observable validate() {
  //  return Observable.create(new Single.OnSubscribe<Object>() {
  //    @Override public void call(Subscriber<? super Object> subscriber) {
  //      if (UserLogin.this.username.isEmpty()) {
  //        subscriber.onError(new InvalidUserNameException());
  //      } else if (UserLogin.this.password.isEmpty()) {
  //        subscriber.onError(new InvalidPasswordException());
  //      } else {
  //        subscriber.onCompleted();
  //      }
  //    }
  //  });
  //}
}
