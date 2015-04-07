/**
 * EncryptWriterTest.java 1.0 Oct 1, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.io;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * This class tests the EncryptWriter class.
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
public class EncryptWriterTest {

  private EncryptWriter ew;
  private String expected;
  private String str;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    str = "Hello DEF.456";
    expected = "Gdkkn CDE-345";
    ew = new EncryptWriter(new CharArrayWriter());
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    str = null;
    expected = "";
  }

  /**
   * Test method for {@link edu.elon.io.EncryptWriter#write(char[])}.
   * 
   * @throws IOException
   */
  @Test
  public void testWriteCharArray() throws IOException {
    char[] chars = str.toCharArray();
    String actual = "";
    ew.write(chars);
    for (int i = 0; i < chars.length; i++) {
      actual = actual + chars[i];
    }
    assertEquals(expected, actual);
  }

  /**
   * Test method for
   * {@link edu.elon.io.EncryptWriter#write(char[], int, int)}.
   * 
   * @throws IOException
   */
  @Test
  public void testWriteCharArrayIntInt() throws IOException {
    char[] chars = str.toCharArray();
    String actual = "";
    int off = 0;
    int len = chars.length;
    ew.write(chars, off, len);
    for (int i = off; i < len; i++) {
      actual = actual + chars[i];
    }
    assertEquals(expected, actual);
  }

  /**
   * Test method for {@link edu.elon.io.EncryptWriter#write(int)}.
   * 
   * @throws IOException
   */
  @Test
  public void testWriteInt() throws IOException {
    char[] chars = str.toCharArray();
    String actual = "";
    for (char c : chars){
      ew.write(c);
      actual = actual + c;
    }
    System.out.println("1: " + str);
    assertEquals(expected, actual);
  }

  /**
   * Test method for
   * {@link edu.elon.io.EncryptWriter#write(java.lang.String)}.
   * 
   * @throws IOException
   */
  @Test
  public void testWriteString() throws IOException {
    ew.write(str);
    String actual = new String(str.toCharArray());
    System.out.println("2: " + actual);
    assertEquals(expected, actual);
  }

  /**
   * Test method for
   * {@link edu.elon.io.EncryptWriter#write(java.lang.String, int, int)}
   * .
   * 
   * @throws IOException
   */
  @Test
  public void testWriteStringIntInt() throws IOException {
    char[] chars = str.toCharArray();
    int off = 0;
    int len = chars.length;
    ew.write(str, off, len);
    String actual = str;
    System.out.println("3: " + str);
    assertEquals(expected, actual);
  }

}
