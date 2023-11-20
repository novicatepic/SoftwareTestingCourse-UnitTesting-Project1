package org.unibl.etf.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import javax.naming.OperationNotSupportedException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.unibl.etf.calculator.CalculatorAdvanced;
import org.unibl.etf.exception.NotSupportedOperationException;
import org.unibl.etf.exception.NumberNotInAreaException;

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

	@Test
	void testCalculatorAdvanced() {
		assertThat(calculatorAdvanced, notNullValue());
	}


	@ParameterizedTest
	@MethodSource("correctFactorialParameters")
	void testCorrectFactorial(Double value, char operator) {
		try {
			calculatorAdvanced.setCurrentValue(value);
			calculatorAdvanced.calculateAdvanced(operator);
			if(value == 10) {
				assertThat(calculatorAdvanced.getCurrentValue(), is(3628800.0));
			}
			else if(value >= 3) {
				assertThat(calculatorAdvanced.getCurrentValue(), is(6.0));
			} else {
				assertThat(calculatorAdvanced.getCurrentValue(), is(2.0));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	void testFactorialZero() {
		try {
			calculatorAdvanced.setCurrentValue(0.0);
			calculatorAdvanced.calculateAdvanced('!');
			double delta = 0.0001;
			assertThat(calculatorAdvanced.getCurrentValue(), is(closeTo(1.0, delta)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@ParameterizedTest
	@MethodSource("factorialParametersZeroAndOne")
	void testCorrectFactorialZeroAndOne(Double value, char operator) {
		try {
			calculatorAdvanced.setCurrentValue(value);
			calculatorAdvanced.calculateAdvanced(operator);
			assertThat(calculatorAdvanced.getCurrentValue(), is(1.0));
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@ParameterizedTest
	@MethodSource("parameterActionOutOfBounds")
	void testOutOfBounds(Double value, char operator) {
		try {
			calculatorAdvanced.setCurrentValue(value);
			Exception exception = assertThrows(NotSupportedOperationException.class, () -> {
				calculatorAdvanced.calculateAdvanced(operator);
	        });
			assertThat(exception, is(instanceOf(NotSupportedOperationException.class)));
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@ParameterizedTest
	@MethodSource("parameterActionInBounds")
	void testInBounds(Double value, char operator) {
		try {
			calculatorAdvanced.setCurrentValue(value);
			calculatorAdvanced.calculateAdvanced(operator);
			if(value == 0.0) {			
				assertThat(calculatorAdvanced.getCurrentValue(), is(1.0));
			} else if(value == 9.0) {
				assertThat(calculatorAdvanced.getCurrentValue(), is(512.00));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@ParameterizedTest
	@MethodSource("factorialNumberNotInArea")
	void testFactorialNumberNotInArea(Double value, char operator) {
		try {
			calculatorAdvanced.setCurrentValue(value);
			Exception exception = assertThrows(NumberNotInAreaException.class, () -> {
				calculatorAdvanced.calculateAdvanced(operator);
	        });
			assertThat(exception, is(instanceOf(NumberNotInAreaException.class)));
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@ParameterizedTest
	@MethodSource("factorialNumberBorderArea")
	void factorialNumberBorderArea(char operator) {
		try {
			calculatorAdvanced.setCurrentValue(2.0);
			calculatorAdvanced.calculateAdvanced(operator);
			if(operator == '1') {
				assertThat(calculatorAdvanced.getCurrentValue(), is(2.0));
			} else if(operator == '9') {
				assertThat(calculatorAdvanced.getCurrentValue(), is(512.0));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	void testIncorrectAction() {
		try {
			calculatorAdvanced.setCurrentValue(5.0);
			Exception exception = assertThrows(NotSupportedOperationException.class, () -> {
				calculatorAdvanced.calculateAdvanced('#');
	        });
			assertThat(exception, is(instanceOf(NotSupportedOperationException.class)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testActionZero() {
		try {
			calculatorAdvanced.setCurrentValue(5.0);
			calculatorAdvanced.calculateAdvanced('0');
			assertThat(calculatorAdvanced.getCurrentValue(), is(1.0));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testCharacteristicsZero() {
		try {
			calculatorAdvanced.setCurrentValue(0.99);
			Exception exception = assertThrows(NumberNotInAreaException.class, () -> {
				calculatorAdvanced.hasCharacteristic('A');
	        });
			assertThat(exception, is(instanceOf(NumberNotInAreaException.class)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@ParameterizedTest
	@MethodSource("wrongArmstrongParameters")
	void testWrongArmstrongParameters(Double value, char operator) {
		try {
			calculatorAdvanced.setCurrentValue(value);
			boolean has = calculatorAdvanced.hasCharacteristic(operator);
			assertThat(has, is(false));
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@ParameterizedTest
	@MethodSource("correctArmstrongParameters")
	void testCorrectArmstrongParameters(Double value, char operator) {
		try {
			calculatorAdvanced.setCurrentValue(value);
			boolean has = calculatorAdvanced.hasCharacteristic(operator);
			assertThat(has, is(true));
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@ParameterizedTest
	@MethodSource("correctPerfectNumberParameters")
	void testCorrectPerfectNumber(Double value, char operator) {
		try {
			calculatorAdvanced.setCurrentValue(value);
			boolean has = calculatorAdvanced.hasCharacteristic(operator);
			assertThat(has, is(true));
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@ParameterizedTest
	@MethodSource("wrongPerfectNumberParameters")
	void testWrongPerfectNumber(Double value, char operator) {
		try {
			calculatorAdvanced.setCurrentValue(value);
			boolean has = calculatorAdvanced.hasCharacteristic(operator);
			assertThat(has, is(false));
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	void testCharacteristicsInvalidOperation() {
		try {
			calculatorAdvanced.setCurrentValue(1.0);
			Exception exception = assertThrows(OperationNotSupportedException.class, () -> {
				calculatorAdvanced.hasCharacteristic('G');
	        });
			assertThat(exception, is(instanceOf(OperationNotSupportedException.class)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*========================*/
	/*========================*/
	/*========================*/
	/*Calculate Advanced Tests*/
	/*========================*/
	/*========================*/
	/*========================*/
	
	
	/*========================*/
	/*========================*/
	/*========================*/
	/*Factorial Tests*/
	/*========================*/
	/*========================*/
	/*========================*/
	
	private static Stream<Arguments> correctFactorialParameters() {
		return Stream.of(
			Arguments.of(3.1,'!'),
			Arguments.of(3.0,'!'),
			Arguments.of(2.9,'!'),
			Arguments.of(10.0,'!')
			
		);
	}
	
	private static Stream<Arguments> factorialParametersZeroAndOne() {
		return Stream.of(
			Arguments.of(0.0,'!'),
			Arguments.of(0.1,'!'),
			Arguments.of(1.0,'!'),
			Arguments.of(1.1,'!')
		);
	}
	
	private static Stream<Arguments> factorialNumberNotInArea() {
		return Stream.of(
			/*Arguments.of(Math.nextDown(0.0),'!'),
			Arguments.of(Math.nextUp(10.0),'!'),*/
			//Arguments.of(-0.1,'!'),
			Arguments.of(11.1,'!')
		);
	}
	
	private static Stream<Arguments> factorialNumberBorderArea() {
		return Stream.of(
			Arguments.of('1'),
			Arguments.of('9')
		);
	}
	
	/*========================*/
	/*========================*/
	/*========================*/
	/*Parameter bounds tests*/
	/*========================*/
	/*========================*/
	/*========================*/
	
	private static Stream<Arguments> parameterActionOutOfBounds() {
		return Stream.of(
			Arguments.of(2.0, '/'), //Because / is before '0'
			Arguments.of(2.0, ':') //Because : is after '9'
		);
	}
	
	private static Stream<Arguments> parameterActionInBounds() {
		return Stream.of(
			Arguments.of(2.0, '0'), //lower bound
			Arguments.of(2.0, '9') //upper bound
		);
	}
	
	
	
	/*========================*/
	/*========================*/
	/*========================*/
	/*Armstrong tests*/
	/*========================*/
	/*========================*/
	/*========================*/
	
	
	
	private static Stream<Arguments> correctArmstrongParameters() {
		return Stream.of(
			Arguments.of(153.00, 'A'),
			Arguments.of(1634.00, 'A'),
			Arguments.of(153.66, 'A'),
			Arguments.of(1634.1, 'A')
		);
	}
	
	private static Stream<Arguments> wrongArmstrongParameters() {
		return Stream.of(
			Arguments.of(14.4, 'A'),
			Arguments.of(20.9, 'A')
		);
	}
	
	/*========================*/
	/*========================*/
	/*========================*/
	/*Perfect number tests*/
	/*========================*/
	/*========================*/
	/*========================*/
	
	private static Stream<Arguments> correctPerfectNumberParameters() {
		return Stream.of(
			Arguments.of(1.1, 'P'),
			Arguments.of(1.0, 'P'),
			Arguments.of(6.1, 'P'),
			Arguments.of(6.0, 'P'),
			Arguments.of(28.9, 'P'),
			Arguments.of(496.0, 'P'),
			Arguments.of(8128.1, 'P')
		);
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
}
