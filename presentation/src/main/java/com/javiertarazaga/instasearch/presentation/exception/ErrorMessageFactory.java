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
package com.javiertarazaga.instasearch.presentation.exception;

import android.content.Context;
import com.javiertarazaga.instasearch.data.exception.NetworkConnectionException;
import com.javiertarazaga.instasearch.data.exception.UserNotFoundException;
import com.javiertarazaga.instasearch.domain.exception.user.InstagramAuthErrorException;
import com.javiertarazaga.instasearch.domain.exception.user.LogoutNotPossibleException;
import com.javiertarazaga.instasearch.presentation.R;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

  private ErrorMessageFactory() {
    //empty
  }

  /**
   * Creates a String representing an error message.
   *
   * @param context Context needed to retrieve string resources.
   * @param exception An exception used as a condition to retrieve the correct error message.
   * @return {@link String} an error message.
   */
  public static String create(Context context, Exception exception) {
    String message = context.getString(R.string.exception_message_generic);

    if (exception instanceof NetworkConnectionException) {
      message = context.getString(R.string.exception_message_no_connection);
    } else if (exception instanceof UserNotFoundException) {
      message = context.getString(R.string.exception_message_user_not_found);
    } else if (exception instanceof InstagramAuthErrorException) {
      message = context.getString(R.string.exception_message_user_not_found);
    } else if (exception instanceof LogoutNotPossibleException) {
      message = context.getString(R.string.exception_message_logout_not_possible);
    }

    return message;
  }
}
