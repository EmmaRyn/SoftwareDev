/**
 * ContactApplication.java 1.0 Nov 24, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.contact;

/**
 * Starts the application
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public class ContactApplication {

  /**
   * Creates instance of model and controller interfaces and begins application
   * 
   * @param args String[] arguments
   */
  public static void main(String[] args){
    ContactModelInterface model = new ContactModel();
    ContactControllerInterface controller = new ContactController(model);
  }
}
