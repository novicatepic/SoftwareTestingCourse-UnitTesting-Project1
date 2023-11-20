package org.unibl.etf.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.unibl.etf.calculator.Calculator;
import org.unibl.etf.exception.DivisionByZeroException;
import org.unibl.etf.exception.NotSupportedOperationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CalculatorTest {
	private Calculator calculator;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculator = new Calculator();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCalculator() {
		assertThat(calculator, notNullValue());
	}

	@ParameterizedTest
	@MethodSource("correctPlusParameters")
	void testCorrectAddition(Double value, char operator) {
		try {
			calculator.calculate(value, operator);
			assertThat(calculator.getCurrentValue(),is(value));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@ParameterizedTest
	@MethodSource("correctMinusParameters")
	void testCorrectSubtraction(Double value, char operator) {
		try {
			calculator.calculate(value, operator);
			assertThat(calculator.getCurrentValue(),is(-value));
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@ParameterizedTest
	@MethodSource("correctMultiplyParameters")
	void testCorrectMultiplication(Double value, char operator) {
		try {
			calculator.setCurrentValue(4.1);
			Double oldValue = calculator.getCurrentValue();
			calculator.calculate(value, operator);
			assertThat(calculator.getCurrentValue(),is(oldValue * value));
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@ParameterizedTest
	@MethodSource("correctDivisonParameters")
	void testCorrectDivison(Double value, char operator) {
		try {
			calculator.setCurrentValue(4.1);
			Double oldValue = calculator.getCurrentValue();
			calculator.calculate(value, operator);
			assertThat(calculator.getCurrentValue(),is(oldValue / value));
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	void testNullValue() {
		try {
			calculator.setCurrentValue(4.0);
			calculator.calculate(null, '+');
			assertThat(calculator.getCurrentValue(),is(Double.valueOf(4.0)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testDivisionByZero() {
		try {
			calculator.setCurrentValue(4.0);
			Exception exception = assertThrows(DivisionByZeroException.class, () -> {
				calculator.calculate(0.0, '/');
	        });
			assertThat(exception, is(instanceOf(DivisionByZeroException.class)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testSubtractionByZero() {
		try {
			calculator.setCurrentValue(4.0);
			Double oldValue = calculator.getCurrentValue();
			calculator.calculate(0.0, '-');
			assertThat(calculator.getCurrentValue(), is(oldValue));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testNotSupportedOperation() {
		try {
			calculator.setCurrentValue(4.0);
			Exception exception = assertThrows(NotSupportedOperationException.class, () -> {
				calculator.calculate(1.0, '#');
	        });
			assertThat(exception, is(instanceOf(NotSupportedOperationException.class)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGetCurrentValue() {
		assertThat(calculator.getCurrentValue(), is(0.0));
	}

	@Test
	void testSetCurrentValue() {
		calculator.setCurrentValue(1.0);
		assertThat(calculator.getCurrentValue(), is(1.0));
	}
	
	private static Stream<Arguments> correctPlusParameters() {
		return Stream.of(
			Arguments.of(2.1,'+'),
			Arguments.of(-4.1,'+'),
			Arguments.of(0.0,'+')
		);
	}
	
	private static Stream<Arguments> correctMinusParameters() {
		return Stream.of(
			Arguments.of(-0.1,'-'),
			Arguments.of(0.1,'-')
		);
	}
	
	private static Stream<Arguments> correctMultiplyParameters() {
		return Stream.of(
			Arguments.of(-0.1,'*'),
			Arguments.of(0.0,'*'),
			Arguments.of(0.1,'*')
		);
	}

	private static Stream<Arguments> correctDivisonParameters() {
		return Stream.of(
			Arguments.of(-0.1,'/'),
			Arguments.of(0.1,'/')
		);
	}
	
}
