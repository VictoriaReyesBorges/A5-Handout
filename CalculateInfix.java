public class CalculateInfix {
    public static Double infixToPostfix(Queue<Object> tokens) {
        // stack that will hold operators
        Stack<Character> operStack = new Stack<>();
        // queue that will hold output
        Queue<Object> outQueue = new Queue<>();

        // while tokens queue is not empty
        while (!tokens.isEmpty()) {
            // check if token is an operator
            if (tokens.peek() instanceof Character) {
                // remove head or first element of queue and store in reference variable c
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

                // Just keeping in case
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

                // if character is a right paren, pop operators off top of stack onto the output queue (outQueue) 
                // until token at top of stack is a left paren
                // once left paren is found, pop the paren off the operator stack (operStack)
                // throws a runtime exception to let user know there are mismatched parenthesis, 
                // specficially with no left paren found
                if (c == ')') {
                    while (!operStack.isEmpty()) {
                        if (operStack.peek() == '(') {
                            operStack.pop();
                        } else if ((operStack.peek() == '+') || (operStack.peek() == '-') || (operStack.peek() == '*') || (operStack.peek() == '/')){
                            Character m = operStack.pop();
                            outQueue.add(m);
                        } else {
                            throw new RuntimeException("There are mismatched parenthesis. Could not find a left parenthesis.");
                        }
                    }
                }

            // check if token is a double     
            } else if (tokens.peek() instanceof Double) {
                // remove head/first element of queue and store in reference variable d
                // add it to the output queue
                Double d = (Double) tokens.remove();
                outQueue.add(d);
            // break if tokens queue is empty
            } else {
                break;
            }
        }

        // if or once tokens queue is empty
        if (tokens.isEmpty()) {
            // while operator stack is not empty
            // peek at the top of operStack
            // if it's a left paren, throw runtime exception to let user know there are mismatched parenthesis,
            // specifically no right paren was found
            // otherwise, pop the operator and add it to the output queue
            while (!operStack.isEmpty()) {
                if (operStack.peek() == '(') { 
                    throw new RuntimeException("There are mismatched parenthesis. No right parenthesis to match left.");
                } else {
                    Character c = operStack.pop();
                    outQueue.add(c);

                }
            }
        }

        // finally, send output queue to postfix processing method 
        // return final answer 
        return CalculatePostfix.postfixToResult(outQueue);
        


    }
}
