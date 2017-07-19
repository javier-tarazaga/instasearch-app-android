package com.javiertarazaga.instasearch.domain.exception.user;

/**
 * {@link UserException} indicating that there was a problem while trying to logout
 */
public class LogoutNotPossibleException extends UserException {

    public LogoutNotPossibleException() {
        super();
    }

    public LogoutNotPossibleException(Throwable throwable) {
        super(throwable);
    }
}
