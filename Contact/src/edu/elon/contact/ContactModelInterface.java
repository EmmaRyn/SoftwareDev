/**
 * ContactModelInterface.java 1.0 Nov 24, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.contact;

import java.sql.SQLException;

/**
 * The Model interface that works connections and traversing contacts
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public interface ContactModelInterface {
  
  /**
   * Adds item to database and contacts list
   * 
   * @param first String first name
   * @param middle String middle name
   * @param last String last name
   * @param email String email
   * @param major String major
   * @throws SQLException
   */
  void addItem(String first, String middle, String last, String email, String major) throws SQLException;
  
  /**
   * Removes item from database and contacts list
   * 
   * @throws SQLException
   */
  void removeItem() throws SQLException;
  
  /**
   * Updates current contact in database and in contacts list
   * 
   * @param first String first name
   * @param middle String middle name
   * @param last String last name
   * @param email String email
   * @param major String major
   * @param id int primary key of contact
   * @throws SQLException
   */
  void updateItem(String first, String middle, String last, String email, String major, int id) throws SQLException;
  
  /**
   * Empties database table and contacts list
   * 
   * @throws SQLException
   */
  void clearDB() throws SQLException;
  
  /**
   * Increases index to get next contact in list
   */
  void next();
  
  /**
   * Decreases index to get previous contact in list
   */
  void previous();
  
  /**
   * Gets current Contact information
   * 
   * @return Contact contact information
   */
  Contact getContact();

  /**
   * Gets current index
   * 
   * @return int current index in contacts list
   */
  int getIndex();
  
  /**
   * Returns true if there is another contact after the current one
   * 
   * @return boolean hasNext
   */
  boolean getHasNext();
  
  /**
   * Returns true if there is another contact before the current one
   * 
   * @return boolean hasPrev
   */
  boolean getHasPrev();
  
  /**
   * Sets true if there is another contact after the current one
   */
  void setHasNext();
  
  /**
   * Sets true if there is another contact before the current one
   */
  void setHasPrev();
  
  /**
   * Adds observer to observers ArrayList
   * 
   * @param o ModelObserver
   */
  void registerObserver(ModelObserver o);
  
  /**
   * Removes observer from observers ArrayList
   * 
   * @param o ModelObserver
   */
  void removeObserver(ModelObserver o);
  
  /**
   * Calls updateFields() method in observers when information changes
   */
  void notifyObservers();

  /**
   * Creates the connection and contacts list
   * 
   * @param aUser String database username
   * @param aPass String database password
   * @param aServer String database ip address
   * @param aName String database name
   * @param aTable String database table name
   * @throws SQLException 
   */
  void initialize(String aUser, String aPass, String aServer, String aName,
          String aTable) throws SQLException;

  /**
   * Sets contact information
   * 
   * @param aContact a contact
   */
  void setContact(Contact aContact);

}
