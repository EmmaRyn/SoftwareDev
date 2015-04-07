/**
 * FunctionServer.java 1.0 Nov 10, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.math;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Creates the server side of the application.
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public class FunctionServer {

  /**
   * Creates the classes in the rmiregistry
   * 
   * @param args String[] arguments
   * @throws RemoteException 
   */
  public static void main(String[] args) throws RemoteException {
    try {
      System.out.println("Constructing server implementations...");

      Function dell = new Dell();
      Function samsclub = new SamsClub();
      Function minimumabsolutesum = new MinimumAbsoluteSum();

      System.out.println("Binding server implementations to registry...");
      
      Context namingContext = new InitialContext();
      namingContext.rebind("rmi:dell", dell);
      namingContext.rebind("rmi:samsclub", samsclub);
      namingContext.rebind("rmi:minimumabsolutesum", minimumabsolutesum);
      
      System.out.println("Waiting for invocations from clients...");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
