package com.javiertarazaga.instasearch.domain.exception.preferences;

import com.javiertarazaga.instasearch.domain.repository.PreferencesRepository;

/**
 * Base exception class for {@link PreferencesRepository} exceptions.
 */
public class PreferenceException extends RuntimeException {

    PreferenceException() {
    }

    PreferenceException(Throwable throwable) {
        super(throwable);
    }
}
