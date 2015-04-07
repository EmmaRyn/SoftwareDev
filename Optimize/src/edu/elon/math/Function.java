/*
 * Function.java 1.0 August 20, 2014
 *
 * Copyright (c) 2014 David J. Powell and Emma Kwiatkowski, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.util.ArrayList;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
 * Standard interface for consistency in the Elon evaluation process.
 * Each concrete Elon Function must extend this class.
 * 
 * @author dpowell2
 * @version 1.0
 */
public abstract class Function extends UnicastRemoteObject implements FunctionRemote {

  /**
   * Generated serial id
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor
   * 
   * @throws RemoteException
   */
  protected Function() throws RemoteException {

  }

  /**
   * constant to represent new line
   */
  public static final String EOL = "\n";

  /**
   * constant to represent one blank space
   */
  public static final String SPACE = " ";
  ArrayList<String> inputNames = new ArrayList<String>();
  ArrayList<Double> inputValues = new ArrayList<Double>();

  // true if minimization function and false if maximization
  boolean minimize = true;
  String optimizationTechnique = null;
  Double output = 0.0;
  String title = null;
 
  Technique tech = null; 
  
  /**
   * When inputValues change, this notifies observers 
   * to call update.
   * @throws RemoteException 
   */
  public void measurementsChanged() throws RemoteException {
    notifyObservers();
  }

  /**
   * Evaluates the current set of input values to calculate the
   * function value. We currently consider one output value for a
   * function. If the function has multiple output values then the
   * function must have these combined into a single value.
   * 
   * @return Double of function result from evaluation at current
   *         point.
   * @throws RemoteException 
   */
  public abstract Double evaluate() throws RemoteException;
  
  /**
   * Returns an ArrayList of String for the names of each input
   * parameter. This allows the class creator to make the names
   * meaningful to a user instead of X1, X2, ...
   * 
   * @return ArrayList<String> of names for each input parameter
   */
  public ArrayList<String> getInputNames(){
    return inputNames;
  }

  /**
   * Returns the current value of each input for the function. All
   * function inputs are treated as Double
   * 
   * @return ArrayList<Double> of values representing current point.
   */
  public ArrayList<Double> getInputValues() {
    return inputValues;
  }

  /**
   * Gets the full package qualified classname of the currently set
   * optimization technique
   * 
   * @return String representing package qualified classname of
   *         optimization technique
   */
  public String getOptimizationTechnique(){
    return optimizationTechnique;
  }

  /**
   * Gets the function output value resulting from the evaluation of
   * the current input set.
   * 
   * @return Double representing function result
   */
  public Double getOutput(){
    return output;
  }

  /**
   * Gets the name of the function
   * 
   * @return String representing the user friendly name of the
   *         function.
   */
  public String getTitle(){
    return title;
  }

  /**
   * Gets the direction of the optimization problem. If true then we
   * have a minimization problem otherwise a maximization problem
   * 
   * @return boolean value of true if minimization
   */
  public boolean isMinimize() throws RemoteException {
    return minimize;
  }

  /**
   * Optimizes function. The optimizer is required to leave the best
   * design point and function value as the current state of the
   * function in <code>inputValues</code> and <code>output</code>
   * 
   * @return Double representing best achieved function value.
   */
  public Double optimize() throws RemoteException {
    Double optimalValue = tech.doOptimize(this);
    measurementsChanged();
    return optimalValue;
  }

  /**
   * Set the current set of input names for the input parameters to
   * the inputNames passed as a parameter.
   * 
   * @param inputNames ArrayList<String> of names for set of input
   *        parameters for the function.
   * @throws RemoteException 
   */
  public void setInputNames(ArrayList<String> inputNames) throws RemoteException {
    this.inputNames = inputNames;
    measurementsChanged();
  }

  /**
   * Sets the current value of the input set for the function.
   * 
   * @param inputValues ArrayList<Double> representing the value of
   *        each input parameter. The input set is assumed to be all
   *        Doubles.
   * @throws RemoteException 
   */
  public void setInputValues(ArrayList<Double> inputValues) throws RemoteException{
    this.inputValues = inputValues;
    measurementsChanged();
  }

  /**
   * Sets function to be a minimization or a maximization
   * 
   * @param minimize boolean of true if minimization
   */
  public void setMinimize(boolean minimize){
    this.minimize = minimize;
  }

  /**
   * Sets internal field to the value of the passed parameter which
   * represents the package qualified class name of the optimization
   * technique to use.
   * 
   * @param optimizationTechnique String representing package and
   *        class name of the optimizer to use for the problem.
   */
  public void setOptimizationTechnique(String optimizationTechnique) {
    this.optimizationTechnique = optimizationTechnique;
    OptimizerFactory factory = OptimizerFactory.getInstance();
    tech = factory.chooseTechnique(optimizationTechnique);
  }

  /**
   * Sets the value of the function result.
   * 
   * @param output Double instance of function result
   * @throws RemoteException 
   */
  public void setOutput(Double output) throws RemoteException{
    this.output = output;
    measurementsChanged();
  }

  /**
   * Sets the user friendly name of the function
   * 
   * @param title String representing function name
   */
  public void setTitle(String title){
    this.title = title;
  }

  /**
   * User friendly representation of the function state and
   * configuration. Shows the function name, input variable names and
   * input variable values
   * 
   * @return String representing state of function.
   */
  @Override
  public String toString() {
    StringBuffer s = new StringBuffer();
      s.append("Function: " + this.getTitle() + EOL);
      for (int i = 0; i < getInputValues().size(); i++) {
        s.append(getInputNames().get(i) + SPACE + getInputValues().get(i) + EOL);
      }
    return s.toString();
  }
  
  @Override
  public void notifyObservers() throws RemoteException {
    for (FunctionObserver o : functObs){
      o.update();
    }
  }
  
  @Override
  public void removeObserver(FunctionObserver obs){
    functObs.remove(obs);
  }
  
  @Override
  public void addObserver(FunctionObserver obs){
    functObs.add(obs);
  }
  
  @Override
  public String getEnv(String env){
    return System.getenv(env);
    //return "edu.elon.math.NelderMead, edu.elon.math.Powell, edu.elon.math.RandomWalk";
  }
}
