/**
 * DecryptReader.java 1.0 Oct 1, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.io;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

/**
 * This class decrypts the text by subtracting 1 from the character's number.
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public class DecryptReader extends FilterReader {
  

  /**
   * Decrypts the text.
   * 
   * @param aIn Reader
   */
  protected DecryptReader(Reader aIn) {
    super(aIn);
  }
  
  public int read() throws IOException{
    int c = super.read();
    return (c == -1 || c == 32 ? c : c + 1);
    
  }
  
  public int read(char[] chars) throws IOException{
    int i = 0;
    for (i = 0; i < chars.length; i++){
      if ((int) chars[i] != 32){
        chars[i] = (char) (chars[i] + 1);
      }
    }
    return i;
  }
  
  public int read(CharBuffer buff) throws IOException{
    int i = 0;
    for (i = 0; i < buff.length(); i++){
      if ((int) buff.get(i) != 32){
      char c = (char) (buff.get(i) + 1);
      buff.put(i, c);
      }
    }
    return i;
  }
  
  public int read(char[] chars, int off, int len) throws IOException{
    int i = 0;
    for (i = off; i < off + len; i++){
      if ((int) chars[i] != 32){
        chars[i] = (char) (chars[i] + 1);
      }
    }
    return i;
  }

}
