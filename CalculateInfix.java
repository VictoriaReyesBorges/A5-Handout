public class CalculateInfix {
    public static Double infixToPostfix(Queue<Object> tokens) {
        // stack that will hold operators
        Stack<Character> operStack = new Stack<>();
        // queue that will hold output
        Queue<Object> outQueue = new Queue<>();

        while (!tokens.isEmpty()) {
            if (tokens.peek() instanceof Character) {
                Character c = (Character) tokens.remove();

                // if the character is a left paren, push it to the stack
                if (c == '(') {
                   operStack.push(c); 
                }
                // if the character is an operator, compare with any operators in stack using PEMDAS
                // pop, add, and push accordingly
                if ((c == '+') || (c == '-') || (c == '*') || (c == '/')) {
                    while (!operStack.isEmpty()) {
                        if ((operStack.peek() == '*') || (operStack.peek() == '/')) {
                            Character o = operStack.pop();
                            outQueue.add(o);
                        } else {
                            break;
                        }
                    } operStack.push(c);
                }

                // if ((c == '*') || (c == '/')) {
                //     while (!operStack.isEmpty()) {
                //         if ((operStack.peek() == '*') || (operStack.peek() == '/')) {
                //             Character o = operStack.pop();
                //             outQueue.add(o);
                //         } else {
                //             break;
                //         }
                //     } operStack.push(c);
                // }

                if (c == ')') {
                    while (!operStack.isEmpty()) {
                        if (operStack.peek() == '(') {
                            operStack.pop();
                        } else if ((operStack.peek() == '+') || (operStack.peek() == '-') || (operStack.peek() == '*') || (operStack.peek() == '/')){
                            Character m = operStack.pop();
                            outQueue.add(m);
                        } else {
                            throw new RuntimeException("There are mismatched parenthesis.");
                        }
                    }
                }

                
            } else if (tokens.peek() instanceof Double) {
                Double d = (Double) tokens.remove();
                outQueue.add(d);
            }
        }
        


    }
}
