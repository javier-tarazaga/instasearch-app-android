package com.javiertarazaga.instasearch.domain.exception.user;

import com.javiertarazaga.instasearch.domain.repository.UserRepository;

/**
 * Base exception class for {@link UserRepository} exceptions.
 */
public class UserException extends RuntimeException {

    UserException() {
    }

    UserException(Throwable throwable) {
        super(throwable);
    }
}
