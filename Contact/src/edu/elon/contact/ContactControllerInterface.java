/**
 * ContactControllerInterface.java 1.0 Nov 24, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.contact;

/**
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public interface ContactControllerInterface {

  /**
   * Removes contact from list and database
   */
  void remove();

  /**
   * Updates contact in list and database
   */
  void update();

  /**
   * Exits gui and closes application
   */
  void exit();

  /**
   * Clears database and contacts list
   */
  void clearDB();

  
  /**
   * Adds contact to database and contacts list
   * 
   * @param first String first name
   * @param middle String middle name
   * @param last String last name
   * @param email String email
   * @param major String major
   */
  void ok(String first, String middle, String last, String email, String major);

  /**
   * Gets next contact
   */
  void next();

  /**
   * Gets previous contact
   */
  void previous();
  
  /**
   * Creates connection to database
   * 
   * @param user String database username
   * @param pass String database password
   * @param server String database ip address
   * @param dbname String database name
   * @param table String table name
   */
  void connectToDB(String user, String pass, String server, String dbname, String table);

}
