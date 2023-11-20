package org.unibl.etf.calculator;

import org.unibl.etf.exception.DivisionByZeroException;
import org.unibl.etf.exception.NotSupportedOperationException;

public class Calculator {

	private Double currentValue;
	
	public Calculator() {
		currentValue = 0.0;
	}
	
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

	public Double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Double value) {
		this.currentValue = value;
	}
	
}
