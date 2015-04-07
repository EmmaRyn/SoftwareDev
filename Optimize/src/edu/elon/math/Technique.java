/*
 * Technique.java 1.0 September 24, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for all techniques listed in Technique menu
 * 
 * @author ekwiatkowski
 *
 */
public interface Technique extends Remote {
	
	/**
	 * Calls the optimization method for each technique
	 * 
	 * @param funct Function that the technique uses to optimize
	 * @return Double result of the optimization
	 * @throws RemoteException 
	 */
	public Double doOptimize(Function funct) throws RemoteException;
	
	
	/**
	 * Gets name of the technique
	 * 
	 * @return String name of the technique
	 */
	public String getName();
}
