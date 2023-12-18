package org.unibl.etf.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.unibl.etf.calculator.Calculator;
import org.unibl.etf.exception.DivisionByZeroException;
import org.unibl.etf.exception.NotSupportedOperationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("Calculator Tests")
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

	@DisplayName("Constructor Test")
	@Test
	void testCalculator() {
		assertThat(calculator, notNullValue());
		assertThat(calculator.getCurrentValue(), is(0.0));
	}

	@DisplayName("Addition Tests")
	@ParameterizedTest
	@MethodSource("correctPlusParameters")
	void testCorrectAddition(Double value, char operator) throws Exception {
		calculator.calculate(value, operator);
		assertThat(calculator.getCurrentValue(),is(value));
		
	}
	
	@DisplayName("Subtraction Tests")
	@ParameterizedTest
	@MethodSource("correctMinusParameters")
	void testCorrectSubtraction(Double value, char operator) throws Exception {
		calculator.calculate(value, operator);
		assertThat(calculator.getCurrentValue(),is(-value));
	}
	
	@DisplayName("Multiplication Tests")
	@ParameterizedTest
	@MethodSource("correctMultiplyParameters")
	void testCorrectMultiplication(Double value, char operator) throws Exception {
		calculator.setCurrentValue(4.1);
		Double oldValue = calculator.getCurrentValue();
		calculator.calculate(value, operator);
		assertThat(calculator.getCurrentValue(),is(oldValue * value));
	}
	
	@DisplayName("Division Tests")
	@ParameterizedTest
	@MethodSource("correctDivisonParameters")
	void testCorrectDivison(Double value, char operator) throws Exception {
		calculator.setCurrentValue(4.1);
		Double oldValue = calculator.getCurrentValue();
		calculator.calculate(value, operator);
		assertThat(calculator.getCurrentValue(),is(oldValue / value));
	}
	
	@DisplayName("Null Value Test")
	@Test
	void testNullValue() throws Exception {
		calculator.setCurrentValue(4.0);
		calculator.calculate(null, '+');
		assertThat(calculator.getCurrentValue(),is(Double.valueOf(4.0)));
	}
	
	@DisplayName("Exceptions Tests")
	@ParameterizedTest
	@MethodSource("exceptionTests")
	void calculateTestExceptions(Double currentValue, Double value, char option, Class<? extends Exception> exceptionClass) throws Exception {
		calculator.setCurrentValue(currentValue);
		Exception exc = assertThrows(exceptionClass, () -> calculator.calculate(value, option));
		assertThat(exc, is(instanceOf(exceptionClass)));
	}

	@DisplayName("Calculator Get Current Value Test")
	@Test
	void testGetCurrentValue() {
		assertThat(calculator.getCurrentValue(), is(0.0));
	}

	@DisplayName("Calculator Set Current Value Test")
	@Test
	void testSetCurrentValue() {
		calculator.setCurrentValue(1.0);
		assertThat(calculator.getCurrentValue(), is(1.0));
	}
	
	private static Stream<Arguments> exceptionTests() {
		return Stream.of(Arguments.of(0.0, 10.0, '#', NotSupportedOperationException.class),
				Arguments.of(10.0, 0.0, '/', DivisionByZeroException.class));
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
