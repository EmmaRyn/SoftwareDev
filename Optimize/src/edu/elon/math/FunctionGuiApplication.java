/*
 * FunctionGuiApplication.java 1.0 September 24, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 * Creates the window displays to show Functions, their input values
 * and results when optimizers are applied.
 * 
 * @author ekwiatkowski
 *
 */
public class FunctionGuiApplication implements FunctionObserver {

  private ArrayList<JTextField> dataInputs = new ArrayList<JTextField>();
  private FunctionRemote functRemote;
  private ArrayList<String> names;
  private ArrayList<String> opts = new ArrayList<String>();
  private JTextField resText = new JTextField(25);;
  private Double result;
  private Technique tech;
  private JComboBox<String> techMenu = new JComboBox<String>();
  private ArrayList<Double> values;

  /**
   * Registers as observer, calls gui creator, and retrieves
   * optimizers environmental variable
   * 
   * @param functRemote Function that the gui uses
   * @param x int x-axis location of the gui
   * @param y int y-axis location of the gui
   * @throws RemoteException 
   */
  public FunctionGuiApplication(FunctionRemote functRemote) throws RemoteException {
    Remote remote = UnicastRemoteObject.exportObject(this, 0);
    String className;

    try {
      String title = functRemote.getTitle();
      String titleWithoutSpaces = String.join(" ", title);
      className = "edu.elon.math." + titleWithoutSpaces;

      try {
        this.functRemote = (FunctionRemote) Class.forName(className).newInstance();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    } catch (RemoteException e1) {
      e1.printStackTrace();
    }

    try {
      this.functRemote.addObserver(this);
      names = functRemote.getInputNames();
      values = functRemote.getInputValues();
    } catch (RemoteException e1) {
      e1.printStackTrace();
    }  

    String env;
    try {
      env = this.functRemote.getEnv("optimizers");
      StringTokenizer token = new StringTokenizer(env, ", ");
      while (token.hasMoreTokens()) {
        String tok = token.nextToken();
        opts.add(tok);
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  /**
   * @param args
   * @throws RemoteException
   */
  public static void main(String[] args) throws RemoteException {
    FunctionRemote dellFunc;
    FunctionRemote samsFunc;
    FunctionRemote minFunc;
    System.setProperty("java.security.policy", "client.policy");
    System.setSecurityManager(new SecurityManager());
    String url = "rmi://localhost/";
    if (args.length == 1) {
      url = "rmi://" + args[0] + "/";
    }
    // change to "rmi://yourserver.com/"
    // when server runs on remote machine yourserver.com
    try {
      Context namingContext = new InitialContext();
      dellFunc = (FunctionRemote) namingContext.lookup(url + "dell");
      samsFunc = (FunctionRemote) namingContext.lookup(url + "samsclub");
      minFunc = (FunctionRemote) namingContext.lookup(url
              + "minimumabsolutesum");

      FunctionGuiApplication dellGui = new FunctionGuiApplication(dellFunc);
      FunctionGuiApplication samsGui = new FunctionGuiApplication(samsFunc);
      FunctionGuiApplication minGui = new FunctionGuiApplication(minFunc);

      dellGui.createGui(50, 100);
      samsGui.createGui(50, 300);
      minGui.createGui(425, 100);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Physically creates the gui and sets them visible
   * 
   * @param x int x-axis location of the gui
   * @param y int y-axis location of the gui
   */
  public void createGui(int x, int y) {
    JFrame frame = new JFrame();
    Container container = frame.getContentPane();
    JPanel holdGrid = new JPanel();
    JPanel leftGrid = new JPanel();
    JPanel rightGrid = new JPanel();
    JPanel southPanel = new JPanel();
    southPanel.setLayout(new GridLayout(0, 1));

    JPanel techPanel = new JPanel();
    JPanel resultPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    resText.setText("");
    JButton solve = new JButton("Solve");
    JButton optimize = new JButton("Optimize");

    for (String opt : opts) {
      techMenu.addItem(opt);
    }

    /**
     * Creates action listener for Solve button and implements
     * Runnable to show all output updates
     * 
     * @author ekwiatkowski
     *
     */
    class SolveListener implements ActionListener, Runnable {

      @Override
      public void actionPerformed(ActionEvent e) {
        int i = 0;
        for (JTextField text : dataInputs) {
          double t = Double.parseDouble(text.getText());
          values.set(i, t);
          i++;
        }

        try {
          functRemote.setInputValues(values);
        } catch (RemoteException e1) {
          e1.printStackTrace();
        }

        Thread t = new Thread(this);
        t.start();
      }

      @Override
      public void run() {

        try {
          functRemote.evaluate();
        } catch (RemoteException e) {
          e.printStackTrace();
        }

      }
    }

    /**
     * Creates action listener for Optimize button and implements
     * Runnable to show all output updates
     * 
     * @author ekwiatkowski
     *
     */
    class OptListener implements ActionListener, Runnable {

      @Override
      public void actionPerformed(ActionEvent e) {
        int i = 0;
        for (JTextField text : dataInputs) {
          double t = Double.parseDouble(text.getText());
          values.set(i, t);
          i++;
        }
        try {
          functRemote.setInputValues(values);
          String optTech = (String) techMenu.getSelectedItem();
          functRemote.setOptimizationTechnique(optTech);
        } catch (RemoteException e1) {
          e1.printStackTrace();
        }
        Thread t = new Thread(this);
        t.start();
      }

      @Override
      public void run() {
        try {
          functRemote.optimize();
        } catch (RemoteException e) {
          e.printStackTrace();
        }

      }
    }

    solve.addActionListener(new SolveListener());
    optimize.addActionListener(new OptListener());

    leftGrid.setLayout(new GridLayout(names.size(), 1));
    rightGrid.setLayout(new GridLayout(values.size(), 1));
    holdGrid.setLayout(new BorderLayout(5, 0));

    for (int i = 0; i < names.size(); i++) {
      leftGrid.add(new JLabel(names.get(i), SwingConstants.RIGHT));
      JTextField text = new JTextField("" + values.get(i), 10);
      dataInputs.add(text);
      rightGrid.add(text);
    }

    techPanel.add(new JLabel("Technique"));
    techPanel.add(techMenu);
    resultPanel.add(new JLabel("Result"));
    resultPanel.add(resText);
    buttonPanel.add(solve);
    buttonPanel.add(optimize);
    southPanel.add(resultPanel);
    southPanel.add(buttonPanel);

    container.add(techPanel, BorderLayout.NORTH);
    holdGrid.add(leftGrid, BorderLayout.WEST);
    holdGrid.add(rightGrid, BorderLayout.CENTER);

    JScrollPane scrollPane = new JScrollPane(holdGrid);
    scrollPane
    .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane
    .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    container.add(scrollPane, BorderLayout.CENTER);
    container.add(southPanel, BorderLayout.SOUTH);

    try {
      frame.setLocation(x, y);
      frame.setTitle(functRemote.getTitle());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update() throws RemoteException{
    result = functRemote.getOutput();
    resText.setText("" + result);
    int i = 0;
    for (JTextField field : dataInputs) {
      field.setText("" + functRemote.getInputValues().get(i));
      ++i;
    }
  }
}
