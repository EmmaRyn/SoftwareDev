/*
 * Copyright (c) 1994, 1998, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package edu.elon.math;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The observer interface mimicked after the java.util.observer
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public interface FunctionObserver extends Remote {

  /**
   * This method is called whenever the observed object is changed. An
   * application calls an <tt>Observable</tt> object's
   * <code>notifyObservers</code> method to have all the object's
   * observers notified of the change.
   *
   * @throws RemoteException 
   */
  public void update() throws RemoteException;
}
