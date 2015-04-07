/**
 * FunctionRemote.java 1.0 Nov 10, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.math;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * A Remote interface that allows the Function class to extend other classes.
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public interface FunctionRemote extends Remote {
  
  public ArrayList<FunctionObserver> functObs = new ArrayList<FunctionObserver>();

  /**
   * When inputValues change, this notifies observers 
   * to call update.
   */
  public void measurementsChanged() throws RemoteException;

  /**
   * Evaluates the current set of input values to calculate the
   * function value. We currently consider one output value for a
   * function. If the function has multiple output values then the
   * function must have these combined into a single value.
   * 
   * @return Double of function result from evaluation at current
   *         point.
   */
  public abstract Double evaluate() throws RemoteException;

  /**
   * Returns an ArrayList of String for the names of each input
   * parameter. This allows the class creator to make the names
   * meaningful to a user instead of X1, X2, ...
   * 
   * @return ArrayList<String> of names for each input parameter
   */
  public ArrayList<String> getInputNames() throws RemoteException;

  /**
   * Returns the current value of each input for the function. All
   * function inputs are treated as Double
   * 
   * @return ArrayList<Double> of values representing current point.
   */
  public ArrayList<Double> getInputValues() throws RemoteException;

  /**
   * Gets the full package qualified classname of the currently set
   * optimization technique
   * 
   * @return String representing package qualified classname of
   *         optimization technique
   */
  public String getOptimizationTechnique() throws RemoteException;

  /**
   * Gets the function output value resulting from the evaluation of
   * the current input set.
   * 
   * @return Double representing function result
   */
  public Double getOutput() throws RemoteException;

  /**
   * Gets the name of the function
   * 
   * @return String representing the user friendly name of the
   *         function.
   */
  public String getTitle() throws RemoteException;

  /**
   * Gets the direction of the optimization problem. If true then we
   * have a minimization problem otherwise a maximization problem
   * 
   * @return boolean value of true if minimization
   */
  public boolean isMinimize() throws RemoteException;

  /**
   * Optimizes function. The optimizer is required to leave the best
   * design point and function value as the current state of the
   * function in <code>inputValues</code> and <code>output</code>
   * 
   * @return Double representing best achieved function value.
   */
  public Double optimize() throws RemoteException;

  /**
   * Set the current set of input names for the input parameters to
   * the inputNames passed as a parameter.
   * 
   * @param inputNames ArrayList<String> of names for set of input
   *        parameters for the function.
   */
  public void setInputNames(ArrayList<String> inputNames) throws RemoteException;

  /**
   * Sets the current value of the input set for the function.
   * 
   * @param inputValues ArrayList<Double> representing the value of
   *        each input parameter. The input set is assumed to be all
   *        Doubles.
   */
  public void setInputValues(ArrayList<Double> inputValues) throws RemoteException;

  /**
   * Sets function to be a minimization or a maximization
   * 
   * @param minimize boolean of true if minimization
   */
  public void setMinimize(boolean minimize) throws RemoteException;

  /**
   * Sets internal field to the value of the passed parameter which
   * represents the package qualified class name of the optimization
   * technique to use.
   * 
   * @param optimizationTechnique String representing package and
   *        class name of the optimizer to use for the problem.
   */
  public void setOptimizationTechnique(String optimizationTechnique) throws RemoteException;

  /**
   * Sets the value of the function result.
   * 
   * @param output Double instance of function result
   */
  public void setOutput(Double output) throws RemoteException;

  /**
   * Sets the user friendly name of the function
   * 
   * @param title String representing function name
   */
  public void setTitle(String title) throws RemoteException;

  /**
   * @param aFunctionGuiApplication
   */
  public void addObserver(FunctionObserver obs) throws RemoteException;

  /**
   * @param aString
   * @return
   */
  public String getEnv(String aString) throws RemoteException;
  
  /**
   * @throws RemoteException
   */
  public void notifyObservers() throws RemoteException;
  
  /**
   * @param obs
   * @throws RemoteException
   */
  public void removeObserver(FunctionObserver obs) throws RemoteException;

}
