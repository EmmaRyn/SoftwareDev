/**
 * EncryptWriter.java 1.0 Oct 1, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.io;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * This class encrypts the text with a code of the character + 1.
 * 
 * @author ekwiatkowski
 * @version 1.0
 * 
 */
public class EncryptWriter extends FilterWriter {

  /**
   * Encrypts the text.
   * 
   * @param aOut Writer
   */
  protected EncryptWriter(Writer aOut) {
    super(aOut);
  }

  @Override
  public void write(char[] chars) throws IOException {
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] != 32) {
        chars[i] = (char) (chars[i] - 1);
      }
    }
  }

  @Override
  public void write(char[] chars, int off, int len) throws IOException {
    for (int i = off; i < off + len; i++) {
      if (chars[i] != 32) {
        chars[i] = (char) (chars[i] - 1);
      }
    }
  }

  @Override
  public void write(int c) throws IOException {
    if (c != 32) {
      super.write(c - 1);
    }
  }

  @Override
  public void write(String str) throws IOException {
    char[] chars = str.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] != 32) {
        chars[i] = (char) (chars[i] - 1);
      }
    }
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    char[] chars = str.toCharArray();
    for (int i = off; i < len; i++) {
      if (chars[i] != 32) {
        chars[i] = (char) (chars[i] - 1);
      }
    }
    String enc = "";
    for (int i = 0; i < str.length(); i++) {
      enc = enc + chars[i];
    }
    super.write(enc, off, len);
  }

}
