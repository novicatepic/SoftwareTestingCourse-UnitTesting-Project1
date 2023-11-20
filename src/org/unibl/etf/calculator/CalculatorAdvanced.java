package org.unibl.etf.calculator;

import javax.naming.OperationNotSupportedException;

import org.unibl.etf.exception.NotSupportedOperationException;
import org.unibl.etf.exception.NumberNotInAreaException;

public class CalculatorAdvanced extends Calculator {

	public void calculateAdvanced(char action) throws NotSupportedOperationException, NumberNotInAreaException {
		
		Double currentValue = getCurrentValue();
		
		if(action != '!') {
			if(action < '0' || action > '9') {
				throw new NotSupportedOperationException();
			} 
		}
		
		if(action == '!') {
			Integer currentValueInteger = currentValue.intValue();
			if(currentValueInteger >= 0 && currentValueInteger <= 10) {	
				calculateFactorial();
			} else {
				throw new NumberNotInAreaException();
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
	
	private void calculateFactorial() {
		Double currentValue = getCurrentValue();
		Integer currentValueInteger = currentValue.intValue();

        if (currentValueInteger == 0 || currentValueInteger == 1) {
            setCurrentValue(1.0);
        } else {
        	double result = 1.0;
            for (int i = 2; i <= currentValueInteger; i++) {
            	result *= i;
            }
            setCurrentValue(result);
        }       
	}
	
	public Boolean hasCharacteristic(char value) throws NumberNotInAreaException, OperationNotSupportedException {
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
			throw new OperationNotSupportedException();
		}
		
		return false;
	}
	
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
	
	private static int powerWithLoop(double base, int exponent) {
        int result = 1;

        for (int i = 0; i < exponent; i++) {
            result *= base;
        }

        return result;
    }
	
	private boolean isPerfectNumber() {
		Double currentValue = getCurrentValue();
		Integer currentValueInteger = currentValue.intValue();

		if(currentValueInteger == 1) {
			return true;
		}
		
		if(currentValueInteger < 6) {
			return false;
		}
		
		// Calculate the sum of positive divisors
		long sum = 1; // Start with 1 since every number is a divisor of itself
		for (int i = 2; i <= currentValueInteger / 2; i++) {
		    if (currentValueInteger % i == 0) {
		        sum += i;
		    }
		}

		// Check if the sum equals the original value
		return sum == currentValueInteger;
    }

	
}
