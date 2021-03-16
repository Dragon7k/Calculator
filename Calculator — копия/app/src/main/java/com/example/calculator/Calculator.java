package com.example.calculator;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Stack;

public class Calculator {

    final String[] OPERATORS = {"+","-","/","*"};
    final String[] FUNCTIONS = {"sin","cos"};
    protected Stack<String> stackOperations = new Stack<>();
    protected Stack<String> stackPOLIZ = new Stack<>();
    protected Stack<String> stackCalc = new Stack<>();

    Calculator() {
        stackOperations.clear();
        stackPOLIZ.clear();
        stackCalc.clear();
    }

    public double calculate(ListIterator<String> InputData) {

        convertToRPN(InputData);

        Collections.reverse(stackPOLIZ);

        System.out.println("reverce poliz:  "+stackPOLIZ);

        stackCalc.clear();

        while (!stackPOLIZ.empty()) {
            String token = stackPOLIZ.pop();
            if (isNumber(token)) {
                stackCalc.push(token);

            } else if (isOperator(token)) {

                double operand1 = Double.parseDouble(stackCalc.pop());
                double operand2 = Double.parseDouble(stackCalc.pop());

                switch (token) {
                    case "+":
                        stackCalc.push(String.valueOf(operand2+operand1));
                        break;
                    case "-":
                        stackCalc.push(String.valueOf(operand2-operand1));
                        break;
                    case "*":
                        stackCalc.push(String.valueOf(operand2*operand1));
                        break;
                    case "/":
                        if(operand1== 0){
                            System.out.println("Devised by zero");
                            return Double.POSITIVE_INFINITY;

                        }else{
                            stackCalc.push(String.valueOf(operand2/operand1));}
                        break;
                }
            } else if (isFunction(token)) {

                double operand = Double.parseDouble(stackCalc.pop());
                switch (token) {
                    case "sin":
                        stackCalc.push(String.valueOf(Math.sin(Math.toRadians(operand))));
                        break;

                    case "cos":
                        stackCalc.push(String.valueOf(Math.cos(Math.toRadians(operand))));
                        break;

                }
            }
        }
        return Double.parseDouble(stackCalc.pop());
    }



    protected void convertToRPN(ListIterator<String> tokens) {

        while (tokens.hasNext()) {
            String token = tokens.next();
            if (isNumber(token)) {
                stackPOLIZ.push(token);
            } else if (isFunction(token)) {
                stackOperations.push(token);
            } else if (isOperator(token)) {
                while (!stackOperations.empty()

                        && isOperator(stackOperations.lastElement())

                        && (definePriority(token)

                        <= definePriority(stackOperations.lastElement()))) {

                    stackPOLIZ.push(stackOperations.pop());
                }
                stackOperations.push(token);
            } else if (isLeftBracket(token)) {
                stackOperations.push(token);
            } else if (isRightBracket(token)) {
                while (!stackOperations.empty()
                        && !isLeftBracket(stackOperations.lastElement())) {

                    stackPOLIZ.push(stackOperations.pop());
                }

                stackOperations.pop();

                if (isFunction(stackOperations.lastElement())) {

                    stackPOLIZ.push(stackOperations.pop());
                }
            }
             else{
                System.out.println("Unexpected item!");
                stackOperations.clear();
                stackPOLIZ.clear();
                return ;
            }
        }

        while (!stackOperations.empty()) {

            stackPOLIZ.push(stackOperations.pop());
        }

        System.out.println("We've got an expression:\n"+stackPOLIZ);
    }


    protected boolean isNumber (String token) {
        try {
            Double.parseDouble(token);
        }
        catch (Exception exc) {
            return false;
        }
        return true;
    }

    protected boolean isOperator(String token) {
        for (String op : OPERATORS)
            if (op.equals(token))
                return true;
        return false;
    }

    protected boolean isLeftBracket(String token) {
        return token.equals("(");
    }

    protected boolean isRightBracket(String token) {
        return token.equals(")");
    }

    protected boolean isFunction(String token) {
        for (String func : FUNCTIONS) {
            if (func.equalsIgnoreCase(token))
                return true;
        }
        return false;
    }

    protected int definePriority(String operation) {
        if (operation.equals("+") || operation.equals("-"))
            return 1;
        else
            return 2;
    }


}
