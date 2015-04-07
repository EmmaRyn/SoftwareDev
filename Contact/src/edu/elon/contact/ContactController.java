/**
 * ContactController.java 1.0 Nov 24, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.contact;

import java.sql.SQLException;

/**
 * Controls communication between view and model classes
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public class ContactController implements ContactControllerInterface {
  
  ContactModelInterface model;
  private Contact contact;
  ContactView view;

  /**
   * ContactController constructor
   * 
   * @param aModel ContactModelInterface instance
   */
  public ContactController(ContactModelInterface aModel) {
    setContact(null);
    model = aModel;

    view = new ContactView(this, model);
    view.createFrame();
    view.disableClear();
    view.disableAdd();
    view.disableRemove();
    view.disableUpdate();
    view.disableNext();
    view.disablePrevious();
  }

  /* (non-Javadoc)
   * @see edu.elon.contact.ContactControllerInterface#remove()
   */
  @Override
  public void remove() {
    try {
      model.removeItem();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /* (non-Javadoc)
   * @see edu.elon.contact.ContactControllerInterface#update()
   */
  @Override
  public void update() {
   Contact person = new Contact(view.getFirstName(), view.getMiddleName(), 
            view.getLastName(), view.getEmail(), view.getMajor(), model.getIndex());
    try {
      model.updateItem(person.getFirst(), person.getMiddle(), person.getLast(), 
              person.getEmail(), person.getMajor(), person.getId());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /* (non-Javadoc)
   * @see edu.elon.contact.ContactControllerInterface#exit()
   */
  @Override
  public void exit() {
    System.exit(0);
  }

  /* (non-Javadoc)
   * @see edu.elon.contact.ContactControllerInterface#clearDB()
   */
  @Override
  public void clearDB() {
    try {
      model.clearDB();
      view.setFirstName("");
      view.setMiddleName("");
      view.setLastName("");
      view.setEmail("");
      view.setMajor("");
      view.disableNext();
      view.disablePrevious();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /* (non-Javadoc)
   * @see edu.elon.contact.ContactControllerInterface#ok()
   */
  @Override
  public void ok(String first, String middle, String last, String email, String major) {
    try {
      model.addItem(first, middle, last, email, major);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /* (non-Javadoc)
   * @see edu.elon.contact.ContactControllerInterface#next()
   */
  @Override
  public void next() {
    if (model.getHasNext() == true){
      model.next();
      view.enableNext();
      view.enablePrevious();
    } else {
      view.disableNext();
    }
  }

  @Override
  public void previous() {
    if (model.getHasPrev() == true){
      model.previous();
      view.enablePrevious();
      view.enableNext();
    } else {
      view.disablePrevious();
    }
  }

  @Override
  public void connectToDB(String aUser, String aPass, String aServer, String aName, String aTable) {
    try {
      model.initialize(aUser, aPass, aServer, aName, aTable);
      view.enableAdd();
      view.enableRemove();
      view.enableUpdate();
      view.enableClear();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (model.getHasNext() == true){
      view.enableNext();
    }
  }

  /**
   * Gets current Contact
   * 
   * @return Contact
   */
  public Contact getContact() {
    return contact;
  }

  /**
   * Sets current Contact
   * 
   * @param aContact
   */
  public void setContact(Contact aContact) {
    contact = aContact;
  }
}
