package com.javiertarazaga.instasearch.presentation.view;

/**
 * Interface representing the preferences view.
 */
public interface PreferencesView {

  /**
   * Update the distance view formatted in Kilometers
   *
   * @param distanceInKm Distance in Kilometers
   */
  void setDistanceInKm(float distanceInKm);

  /**
   * Update the distance view formatted in Meters
   *
   * @param distanceInM Distance in Meters
   */
  void setDistanceInM(int distanceInM);

  /**
   * Simple method to update the selected distance with what ever the user did set before
   *
   * @param distance The distance selected by the user
   */
  void updateDistance(int distance);

  /**
   * The logout was completed successfully.
   */
  void logoutSuccessful();
}
