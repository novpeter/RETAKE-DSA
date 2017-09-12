import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;


public class Calculator {
    private Stack<String> operatorStack;
    private LinkedQueue<String> outputQueue;
    private StringTokenizer expression;
    private String result;

    public Calculator(StringTokenizer expression) {
        operatorStack = new Stack<String>();
        outputQueue = new LinkedQueue<String>();
        this.expression = expression;
    }

    public String calculate() {
        shuntingYard();
        RPN();
        return result;
    }

    private void shuntingYard() {
        while (expression.hasMoreTokens()) {
            String token = expression.nextToken();
            if (isNumber(token)) {
                outputQueue.enqueue(token);
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && !hasHigherPrecedence(token, operatorStack.peek())) {
                    outputQueue.enqueue(operatorStack.pop());
                }
                operatorStack.push(token);
            } else if (token.equals("(")){
                operatorStack.push(token);
            } else if (token.equals(")")){
                while (!operatorStack.peek().equals("(")){
                    outputQueue.enqueue(operatorStack.pop());
                }
                operatorStack.pop();
            }
        }
        while (!operatorStack.isEmpty()) outputQueue.enqueue(operatorStack.pop());
    }

    private void RPN() {
        while(!outputQueue.isEmpty()){
            String token = outputQueue.dequeue();
            if (isNumber(token)) {
                operatorStack.push(token);
            } else if (isOperator(token)){
                String value2 = operatorStack.pop();
                String value1 = operatorStack.pop();
                switch (token) {
                    case "+":
                        double sum = Double.valueOf(value1) + Double.valueOf(value2);
                        operatorStack.push(String.valueOf(sum));
                        break;
                    case "-":
                        double dif = Double.valueOf(value1) - Double.valueOf(value2);
                        operatorStack.push(String.valueOf(dif));
                        break;
                    case "*":
                        double mult = Double.valueOf(value1) * Double.valueOf(value2);
                        operatorStack.push(String.valueOf(mult));
                        break;
                    case "/":
                        double div = Double.valueOf(value1) / Double.valueOf(value2);
                        operatorStack.push(String.valueOf(div));
                        break;
                }
            }
        }
        java.math.BigDecimal x = new java.math.BigDecimal(Double.valueOf(operatorStack.pop()));
        x = x.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
        result = String.valueOf(x);
    }

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

    private static boolean hasHigherPrecedence(String op1, String op2) {
        if (precedence(op1) > precedence(op2)) {
            return true;
        } else {
            return false;
        }
    }
}
