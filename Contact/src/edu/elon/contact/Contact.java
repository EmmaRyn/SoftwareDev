/**
 * Contact.java 1.0 Nov 24, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.contact;

/**
 * Holds Contact Information
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public class Contact {
  
  private String first;
  private String middle;
  private String last;
  private String email;
  private String major;
  private int id;
  
  /**
   * Contact Constructor
   * 
   * @param first String first name
   * @param middle String middle name
   * @param last String last name
   * @param email String email
   * @param major String major
   * @param id int contact id
   */
  public Contact(String first, String middle, String last, String email, String major, int id){
    this.first = first;
    this.middle = middle;
    this.last = last;
    this.email = email;
    this.major = major;
    this.id = id;
  }

  /**
   * Gets first name
   * 
   * @return String first name
   */
  public String getFirst() {
    return first;
  }

  /**
   * Sets first name
   * 
   * @param aFirst String contact's first name
   */
  public void setFirst(String aFirst) {
    first = aFirst;
  }

  /**
   * Gets middle name
   * 
   * @return String middle name
   */
  public String getMiddle() {
    return middle;
  }

  /**
   * Sets middle name
   * 
   * @param aMiddle String contact's middle name
   */
  public void setMiddle(String aMiddle) {
    middle = aMiddle;
  }

  /**
   * Gets last name
   * 
   * @return String last name
   */
  public String getLast() {
    return last;
  }

  /**
   * Sets last name
   * 
   * @param aLast String contact's last name
   */
  public void setLast(String aLast) {
    last = aLast;
  }

  /**
   * Gets email
   * 
   * @return String email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets email
   * 
   * @param aEmail String contact's email
   */
  public void setEmail(String aEmail) {
    email = aEmail;
  }

  /**
   * Gets major
   * 
   * @return String major
   */
  public String getMajor() {
    return major;
  }

  /**
   * Sets major
   * 
   * @param aMajor String contact's major
   */
  public void setMajor(String aMajor) {
    major = aMajor;
  }

  /**
   * Gets id
   * 
   * @return int id
   */
  public int getId() {
    return id;
  }
}
