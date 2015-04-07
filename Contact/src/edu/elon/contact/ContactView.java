/**
 * ContactView.java 1.0 Nov 24, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.contact;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Creates GUIs and initiates connection
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public class ContactView extends JFrame implements ModelObserver {

  /**
   * Default serial ID
   */
  private static final long serialVersionUID = 1L;
  ContactControllerInterface controller;
  ContactModelInterface model;
  JButton nextButton = new JButton("Next");
  JButton prevButton = new JButton("Previous");

  private JMenuItem addItem = new JMenuItem("Add");
  private CardLayout cardLayout;
  private JMenuItem clearDBItem = new JMenuItem("Clear DB");
  private JMenuItem connectItem = new JMenuItem("Connect");
  private JTextField dbName = new JTextField("");
  private JMenu editMenu = new JMenu("Edit");
  private JTextField email = new JTextField("");
  private JTextField email2 = new JTextField("");
  private JMenuItem exitItem = new JMenuItem("Exit");
  private JMenu fileMenu = new JMenu("File");
  private JTextField firstName = new JTextField("");
  private JTextField firstName2 = new JTextField("");
  private JFrame frame = new JFrame("Contact Display View");
  private JTextField ip = new JTextField("");
  private JTextField lastName = new JTextField("");

  private JTextField lastName2 = new JTextField("");
  private JTextField major = new JTextField("");
  private JTextField major2 = new JTextField("");
  private JMenuBar menuBar = new JMenuBar();
  private JTextField middleName = new JTextField("");
  private JTextField middleName2 = new JTextField("");
  private JPanel panel;
  private JTextField password = new JTextField("");
  private JMenuItem removeItem = new JMenuItem("Remove");
  private JTextField tableName = new JTextField("");
  private JMenuItem updateItem = new JMenuItem("Update");
  private JTextField userName = new JTextField("");

  /**
   * Initalizes controller and model and registers as observer
   * 
   * @param controller ContactControllerInterface
   * @param model ContactModelInterface
   */
  public ContactView(ContactControllerInterface controller,
          ContactModelInterface model) {
    this.controller = controller;
    this.model = model;
    model.registerObserver(this);
    cardLayout = new CardLayout();
    panel = new JPanel(cardLayout);
  }

  /**
   * Creates general JFrame window with the MenuBar and frame settings
   */
  public void createFrame() {
    frame.setSize(350, 270);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setLayout(new CardLayout());
    frame.setLocationRelativeTo(null);

    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    frame.setJMenuBar(menuBar);

    clearDBItem.setEnabled(false);
    addItem.setEnabled(false);
    removeItem.setEnabled(false);
    updateItem.setEnabled(false);

    fileMenu.add(clearDBItem);
    fileMenu.add(connectItem);
    fileMenu.addSeparator();
    fileMenu.add(exitItem);
    editMenu.add(addItem);
    editMenu.add(removeItem);
    editMenu.add(updateItem);

    fileMenu.setMnemonic(KeyEvent.VK_F);
    editMenu.setMnemonic(KeyEvent.VK_E);
    clearDBItem.setMnemonic(KeyEvent.VK_C);
    exitItem.setMnemonic(KeyEvent.VK_X);
    addItem.setMnemonic(KeyEvent.VK_A);
    removeItem.setMnemonic(KeyEvent.VK_R);
    updateItem.setMnemonic(KeyEvent.VK_U);

    panel.add(showMainDisplay(), "MAIN DISPLAY");
    panel.add(showConnectDisplay(), "CONNECT DISPLAY");
    panel.add(showAddDisplay(), "ADD DISPLAY");
    frame.add(panel);

    clearDBItem.addActionListener(new ActionListener() {
      // Clears database when Clear DB menuItem is selected
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.clearDB();
        cardLayout.show(panel, "MAIN DISPLAY");
      }
    });
    
    connectItem.addActionListener(new ActionListener() {
      // Goes to connect Display to connect to database when clicked
      @Override
      public void actionPerformed(ActionEvent e) {
        cardLayout.show(panel, "CONNECT DISPLAY");
      }
    });
    
    exitItem.addActionListener(new ActionListener() {
      // Exits application when clicked
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.exit();
      }
    });
    
    addItem.addActionListener(new ActionListener() {
   // Goes to add display
      @Override
      public void actionPerformed(ActionEvent e) {
        cardLayout.show(panel, "ADD DISPLAY");

      }

    });

    removeItem.addActionListener(new ActionListener() {
      // Removes current contact from ArrayList
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.remove();
      }
    });
    
    updateItem.addActionListener(new ActionListener() {
      // Updates individual contact in ArrayList
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.update();
      }
    });

    frame.setVisible(true);
  }

  /**
   * Disables add menu item
   */
  public void disableAdd() {
    addItem.setEnabled(false);
  }

  /**
   * Disables clear menu item
   */
  public void disableClear() {
    clearDBItem.setEnabled(false);
  }

  /**
   * Disables next button
   */
  public void disableNext() {
    nextButton.setEnabled(false);
  }

  /**
   * Disables previous button
   */
  public void disablePrevious() {
    prevButton.setEnabled(false);
  }

  /**
   * Disables remove menu item
   */
  public void disableRemove() {
    removeItem.setEnabled(false);
  }

  /**
   * Disables update menu item
   */
  public void disableUpdate() {
    updateItem.setEnabled(false);
  }

  /**
   * Enables add menu item
   */
  public void enableAdd() {
    addItem.setEnabled(true);
  }

  /**
   * Enables clear menu item
   */
  public void enableClear() {
    clearDBItem.setEnabled(true);
  }

  /**
   * Enables next button
   */
  public void enableNext() {
    nextButton.setEnabled(true);
  }

  /**
   * Enables previous button
   */
  public void enablePrevious() {
    prevButton.setEnabled(true);
  }

  /**
   * Enables remove menu item
   */
  public void enableRemove() {
    removeItem.setEnabled(true);
  }

  /**
   * Enables update menu item
   */
  public void enableUpdate() {
    updateItem.setEnabled(true);
  }

  /**
   * Gets email String from text field
   * 
   * @return String text in email JTextField
   */
  public String getEmail() {
    return email.getText();
  }

  /**
   * Gets first name String from text field
   * 
   * @return String text in firstName JTextField
   */
  public String getFirstName() {
    return firstName.getText();
  }

  /**
   * Gets lastName String from text field
   * 
   * @return String text in lastName JTextField
   */
  public String getLastName() {
    return lastName.getText();
  }

  /**
   * Gets major String from text field
   * 
   * @return String text in major JTextField
   */
  public String getMajor() {
    return major.getText();
  }

  /**
   * Gets middle name String from text field
   * 
   * @return String text in middleName JTextField
   */
  public String getMiddleName() {
    return middleName.getText();
  }

  /**
   * Sets email String in text field
   */
  public void setEmail(String aString) {
    email.setText(aString);
  }

  /**
   * Sets firstname String in text field
   */
  public void setFirstName(String aString) {
    firstName.setText(aString);
  }

  /**
   * Sets lastname String in text field
   */
  public void setLastName(String aString) {
    lastName.setText(aString);
  }

  /**
   * Sets major String in text field
   */
  public void setMajor(String aString) {
    major.setText(aString);
  }

  /**
   * Sets middlename String in text field
   */
  public void setMiddleName(String aString) {
    middleName.setText(aString);
  }

  /**
   * Creates Add Contact Display
   * 
   * @return JPanel with add contact displays
   */
  public JPanel showAddDisplay() {
    JPanel addPanel = new JPanel(new GridLayout(6, 2));
    JButton okButton2 = new JButton("OK");
    JButton cancelButton = new JButton("Cancel");
    JLabel firstNameLabel = new JLabel("First Name", SwingConstants.RIGHT);
    JLabel middleNameLabel = new JLabel("Middle Name", SwingConstants.RIGHT);
    JLabel lastNameLabel = new JLabel("Last Name", SwingConstants.RIGHT);
    JLabel emailLabel = new JLabel("Email", SwingConstants.RIGHT);
    JLabel majorLabel = new JLabel("Major", SwingConstants.RIGHT);

    addPanel.add(firstNameLabel);
    addPanel.add(firstName2);
    addPanel.add(middleNameLabel);
    addPanel.add(middleName2);
    addPanel.add(lastNameLabel);
    addPanel.add(lastName2);
    addPanel.add(emailLabel);
    addPanel.add(email2);
    addPanel.add(majorLabel);
    addPanel.add(major2);
    addPanel.add(okButton2);
    addPanel.add(cancelButton);

    okButton2.addActionListener(new ActionListener() {
      // Adds Contact to ArrayList
      @Override
      public void actionPerformed(ActionEvent e) {

        controller.ok(firstName2.getText(), middleName2.getText(),
                lastName2.getText(), email2.getText(), major2.getText());
        cardLayout.show(panel, "MAIN DISPLAY");
      }
    });

    cancelButton.addActionListener(new ActionListener() {
      // Leaves Add Display
      @Override
      public void actionPerformed(ActionEvent e) {
        cardLayout.show(panel, "MAIN DISPLAY");
      }
    });
    return addPanel;
  }

  /**
   * Creates Connection Display
   * 
   * @return JPanel with connection displays
   */
  public JPanel showConnectDisplay() {
    JPanel connPanel = new JPanel(new GridLayout(6, 2));

    JLabel userNameLabel = new JLabel("User Name", SwingConstants.RIGHT);
    JLabel passwordLabel = new JLabel("Password", SwingConstants.RIGHT);
    JLabel ipLabel = new JLabel("IP Address", SwingConstants.RIGHT);
    JLabel dbNameLabel = new JLabel("Database Name", SwingConstants.RIGHT);
    JLabel tableNameLabel = new JLabel("Table Name", SwingConstants.RIGHT);
    JButton okButton = new JButton("OK");

    userName.setText("b4402acc8331ea");
    password.setText("828537a4");
    ip.setText("us-cdbr-cb-east-01.cleardb.net");
    dbName.setText("cb_contactdjp");
    tableName.setText("ekk_contact");

    connPanel.add(userNameLabel);
    connPanel.add(userName);
    connPanel.add(passwordLabel);
    connPanel.add(password);
    connPanel.add(ipLabel);
    connPanel.add(ip);
    connPanel.add(dbNameLabel);
    connPanel.add(dbName);
    connPanel.add(tableNameLabel);
    connPanel.add(tableName);
    connPanel.add(okButton);

    okButton.addActionListener(new ActionListener() {
      // Submits database information
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.connectToDB(userName.getText(), password.getText(),
                ip.getText(), dbName.getText(), tableName.getText());
        cardLayout.show(panel, "MAIN DISPLAY");
      }
    });
    return connPanel;
  }

  /**
   * Creates Contact Info display
   * 
   * @return JPanel with Main Displays
   */
  public JPanel showMainDisplay() {
    JPanel mainPanel = new JPanel(new GridLayout(6, 2));
    JLabel firstNameLabel = new JLabel("First Name", SwingConstants.RIGHT);
    JLabel middleNameLabel = new JLabel("Middle Name", SwingConstants.RIGHT);
    JLabel lastNameLabel = new JLabel("Last Name", SwingConstants.RIGHT);
    JLabel emailLabel = new JLabel("Email", SwingConstants.RIGHT);
    JLabel majorLabel = new JLabel("Major", SwingConstants.RIGHT);

    mainPanel.add(firstNameLabel);
    mainPanel.add(firstName);
    mainPanel.add(middleNameLabel);
    mainPanel.add(middleName);
    mainPanel.add(lastNameLabel);
    mainPanel.add(lastName);
    mainPanel.add(emailLabel);
    mainPanel.add(email);
    mainPanel.add(majorLabel);
    mainPanel.add(major);
    mainPanel.add(prevButton);
    mainPanel.add(nextButton);

    prevButton.addActionListener(new ActionListener() {
      // Goes to previous contact entry
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.previous();
      }
    });

    nextButton.addActionListener(new ActionListener() {
      // Goes to next contact entry
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.next();
      }
    });
    return mainPanel;
  }

  /*
   * (non-Javadoc)
   * 
   * @see edu.elon.contact.ModelObserver#updateFields()
   */
  @Override
  public void updateFields() {
    firstName.setText((model.getContact().getFirst()));
    middleName.setText((model.getContact().getMiddle()));
    lastName.setText((model.getContact().getLast()));
    email.setText((model.getContact().getEmail()));
    major.setText((model.getContact().getMajor()));
  }
}