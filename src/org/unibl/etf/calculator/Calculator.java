package org.unibl.etf.calculator;

import org.unibl.etf.exception.DivisionByZeroException;
import org.unibl.etf.exception.NotSupportedOperationException;

/**
 * Represents a class used for four basic arithmetic operations.
 * Operations that are supported are addition, subtraction, multiplication and division.
 * There is one number attribute which represents current value of the calculator which is updated after every operation.
 * 
 * @author Novica Tepic
 * @version 1.0
 * @since 2023-11-21
 */
public class Calculator {

	/**
	 * Represents current result, which is current computed value.
	 */
	private Double currentValue;
	
	/**
	 * Represents constructor which initalizes current value to the expected value of 0.0.
	 */
	public Calculator() {
		currentValue = 0.0;
	}
	
	/**
	 * Calculates current value based on passed value and operator.
	 * The first operand is value which is used in combination with the current value in the calculator, performing an operator action.
	 * Result is correctly updated and stored in the calculator.
	 * @param value Double parameter of the method which represents the second operand in the operation.
	 * @param operator Character parameter of the method which represents the operation performed between the stored value in the calculator and passed value, valid operations are '+', '-', '*' and '/'.
	 * @throws DivisionByZeroException Thrown if the value is 0.0 and the operator is '/'. 
	 * @throws NotSupportedOperationException Thrown if operation doesn't equal to '+', '-', '*' or '/'. 
	 */
	public void calculate(Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException {
		
		if(value == null)
			return;
		
		if(operator == '+') {
			currentValue += value;
		}
		else if(operator == '-') {
			currentValue -= value;
		} else if(operator == '*') {
			currentValue *= value;
		} else if(operator == '/') {
			if(value.equals(0.0)) {
				throw new DivisionByZeroException();
			}
			currentValue /= value;
		} else {
			throw new NotSupportedOperationException();
		}
		
	}

	/**
	 * Retrieves the value which represents current value in the calculator.
	 * @return The value stored in the calculator.
	 */
	public Double getCurrentValue() {
		return currentValue;
	}

	/**
	 * Sets new value in the calculator, which replaces the current stored value in the calculator.
	 * @param value Double value which replaces the current stored value in the calculator.
	 */
	public void setCurrentValue(Double value) {
		this.currentValue = value;
	}
	
}
