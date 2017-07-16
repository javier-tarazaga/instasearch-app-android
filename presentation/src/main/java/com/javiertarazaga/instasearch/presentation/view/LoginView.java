package com.javiertarazaga.instasearch.presentation.view;

/**
 * Interface representing the login view.
 */
public interface LoginView extends LoadDataView {

  /**
   * Render the invalid email error message
   *
   * @param errorMessage The error message to display
   */
  void showInvalidUserName(String errorMessage);

  /**
   * Render the invalid password error message
   *
   * @param errorMessage The error message to display
   */
  void showInvalidPassword(String errorMessage);

  /**
   * The login was completed successfully.
   */
  void loginSuccessful();
}
