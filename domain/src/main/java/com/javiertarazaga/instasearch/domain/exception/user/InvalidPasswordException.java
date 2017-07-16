package com.javiertarazaga.instasearch.domain.exception.user;

/**
 * Exception thrown when the password used for login is not valid
 **/
public class InvalidPasswordException extends UserException {

    public InvalidPasswordException() {
    }

    public InvalidPasswordException(Throwable cause) {
        super(cause);
    }
}
