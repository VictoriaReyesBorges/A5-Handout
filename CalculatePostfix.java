public class CalculatePostfix {
    public static Double postfixToResult(Queue<Object> tokens) {
        // FILL IN
    }


    /**
     * Applies the operator to operands a and b and retuns the result
     * 
     * @param a the left operand
     * @param b the right operand
     * @param op the operators (+, -, *, /, or ^)
     * @return the result of applying the operator to a and b
     * @throws IllegalArgumentException for division by 0 and unsupported operator
     */
    public static double applyOperator(double a, double b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else if (op == '/') {
            if (b == 0.0) {
                throw new IllegalArgumentException("Can't be divided by 0");
            }
            return a / b;
        } else if (op == '^') {
            return Math.pow (a, b);
        } else {
            throw new IllegalArgumentException("Unsupported Operator:" + op);
        }
    }

    /**
     * Returns the precedence level
     * 
     * @param op the operators (+, -, *, /, or ^)
     * @return precedence level
     * @throws IllegalArgumentException for unsupported operator
     */
    public static int precedence(char op) {
        if (op == '+' || op == '-') {
            return 1;
        } else if (op == '*' || op == '/') {
            return 2;
        } else if (op == '^') {
            return 3;
        } else {
            throw new IllegalArgumentException("Unsupported OPerator:" + op);
        }
    }
}
