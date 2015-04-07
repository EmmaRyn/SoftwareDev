/*
 * NelderMead.java 1.0 August 20, 2014
 *
 * Copyright (c) 2014 David J. Powell and Emma Kwiatkowski, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import flanagan.math.Minimisation;
import flanagan.math.MinimisationFunction;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Nelder Mead also known as direct simplex method is a widely used
 * nonlinear unconstrained optimization technique. The goSimplex code
 * is only partially implemented and always returns a optimal value of
 * 0.0. This class will be replaced in a follow on homework assignment
 * using the Adapter design pattern and code from Michael Flanagan
 * 
 * @author dpowell2
 * @version 1.0
 */
public class NelderMead implements Technique, Remote{

  /**
   * Calls function from a MinimisationFunction class in order to fit
   * the nelderMead() parameter requirements.
   * 
   * @author ekwiatkowski
   * @version 1.0
   *
   */
  public class TransFunction implements MinimisationFunction {

    @Override
    public double function(double[] aParam) {
      double res = 0;
      try {
        function.setInputValues(convertDoubleArrayToArrayList(aParam));
        res = function.evaluate();
        if (!function.isMinimize()) {
          res *= -1;
        }
      } catch (RemoteException e) {
        e.printStackTrace();
      }
      return res;
    }

  }

  private Function function;
  private Minimisation min;
  private String name = "edu.elon.math.NelderMead";
  private TransFunction transFunc;


  /**
   * Default constructor to satisfy coding best practices
   */
  public NelderMead() {
    min = new Minimisation();
    transFunc = new TransFunction();
  }

  @Override
  public Double doOptimize(Function funct) throws RemoteException {
    Double d = goSimplex(funct);
  
      funct.getInputValues();
    
    return d;

  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * 
   * Leaves optimal design as the value of the parameter, function
   * instance, field called inputValues;
   * 
   * @param function Function instance containing function starting
   *        point and evaluation logic
   * @return Double value for optimal design.
   * @throws RemoteException 
   */
  public Double goSimplex(Function function) throws RemoteException {
    this.function = function;
 
    ArrayList<Double> vals;

      vals = function.getInputValues();
      double[] start = convertArrayListToDouble(vals);
      double[] step = new double[start.length];
      for (int i = 0; i < step.length; i++) {
        if (Math.abs(start[i]) < 1) {
          step[i] = 1.0;
        } else {
          step[i] = 0.5 * start[i];
        }
      }
      double fTol = 1e-15;

      min.nelderMead(transFunc, start, step, fTol);
      double[] param = min.getParamValues();
      function.setInputValues(convertDoubleArrayToArrayList(param));
      function.evaluate();
      System.out.println("Nelder Mead stub invoked");
      return function.getOutput();

  }

  /**
   * Converts an input point represented as a one dimensional array of
   * doubles into an arraylist of double
   * 
   * @param aInputArray input array of double values
   * @return input arraylist of Double values
   */
  private ArrayList<Double> convertDoubleArrayToArrayList(double[] aInputArray) {
    ArrayList<Double> bestInputPoint = new ArrayList<Double>();
    for (double d : aInputArray) {
      bestInputPoint.add(d);
    }
    return bestInputPoint;
  }
  
  /**
   * Converts an arraylist of Double into an array of doubles
   * 
   * @param aStartingPoint arraylist of Double representing an input
   *        point for an optimization problem
   * @return double array of input point represented as a 1D vector
   */
  private double[] convertArrayListToDouble(ArrayList<Double> aStartingPoint) {
    int length = aStartingPoint.size();
    double[] inputArray = new double[length];
    for (int i = 0; i < length; i++) {
      inputArray[i] = aStartingPoint.get(i);
    }
    return inputArray;
  }

}
