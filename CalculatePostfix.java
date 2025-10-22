public class CalculatePostfix {
  public static Double postfixToResult(Queue<Object> tokens) {
      Stack<Double> stack = new Stack<>();
      while (!tokens.isEmpty()){
          Object token = tokens.remove();
          if (token instanceof Double){
              stack.push((Double) token);




          } else if (token instanceof Character){
              char operation = (Character) token;




              double b = stack.pop();
              double a = stack.pop();
              double result = 0.0;




              if (operation == '+'){
                  result = a + b;
          }
              else if (operation == '-'){
                  result = a - b;
              }
            
              else if (operation == '*'){
                  result = a * b;
              }




              else if (operation == '/'){
                  if (b == 0){
                      throw new IllegalArgumentException("Division by zero");
                  } else {
                      result = a / b;
                  }
              }
          stack.push(result);
          }


      }
      return stack.pop();
  }


   public static void main(String[] args) {
       String expression = args[0];


       Queue<Object> tokens = Tokenizer.readTokens(expression);


       double result = postfixToResult(tokens);


       System.out.println("Answer" + " " + result);
   }


 }




