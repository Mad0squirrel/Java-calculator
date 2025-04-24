package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calculator {
    private int firstNumber;
    private int secondNumber;
    private String operation;
    private boolean isFirstNumberSet = false;
    private boolean isOperationSet = false;
    private boolean isError = false;

    public void enter(int number) {
        if (!isFirstNumberSet) {
            firstNumber = number;
            isFirstNumberSet = true;
        } else {
            secondNumber = number;
        }
    }

    public void add() {
        operation = "+";
        isOperationSet = true;
    }

    public void subtract() {
        operation = "-";
        isOperationSet = true;
    }

    public void multiply() {
        operation = "*";
        isOperationSet = true;
    }

    public void divide() {
        operation = "/";
        isOperationSet = true;
    }

    public int calculate() {
        if (!isFirstNumberSet || !isOperationSet) {
            throw new IllegalStateException("Calculator is not properly initialized");
        }

        switch (operation) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return firstNumber / secondNumber;
            default:
                throw new UnsupportedOperationException("Unknown operation: " + operation);
        }
    }

    public void reset() {
        firstNumber = 0;
        secondNumber = 0;
        operation = null;
        isFirstNumberSet = false;
        isOperationSet = false;
    }
}