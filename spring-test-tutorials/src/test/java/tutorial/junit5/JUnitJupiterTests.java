package tutorial.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tutorial.Calculator;

class JUnitJupiterTests {

	private final Calculator calculator = new Calculator();

    @Test
    void addition() {
    	Assertions.assertEquals(2, calculator.add(1, 1));
    }
	
}
