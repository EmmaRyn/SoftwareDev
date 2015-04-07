/*
 * MinimumAbsoluteSum.java 1.0 September 24, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Takes the absolute sum of all the values in the input fields
 * 
 * @author ekwiatkowski
 * @version 1.0
 * 
 */
public class MinimumAbsoluteSum extends Function implements Remote{

  /**
   * Default constructor to set initial input point to (0, 0)
   */
  public MinimumAbsoluteSum() throws RemoteException {
    this(new double[] { -100, 100, -100, 100, -100, 100, -100, 100, -100, 100 });

  }

  /**
   * Constructor initializes initial input point to ArrayList<Double>
   * passed in as a parameter
   * 
   * @param inputValues ArrayList<Double> representing values for
   *        initial design point.
   */
  public MinimumAbsoluteSum(ArrayList<Double> inputValues) throws RemoteException{
    this(inputValues, createDefaultInputNames());
  }

  /**
   * Initializes the names of each input along with its initial value
   * from the parameters.
   * 
   * @param inputValues ArrayList<Double> representing values of
   *        initial design point.
   * @param inputNames ArrayList<String> representing names of each
   *        input parameter
   */
  public MinimumAbsoluteSum(ArrayList<Double> inputValues,
          ArrayList<String> inputNames) throws RemoteException {
   
      this.setInputValues(inputValues);
      this.setInputNames(inputNames);
      this.setMinimize(true);
      this.setTitle("MinimumAbsoluteSum");
    
  }

  /**
   * Constructor that initializes starting design point from values
   * passed in as an array of double.
   * 
   * @param inputs double[] array of values to set initial design
   *        point.
   */
  public MinimumAbsoluteSum(double[] inputs) throws RemoteException{
    ArrayList<Double> values = new ArrayList<Double>();
    for (double d : inputs) {
      values.add(new Double(d));
    }

      this.setInputValues(values);
      this.setInputNames(createDefaultInputNames());
      this.setMinimize(true);
      this.setTitle("MinimumAbsoluteSum");

  }

  /**
   * Provides a default set of names for input parameters and is used
   * if user does not supply any.
   * 
   * @return ArrayList<String> representing input parameter names.
   */
  private static ArrayList<String> createDefaultInputNames() {
    ArrayList<String> names = new ArrayList<String>();
    names.add("Value1");
    names.add("Value2");
    names.add("Value3");
    names.add("Value4");
    names.add("Value5");
    names.add("Value6");
    names.add("Value7");
    names.add("Value8");
    names.add("Value9");
    names.add("Value10");
    return names;
  }

  /**
   * Evaluates the function from the current set of input values.
   * 
   * @return Double instance of function value
   * @throws RemoteException 
   */
  @Override
  public Double evaluate() throws RemoteException {
    double absSum = 0;
    ArrayList<Double> values;
      values = getInputValues();
      for (Double val : values) {
        absSum += Math.abs(val);
      }
      this.setOutput(absSum);
      return this.getOutput();
    
  }
}
