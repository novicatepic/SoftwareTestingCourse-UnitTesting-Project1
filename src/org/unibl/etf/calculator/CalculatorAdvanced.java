package org.unibl.etf.calculator;

import org.unibl.etf.exception.NotSupportedOperationException;
import org.unibl.etf.exception.NumberNotInAreaException;

/**
 * Represents a class used for advanced calculations, extends Calculator class.
 * Supports factorial and graduation computation, as well as checking if a number is Armstrong or perfect number.
 * 
 * 
 * @author Novica Tepic
 * @version 1.0
 * @since 2023-11-21
 */
public class CalculatorAdvanced extends Calculator {

	/**
	 * Calculates factorial or graduation of a number based on action parameter.
	 * Factorial is calculated only if current value in the calculator is between 0 and 10 and the action parameter is '!'.
	 * Graduation is calculated only if action parameter is between '0' and '9'.
	 * 
	 * @param action Character based on which calculator performs operations, valid characters are '!' and between '0' and '9'.
	 * @throws NotSupportedOperationException Thrown if operation is not '!' or is not between '0' and '9'.
	 * @throws NumberNotInAreaException Thrown if the current value in the calculator is not between 0 and 10 when performing factorial operation.
	 */
	public void calculateAdvanced(char action) throws NotSupportedOperationException, NumberNotInAreaException {
		
		Double currentValue = getCurrentValue();
		
		if(action != '!') {
			if(action < '0' || action > '9') {
				throw new NotSupportedOperationException();
			} 
		}
		
		if(action == '!') {
			if(currentValue < 0 || currentValue > 10) {
				throw new NumberNotInAreaException();
			}
			Integer currentValueInteger = currentValue.intValue();

			if (currentValueInteger == 0 || currentValueInteger == 1) {
	            setCurrentValue(1.0);
	        } else if(currentValueInteger <= 10) {
	        	calculateFactorial();
	        } 
		} else {
			if(action != '0') {
				int intAction = Character.getNumericValue(action);
				Integer currentValueInt = currentValue.intValue();
				Integer temp = currentValueInt;
				for(int i=1; i<intAction; i++) {
					currentValueInt *= temp;
				}
				setCurrentValue(currentValueInt.doubleValue());
			} else {
				setCurrentValue(1.0);
			}
			
		}
	}
	
	/**
	 * Calculates factorial for numbers greater or equal to 2.
	 */
	private void calculateFactorial() {
		Double currentValue = getCurrentValue();
		Integer currentValueInteger = currentValue.intValue();

    	double result = 1.0;
        for (int i = 2; i <= currentValueInteger; i++) {
        	result *= i;
        }
        setCurrentValue(result);      
	}
	
	/**
	 * Returns whether the integer part of the number is Armstrong number or a perfect number.
	 * @param value Character which determines whether to check if a number is Armstrong number or a number is a perfect number. For Armstrong valid character is 'A', for perfect it is 'P'.
	 * @return true if the current value in the calculator meets required conditions, false otherwise.
	 * @throws NumberNotInAreaException Thrown is current value in the calculator is less than 1.
	 * @throws OperationNotSupportedException Thrown if parameter of the method is different from 'A' or 'P'.
	 */
	public Boolean hasCharacteristic(char value) throws NumberNotInAreaException, NotSupportedOperationException {
		Double currentValue = getCurrentValue();
		
		if(currentValue < 1) {
			throw new NumberNotInAreaException();
		}
		if(value == 'A') {
			if(isArmstrongNumber()) {
				return true;
			}
		} else if(value == 'P') {
			if(isPerfectNumber()) {
				return true;
			}
		} else {
			throw new NotSupportedOperationException();
		}
		
		return false;
	}
	
	/**
	 * Checks whether Integer part of the calculator is an Armstrong number.
	 * @return true if a number is an Armstrong number, false otherwise.
	 */
	private boolean isArmstrongNumber() {
		Double currentValue = getCurrentValue();
		Integer currentValueInteger = currentValue.intValue();
		
        String numberStr = String.valueOf(currentValue.intValue());
        int length = numberStr.length();
        
        double sum = 0.0;    
        while (currentValueInteger > 0) {
            double digit = currentValueInteger % 10;
            sum += powerWithLoop(digit, length);     
            currentValueInteger /= 10;
        }
        
        return sum == currentValue.intValue();
    }
	
	/**
	 * Calculates graduation of a number.
	 * @param base Double value which represents base when calculating graduation.
	 * @param exponent Integer value which represents the value on which base will be graduated.
	 * @return Result of the graduation.
	 */
	private int powerWithLoop(double base, int exponent) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
	
	/**
	 * Checks whether a number is a perfect number.
	 * @return true if a number is a perfect number, false otherwise.
	 */
	private boolean isPerfectNumber() {
		Double currentValue = getCurrentValue();
		Integer currentValueInteger = currentValue.intValue();

		if(currentValueInteger == 1) {
			return true;
		}
		
		if(currentValueInteger < 6) {
			return false;
		}
		
		long sum = 1; 
		for (int i = 2; i <= currentValueInteger / 2; i++) {
		    if (currentValueInteger % i == 0) {
		        sum += i;
		    }
		}
		return sum == currentValueInteger;
    }
}
