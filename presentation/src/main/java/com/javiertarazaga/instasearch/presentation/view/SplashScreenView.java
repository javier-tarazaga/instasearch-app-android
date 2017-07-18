package com.javiertarazaga.instasearch.presentation.view;

/**
 * Interface representing the Splash Screen view.
 */
public interface SplashScreenView extends LoadDataView {

  /**
   * Transition the user to the login screen.
   */
  void goToLoginView();

  /**
   * Transition the user to the main screen of the app.
   */
  void goToMainView();
}
