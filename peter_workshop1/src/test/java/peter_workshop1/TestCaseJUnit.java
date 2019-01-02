package peter_workshop1;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCaseJUnit {

	  @Test(expected = IllegalArgumentException.class)
	  public void testExceptionIsThrown() {
		  TestCase tester = new TestCase();
	    tester.multiply(1000, 5);
	  }

	  @Test
	  public void testMultiply() {
		  TestCase tester = new TestCase();
	    assertEquals("10 x 5 must be 50", 50, tester.multiply(10, 5));
	  }
	}

