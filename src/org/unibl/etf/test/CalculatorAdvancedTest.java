package org.unibl.etf.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.unibl.etf.calculator.CalculatorAdvanced;
import org.unibl.etf.exception.DivisionByZeroException;
import org.unibl.etf.exception.NotSupportedOperationException;
import org.unibl.etf.exception.NumberNotInAreaException;

@DisplayName("Advanced Calculator Tests")
class CalculatorAdvancedTest {

	private CalculatorAdvanced calculatorAdvanced;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculatorAdvanced = new CalculatorAdvanced();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@DisplayName("Constructor Tests")
	@Test
	void testCalculatorAdvanced() {
		assertThat(calculatorAdvanced, notNullValue());
		assertThat(calculatorAdvanced.getCurrentValue(), is(0.0));
	}

	@DisplayName("Factorial Correct Tests")
	@ParameterizedTest
	@MethodSource("correctFactorialParameters")
	void testCorrectFactorial(Double value, char operator, Double result) throws Exception {
		calculatorAdvanced.setCurrentValue(value);
		calculatorAdvanced.calculateAdvanced(operator);
		assertThat(calculatorAdvanced.getCurrentValue(), is(result));	
	}
	
	private static Stream<Arguments> correctFactorialParameters() {
		return Stream.of(
			Arguments.of(0.0,'!', 1.0),
			Arguments.of(10.0,'!', 3628800.0)	
		);
	}
	
	@DisplayName("Factorial Incorrect Tests")
	@ParameterizedTest
	@MethodSource("factorialNumberNotInArea")
	void testFactorialNumberNotInArea(Double value, char operator) throws Exception {
		calculatorAdvanced.setCurrentValue(value);
		Exception exception = assertThrows(NumberNotInAreaException.class, () -> {
			calculatorAdvanced.calculateAdvanced(operator);
        });
		assertThat(exception, is(instanceOf(NumberNotInAreaException.class)));	
	}
	
	private static Stream<Arguments> factorialNumberNotInArea() {
		return Stream.of(
			Arguments.of(-0.01,'!'),
			Arguments.of(10.01,'!')
		);
	}
	
	
	@DisplayName("Wrong Parameter Tests")
	@ParameterizedTest
	@MethodSource("parameterActionOutOfBounds")
	void testOutOfBounds(Double value, char operator) throws Exception {
		calculatorAdvanced.setCurrentValue(value);
		Exception exception = assertThrows(NotSupportedOperationException.class, () -> {
			calculatorAdvanced.calculateAdvanced(operator);
        });
		assertThat(exception, is(instanceOf(NotSupportedOperationException.class)));
	}
	
	private static Stream<Arguments> parameterActionOutOfBounds() {
		return Stream.of(
			Arguments.of(2.0, '/'), //Because / is before '0'
			Arguments.of(2.0, ':'), //Because : is after '9',
			Arguments.of(2.0, '\u002F'), //equivalent to '/'
			Arguments.of(2.0, '\u003A'), //equivalent to ':'
			Arguments.of(2.0, 'x')
		);
	}
	
	@DisplayName("Correct Graduation Tests")
	@ParameterizedTest
	@MethodSource("parameterActionInBounds")
	void testInBounds(Double value, char operator, Double result) throws Exception {
		calculatorAdvanced.setCurrentValue(value);
		calculatorAdvanced.calculateAdvanced(operator);
		assertThat(calculatorAdvanced.getCurrentValue(), is(result));
	}
	
	private static Stream<Arguments> parameterActionInBounds() {
		return Stream.of(
			Arguments.of(0.0, '0', 1.0), //zero power
			Arguments.of(5.0, '1', 5.0), //power one
			Arguments.of(2.0, '0', 1.0), //lower bound
			Arguments.of(2.0, '9', 512.0), //upper bound
			Arguments.of(2.0, '\u0030', 1.0), //equivalent to '0'
			Arguments.of(2.0, '\u0039', 512.0) //equivalent to '9'
		);
	}
	
	@DisplayName("Incorrect Armstrong Tests")
	@ParameterizedTest
	@MethodSource("wrongArmstrongParameters")
	void testWrongArmstrongParameters(Double value, char operator) throws Exception {
		calculatorAdvanced.setCurrentValue(value);
		boolean has = calculatorAdvanced.hasCharacteristic(operator);
		assertThat(has, is(false));			
	}
	
	private static Stream<Arguments> wrongArmstrongParameters() {
		return Stream.of(
			Arguments.of(14.4, 'A'),
			Arguments.of(20.9, 'A')
		);
	}
	
	@DisplayName("Correct Armstrong Tests")
	@ParameterizedTest
	@MethodSource("correctArmstrongParameters")
	void testCorrectArmstrongParameters(Double value, char operator) throws Exception {
		calculatorAdvanced.setCurrentValue(value);
		boolean has = calculatorAdvanced.hasCharacteristic(operator);
		assertThat(has, is(true));		
	}
	
	private static Stream<Arguments> correctArmstrongParameters() {
		return Stream.of(
			Arguments.of(153.1, 'A'),
			Arguments.of(1634.5, 'A'),
			Arguments.of(153.9, 'A'),
			Arguments.of(1634.6, 'A')
		);
	}
	
	@DisplayName("Correct Perfect Number Tests")
	@ParameterizedTest
	@MethodSource("correctPerfectNumberParameters")
	void testCorrectPerfectNumber(Double value, char operator) throws Exception {
		calculatorAdvanced.setCurrentValue(value);
		boolean has = calculatorAdvanced.hasCharacteristic(operator);
		assertThat(has, is(true));	
	}
	
	private static Stream<Arguments> correctPerfectNumberParameters() {
		return Stream.of(
			Arguments.of(1.1, 'P'),
			Arguments.of(1.0, 'P'),
			Arguments.of(6.1, 'P'),
			Arguments.of(6.0, 'P'),
			Arguments.of(28.6, 'P'),
			Arguments.of(496.9, 'P'),
			Arguments.of(8128.1, 'P')
		);
	}
	
	@DisplayName("Wrong Perfect Number Tests")
	@ParameterizedTest
	@MethodSource("wrongPerfectNumberParameters")
	void testWrongPerfectNumber(Double value, char operator) throws Exception {
		calculatorAdvanced.setCurrentValue(value);
		boolean has = calculatorAdvanced.hasCharacteristic(operator);
		assertThat(has, is(false));	
	}
	
	private static Stream<Arguments> wrongPerfectNumberParameters() {
		return Stream.of(
			Arguments.of(2.1, 'P'),
			Arguments.of(2.0, 'P'),
			Arguments.of(15.1, 'P'),
			Arguments.of(14.9, 'P'),
			Arguments.of(5.99, 'P')
		);
	}
	
	@DisplayName("Exception Tests")
	@ParameterizedTest
	@MethodSource("exceptionTests")
	void testExceptions(Double value, char operator, Class<? extends Exception> exceptionClass) throws Exception {
		calculatorAdvanced.setCurrentValue(value);
		Exception exc = assertThrows(exceptionClass, () -> calculatorAdvanced.hasCharacteristic(operator));
		assertThat(exc, is(instanceOf(exceptionClass)));	
	}
	
	private static Stream<Arguments> exceptionTests() {
		return Stream.of(Arguments.of(1.0, 'G', NotSupportedOperationException.class),
				Arguments.of(0.99, 'A', NumberNotInAreaException.class));
	}
}
