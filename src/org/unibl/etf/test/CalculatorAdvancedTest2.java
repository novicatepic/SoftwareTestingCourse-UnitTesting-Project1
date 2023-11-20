package org.unibl.etf.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

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

class CalculatorAdvancedTest2 {
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

	@ParameterizedTest
	@MethodSource("valid")
	void testValid(Double value, char operator) {
		try {
			calculatorAdvanced.setCurrentValue(value);
			calculatorAdvanced.calculateAdvanced(operator);
			assertThat(calculatorAdvanced.getCurrentValue(), is(1.0));
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@ParameterizedTest
	@MethodSource("notValid")
	void testNotValid(Double value, char operator) {
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
	
	private static Stream<Arguments> valid() {
		return Stream.of(
			Arguments.of(1.0,'0'),
			Arguments.of(1.0,'9'),
			Arguments.of(1.0,'1'),
			Arguments.of(1.0,'2'),
			Arguments.of(1.0,'5')
		);
	}
	
	private static Stream<Arguments> notValid() {
		return Stream.of(
			Arguments.of(0.0,':'),
			Arguments.of(0.1,'/'),
			Arguments.of(1.0,'h')
		);
	}

}
