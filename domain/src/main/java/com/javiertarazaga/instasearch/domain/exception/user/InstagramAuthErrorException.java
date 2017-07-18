package com.javiertarazaga.instasearch.domain.exception.user;

import org.jetbrains.annotations.NonNls;

/**
 * {@link UserException} indicating that there was a problem while login into instagram.
 */
public class InstagramAuthErrorException extends UserException {

    public InstagramAuthErrorException() {
        super();
    }

    public InstagramAuthErrorException(Throwable throwable) {
        super(throwable);
    }

    public InstagramAuthErrorException(@NonNls String message) {
        super(message);
    }
}
