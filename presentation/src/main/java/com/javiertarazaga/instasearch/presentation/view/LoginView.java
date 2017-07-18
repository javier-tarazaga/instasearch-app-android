package com.javiertarazaga.instasearch.presentation.view;

import android.content.Context;

/**
 * Interface representing the login view.
 */
public interface LoginView extends LoadDataView {

  void loadUrl(String url);

  /**
   * The login was completed successfully.
   */
  void loginSuccessful();

  /**
   * Show an error message
   *
   * @param message A string representing an error.
   */
  void showError(String message);

  /**
   * Get a {@link android.content.Context}.
   */
  Context context();
}
