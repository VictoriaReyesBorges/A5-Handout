public class CalculateInfix {
    /**
     * changes string equation into postfix order
     * @param tokens, queue from Tokenizer that contains equation elements
     * @return double, calculated from CalculatePostFix.postfixToResult method
     */
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
                        if ((operStack.peek() == '*') || (operStack.peek() == '/') || (operStack.peek() == '^')) {
                            outQueue.add(operStack.pop());
                        } else if ((c == '-') && operStack.peek() == '-') {
                            outQueue.add(operStack.pop());
                        } else {
                            break;
                        }
                    } operStack.push(c);
                }
                // handling right associativity for ^
                if(c == '^') {
                    operStack.push(c);
                }
                // if character is a right paren, pop operators off top of stack onto the output queue (outQueue) 
                // until token at top of stack is a left paren
                // once left paren is found, pop the paren off the operator stack (operStack)
                // throws a runtime exception to let user know there are mismatched parenthesis when operator stack is empty and no left paren found,
                // specficially with message that no left paren is found
                if (c == ')') {
                    while (!operStack.isEmpty() && (operStack.peek() != '(')) {
                        outQueue.add(operStack.pop());
                    } 

                    if (operStack.isEmpty()) {
                        throw new RuntimeException("There are mismatched parenthesis. No left parenthesis to match right.");
                    }

                    operStack.pop();
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
                    outQueue.add(operStack.pop());
                }
            }
        }

        // testing/debugging code
        // System.out.println("These are the contents of the output queue:" + outQueue);

        // Double number = CalculatePostfix.postfixToResult(outQueue);

        // System.out.println(number);

        // return number;
        // end of debugging code

        // if(c == '+' || c == '-' || c == '/' || c == '*'){ 
        //     return true; //return true if it's left associativity
        // } else {
        //     return false; //return false it's right associativity
        // }


        // finally, send output queue to postfix processing method 
        // return final answer 
        return CalculatePostfix.postfixToResult(outQueue);

    }
        

    public static void main(String[] args){
        if (args.length == 0){
            System.err.println("Usage: java CalculateInfix <expr>");
            return;
        }

        // Convert the command-line argument into a queue of tokens
        String expression = args[0];
        Queue<Object> tokens = Tokenizer.readTokens(expression); // assume readTokens handles numbers/operators/parentheses
        // System.out.println("Tokens queue:" + tokens);

        // Directly compute the result
        System.out.println("Result of Postfix Operation:" + infixToPostfix(tokens));
    
              
    }


    // Testing method
    // public static void main(String[] args) {
    //     Queue<Object> tokens = Tokenizer.readTokens("(4-2)^2");
    //     infixToPostfix(tokens);
    // }
}
