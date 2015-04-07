/**
 * ContactModel.java 1.0 Nov 24, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.contact;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Connects the database to application and performs queries
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public class ContactModel implements ContactModelInterface {

  private static final String DB_URL = "jdbc:mysql://us-cdbr-cb-east-01.cleardb.net:3306/cb_contactdjp";
  private Statement statement = null;
  private Connection connection = null;
  private ResultSet rs = null;

  private String dbURL;
  private String dbUser;
  private String dbPass;
  private String dbServer;
  private String dbName;
  private String tableName;
  private boolean isConnected;
  private ArrayList<Contact> contacts;
  private int index;
  private Contact contact;
  private boolean hasNext = false;
  private boolean hasPrev = false;

  private ArrayList<ModelObserver> observers = new ArrayList<ModelObserver>();

  /**
   * Model Constructor
   */
  public ContactModel(){
    contacts = new ArrayList<Contact>();
    setConnected(false);
    index = 0;
    setContact(null);
  }

  @Override
  public void initialize(String user, String pass, String server, String name, String table) throws SQLException{
    dbURL = DB_URL;
    dbUser = user;
    dbPass = pass;
    dbServer = server;
    dbName = name;
    tableName = table;
    getConnection();
    createList();
    setContact(contacts.get(0));
    if (contacts.size() > 0){
      hasNext = true;
    }
  }
 
  /**
   * Creates connection between database and application
   */
  public void getConnection(){
    dbURL = getDbURL();
    dbUser = getDbUser();
    dbPass = getDbPass();
    dbServer = getDbServer();
    dbName = getDbName();
    try {
      connection = DriverManager.getConnection(DB_URL, dbUser, dbPass);
      setConnected(true);
    } catch (SQLException e) {
      JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(frame, "You did not correctly specify db parameters.", "DB Settings", JOptionPane.ERROR_MESSAGE);
      setConnected(false);
    }
  }

  /**
   * Creates ArrayList of contacts
   * 
   * @return ArrayList<ContactInfo> array list of ContactInfo items
   * @throws SQLException
   */
  public void createList() throws SQLException {
    try {
      if (connection == null){
        getConnection();
      } else {
        if (!contacts.isEmpty()){
          contacts.clear();
        }
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = statement.executeQuery("SELECT * FROM " + tableName + ";");
        while (rs.next() == true){
          if (!rs.getString("FirstName").equals(null)){
            String firstName = rs.getString("FirstName");
            String middleName = rs.getString("MiddleName");
            String lastName = rs.getString("LastName");
            String email = rs.getString("Email");
            String major = rs.getString("Major");
            int id = rs.getInt("ID");
            contacts.add(new Contact(firstName, middleName, lastName, email, major, id));
          }
        }
      }
    } catch (SQLException e) {
    } finally {
      if (connection != null){
        statement.close();
        rs.close();
      }
    }
  }

  @Override
  public void clearDB() throws SQLException {
    try {
      statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      statement.executeUpdate("TRUNCATE " + tableName + ";");
      contacts.clear();
    } catch (SQLException e) {
      System.err.println(e);
    } finally {
      if (connection != null){
        statement.close();
      }
    }
  }

  @Override
  public void addItem(String firstName, String middleName, 
          String lastName, String email, String major) throws SQLException {
    try {
      statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      statement.executeUpdate("INSERT INTO " + dbName + "." + tableName + " VALUES ('" + firstName + "', '" + middleName + "', '" + 
              lastName + "', '" + email + "', '" + major + "', NULL);");
      createList();
      setContact(contacts.get(index));
    } catch (SQLException e) {
      System.err.println(e);
    } finally {
      if (connection != null){
        statement.close();
        rs.close();
      }
    }
  }

  @Override
  public void removeItem() throws SQLException {
    try {
      int id = (index * 10) + 1;
      statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      statement.executeUpdate("DELETE FROM " + dbName + "." + tableName + " WHERE ID=" + id + ";");
      contacts.remove(index);
      if (index > contacts.size() - 1){
        index--;
      }
      setContact(contacts.get(index));
    } catch (SQLException e) {
      System.err.println(e);
    } finally {
      if (connection != null){
        statement.close();
      }
    }
  }

  @Override
  public void updateItem(String firstName, String middleName, String lastName, String email, String major, int id) throws SQLException {
    try {
      statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      statement.executeUpdate("UPDATE " + tableName + " SET FirstName='" + firstName + "', MiddleName='" + middleName + "', LastName='" + lastName +"', Email='" + email + "', Major='" + major + "', ID=" + id + " WHERE ID=" + id + ";");
      Contact c = contacts.get(index);
      c.setFirst(firstName);
      c.setMiddle(middleName);
      c.setLast(lastName);
      c.setEmail(email);
      c.setMajor(major);
    } catch (SQLException e) {
      System.err.println(e);
    } finally {
      if (connection != null){
        statement.close();
      }
      setContact(contacts.get(index));
    }
  }

  @Override
  public void next(){
    if (getHasNext() == true){
      index++;
    }
    setContact(contacts.get(index));
  }

  @Override
  public void previous(){
    if (getHasPrev() == true){
      index--;
    }
    setContact(contacts.get(index));
  }

  /**
   * Gets database URL
   * 
   * @return String database URL
   */
  public String getDbURL() {
    return dbURL;
  }

  /**
   * Gets Database Username
   * 
   * @return String database UserName
   */
  public String getDbUser() {
    return dbUser;
  }

  /**
   * Gets Database Password
   * 
   * @return String database password
   */
  public String getDbPass() {
    return dbPass;
  }

  /**
   * Gets Database IP Address/Server
   * 
   * @return String ip address
   */
  public String getDbServer() {
    return dbServer;
  }

  /**
   * Gets Database Name
   * 
   * @return String database name
   */
  public String getDbName() {
    return dbName;
  }

  /**
   * Returns true if database is conntected
   * 
   * @return boolean isConnected
   */
  public boolean isConnected() {
    return isConnected;
  }

  /**
   * Sets isConnected to true or false
   * 
   * @param isConnected boolean
   */
  public void setConnected(boolean isConnected) {
    this.isConnected = isConnected;
  }

  @Override
  public Contact getContact() {
    return contact;
  }

  @Override
  public void setContact(Contact aContact) {
    contact = aContact;
    notifyObservers();
  }

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public void registerObserver(ModelObserver aO) {
    observers.add(aO);
  }

  @Override
  public void removeObserver(ModelObserver aO) {
    observers.remove(aO);
  }

  @Override
  public void notifyObservers() {
    for (Object o : observers){
      ModelObserver eo = (ModelObserver) o;
      eo.updateFields();
    }
  }
  
  @Override
  public boolean getHasNext(){
    setHasNext();
    return hasNext;
  }

  @Override
  public boolean getHasPrev(){
    setHasPrev();
    return hasPrev;
  }

  @Override
  public void setHasNext() {
    if (index < contacts.size() - 1){
      hasNext = true;
    } else {
      hasNext = false;
    } 
  }

  @Override
  public void setHasPrev() {
    if (index > 1){
      hasPrev = true;
    } else {
      hasPrev = false;
    } 
  }
}
