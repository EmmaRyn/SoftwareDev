/**
 * AllTests.java 1.0 Oct 2, 2014
 *
 * Copyright (c) 2014 Emma Kwiatkowski. All Rights Reserved
 */
package edu.elon.io;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This class runs the DecryptReader and EncryptWriter test classes.
 * 
 * @author ekwiatkowski
 * @version 1.0
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ DecryptReaderTest.class, EncryptWriterTest.class })
public class AllTests {

}
