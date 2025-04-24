package org.example.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.example.Calculator;

import static org.junit.Assert.*;

public class CalculatorSteps {
    private Calculator calculator;
    private int result;

    @Дано("^я ввел в калькулятор число (\\d+)$")
    public void яВвелВКалькуляторЧисло(int number) throws Throwable {
        calculator = new Calculator();
        boolean tempBoolean = calculator.isFirstNumberSet();
        calculator.enter(number);
        if (tempBoolean) {
            assertEquals(calculator.getSecondNumber(), number);
        } else {
            assertEquals(calculator.getFirstNumber(), number);
        }
    }

    @Когда("^я нажимаю кнопку \"([^\"]*[^C])\"$")
    public void яНажимаюКнопку(String operation) throws Throwable {
        switch (operation) {
            case "+":
                calculator.add();
                break;
            case "-":
                calculator.subtract();
                break;
            case "*":
                calculator.multiply();
                break;
            case "/":
                calculator.divide();
                break;
            case "=":
                try {
                    result = calculator.calculate();
                } catch (Exception e) {
                    calculator.setError(true);
                }
                break;
        }
        assertTrue(calculator.getOperation() != null);
    }

    @Когда("^ввожу число (\\d+)$")
    public void ввожуЧисло(int number) throws Throwable {
        boolean tempBoolean = calculator.isFirstNumberSet();
        calculator.enter(number);
        if (tempBoolean) {
            assertEquals(calculator.getSecondNumber(), number);
        } else {
            assertEquals(calculator.getFirstNumber(), number);
        }
    }

    @Тогда("^я получаю результат (\\d+)$")
    public void яПолучаюРезультат(int expected) throws Throwable {
        assertEquals(expected, result);
    }

    @Когда("^я нажимаю кнопку \"C\"$")
    public void яНажимаюКнопкуC() throws Throwable {
        calculator.reset();
        assertEquals(0, calculator.getSecondNumber());
        assertEquals(0, calculator.getSecondNumber());
        assertNull(calculator.getOperation());
    }

    @Тогда("^я получаю ошибку")
    public void яПолучаюСообщениеОбОшибке() throws Throwable {
        assertTrue(calculator.isError());
    }
}
