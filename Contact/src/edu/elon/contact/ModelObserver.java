/**
 * ModelObserver.java 1.0 Nov 25, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.contact;

/**
 * An Observer class for views that observes the model
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public interface ModelObserver {
  
  /**
   * Updates text fields that change
   */
  public void updateFields();

}
