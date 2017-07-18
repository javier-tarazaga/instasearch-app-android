package com.javiertarazaga.instasearch.domain.exception.user;

import com.javiertarazaga.instasearch.domain.repository.UserRepository;
import org.jetbrains.annotations.NonNls;

/**
 * Base exception class for {@link UserRepository} exceptions.
 */
public class UserException extends RuntimeException {

    public UserException() {
    }

    public UserException(Throwable throwable) {
        super(throwable);
    }

    public UserException(@NonNls String message) {
        super(message);
    }
}
