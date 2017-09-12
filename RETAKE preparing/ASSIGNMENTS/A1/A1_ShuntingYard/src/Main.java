import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Petr on 03.02.2017.
 */
public class Main {
    private static LinkedQueue<String> outputQueue = new LinkedQueue<>();
    private static LinkedStack<String> operatorStack = new LinkedStack<>();

    public static void main(String[] args) {
        writeString(RPN(ShuntingYard()));
    }

    /**
     * There’s an algorithm for infix arithmetic parsing - Shunting-yard algorithm. It can be used for
     either creation of RPN (reversed polish notation) or AST (abstract syntax tree). It uses both stack and
     queue. Here’s simplified algorithm without functions support.
     * @return LinkedQueue<String>
     */
    private static LinkedQueue<String> ShuntingYard() {
        StringTokenizer expression = readFile();
        while (expression.hasMoreTokens()) {
            String token = expression.nextToken();
            if (isNumber(token)) {
                outputQueue.enqueue(token);
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && !hasHigherPrec(token, operatorStack.top())) {
                    outputQueue.enqueue(operatorStack.pop());
                }
                operatorStack.push(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.top().equals("(")) outputQueue.enqueue(operatorStack.pop());
                operatorStack.pop();
            }
        }
        while (!operatorStack.isEmpty()) outputQueue.enqueue(operatorStack.pop());
        return outputQueue;
    }

    /**
     * This method calculate all expressions in outputQueue using RPN
     * @param outputQueue
     * @return result string
     */
    private static String RPN(LinkedQueue<String> outputQueue) {
        String result;
        while (!outputQueue.isEmpty()) {
            String token = outputQueue.dequeue();
            if (isNumber(token)) {
                operatorStack.push(token);
            } else if (isOperator(token)) {
                String num2 = operatorStack.pop();
                String num1 = operatorStack.pop();
                switch (token) {
                    case "+":
                        double sum = Double.valueOf(num1) + Double.valueOf(num2);
                        operatorStack.push(String.valueOf(sum));
                        break;
                    case "-":
                        double dif = Double.valueOf(num1) - Double.valueOf(num2);
                        operatorStack.push(String.valueOf(dif));
                        break;
                    case "*":
                        double mult = Double.valueOf(num1) * Double.valueOf(num2);
                        operatorStack.push(String.valueOf(mult));
                        break;
                    case "/":
                        double div = Double.valueOf(num1) / Double.valueOf(num2);
                        operatorStack.push(String.valueOf(div));
                        break;
                }
            }
        }
        return String.format("%10.2f%n", Double.valueOf(operatorStack.pop()));
    }

    /**
     * Determines whether the given string is a number
     * @param num - given substring
     * @return true/false
     */
    private static boolean isNumber(String num) {
        boolean result = false;
        for (int i = 0; i < num.length(); i++) {
            switch (num.charAt(i)) {
                case '1':
                    result = true;
                case '2':
                    result = true;
                case '3':
                    result = true;
                case '4':
                    result = true;
                case '5':
                    result = true;
                case '6':
                    result = true;
                case '7':
                    result = true;
                case '8':
                    result = true;
                case '9':
                    result = true;
                case '.':
                    result = true;
            }
        }
        return result;
    }

    /**
     * Determines whether the given string is an operator
     * @param op - given substring
     * @return true/false
     */
    private static boolean isOperator(String op) {
        switch (op) {
            case "+":
                return true;
            case "-":
                return true;
            case "*":
                return true;
            case "/":
                return true;
        }
        return false;
    }

    /**
     * This method determine what precedence operation has
     * @param op - given substring
     * @return operation precedence
     */
    private static int precedence(String op) {
        int prec = 0;
        switch (op) {
            case "+":
                prec = 1;
                break;
            case "-":
                prec = 1;
                break;
            case "*":
                prec = 2;
                break;
            case "/":
                prec = 2;
                break;
        }
        return prec;
    }

    /**
     * Determines whether the first operation has higher precedence than second operation
     * @param op1
     * @param op2
     * @return true/false
     */
    private static boolean hasHigherPrec(String op1, String op2) {
        if (precedence(op1) > precedence(op2)) {
            return true;
        } else {
            return false;
        }
    }

    private static StringTokenizer readFile() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            StringTokenizer st = new StringTokenizer(sc.nextLine(), "+-*/() ", true);
            return st;
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

    /**
     * Writes the string in the file
     *
     * @param s the string which has to be written
     */
    private static void writeString(String s) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(s);
        } catch (IOException ex) {
        }
    }
}
