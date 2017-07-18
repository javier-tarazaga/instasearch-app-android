package com.javiertarazaga.instasearch.domain.exception.user;

/**
 * {@link UserException} indicating that the user needs to authenticate using his/her credentials. In order to log in.
 */
public class UserNeedsAuthenticationException extends UserException {

    public UserNeedsAuthenticationException() {
        super();
    }

    public UserNeedsAuthenticationException(Throwable throwable) {
        super(throwable);
    }
}
