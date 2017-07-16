package com.javiertarazaga.instasearch.domain.exception.user;

/**
 * Exception thrown when an username does not conform to what we expect to be valid
 */
public class InvalidUserNameException extends UserException {

    public InvalidUserNameException() {
    }

    public InvalidUserNameException(Throwable cause) {
        super(cause);
    }
}
