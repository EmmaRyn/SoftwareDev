/**
 * OptimizerFactory.java 1.0 Oct 6, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.math;

import java.rmi.Remote;

/**
 * Uses Simpleton and Simple Factory pattern to create 
 * a single instance of an optimizer.
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public class OptimizerFactory implements Remote{
  
  private volatile static OptimizerFactory optimizer;
  
  private OptimizerFactory() {
  }
  
  /**
   * Creates or retrieves single instance of technique.
   * 
   * @return optimizer OptimizerFactory unique instance
   */
  public static OptimizerFactory getInstance(){
    if (optimizer == null){
      synchronized (OptimizerFactory.class) {
        if (optimizer == null) {
          optimizer = new OptimizerFactory();
        }
      }
    }
    return optimizer;
  }
  
  /**
   * Creates instance of the technique chosen in the gui.
   * 
   * @param technique String full class name of technique
   * @return optimizer Technique that was chosen in gui
   */
  public Technique chooseTechnique(String technique){
    Technique optimizer = null;
    Class<?> c = null;
    try {
      c = Class.forName(technique);
      try {
        optimizer = (Technique) c.newInstance();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
    }
    return optimizer;
  }
}
