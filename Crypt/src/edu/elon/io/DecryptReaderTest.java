/**
 * DecryptReaderTest.java 1.0 Oct 1, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.io;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.CharBuffer;

/**
 * This class tests the DecryptWriter class.
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public class DecryptReaderTest {
  
  private String str;
  private String expected;
  private DecryptReader dr;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    str = "Gdkkn CDE-345";
    expected = "Hello DEF.456";
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    str = null;
    expected = null;
  }

  /**
   * Test method for {@link edu.elon.io.DecryptReader#read()}.
   * @throws IOException 
   */
  @Test
  public void testRead() throws IOException {
    dr = new DecryptReader(new StringReader(str));
    int i = 0;
    String actual = "";
    while((i = dr.read()) != -1){
     actual = actual + (char) i;
    }
    System.out.println(expected + ":" + actual);
    assertEquals(expected, actual);
  }

  /**
   * Test method for {@link edu.elon.io.DecryptReader#read(char[])}.
   * @throws IOException 
   */
  @Test
  public void testReadCharArray() throws IOException {
    dr = new DecryptReader(new StringReader(str));
    String actual = "";
    char[] chars = str.toCharArray();
    StringWriter sw = new StringWriter();
    int len = dr.read(chars);
    for (int i = 0; i < len; i++){
      actual = actual + chars[i];
    }
    System.out.println(expected + ":" + actual);
    assertEquals(expected, actual);
  }

  /**
   * Test method for {@link edu.elon.io.DecryptReader#read(char[], int, int)}.
   * @throws IOException 
   */
  @Test
  public void testReadCharArrayIntInt() throws IOException {
    dr = new DecryptReader(new StringReader(str));
    String actual = "";
    char[] chars = str.toCharArray();
    int len = dr.read(chars, 0, chars.length);
    for (int i = 0; i < len; i++){
      actual = actual + chars[i];
    }
    System.out.println(expected + ":" + actual);
    assertEquals(expected, actual);
  }

  /**
   * Test method for {@link edu.elon.io.DecryptReader#read(java.nio.CharBuffer)}.
   * @throws IOException 
   */
  @Test
  public void testReadCharBuffer() throws IOException {
    dr = new DecryptReader(new StringReader(str));
    String actual = "";
    CharBuffer charbuff = CharBuffer.wrap(str.toCharArray());
    int len = dr.read(charbuff);
    for (int i = 0; i < len; i++){
      actual = actual + charbuff.get(i);
    }
    System.out.println(expected + ":" + actual);
    assertEquals(expected, actual);
  }

}
