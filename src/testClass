import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class testClass {
	
	private static QueueMethods <String> queue = new QueueMethods<String>();
	private static StackMethods <String> stack = new StackMethods<String>();
	private static String given;
	private static String errorExpression;
	
	public static void main(String[] args) throws IOException {
		
		File out = new File(args[1]);
		BufferedWriter writer = null;
		boolean error = false;
		Scanner scan = new Scanner(new File(args[0]));
		writer = new BufferedWriter(new FileWriter(out));
		
		while(scan.hasNextLine()){
			try{
				errorExpression = scan.nextLine();
				given = errorExpression;
				given = given.replace(" ", "");
				String expression = "";
				String array [] = new String [given.length()];
				for(int i = 0 ; i < given.length(); i++){
					array[i] = "" + given.charAt(i);
				}
				
				for(int i = 1; i < array.length; i++){
					if(array[i].equals("-")){
						if(compare(array[i+1]).equals("operator") || compare(array[i+1]).equals("parenthesis")){
							array[i] = " - ";
						}
						if(compare(array[i-1]).equals("number") && compare(array[i+1]).equals("number")){
							array[i] = " - ";
						}
					}
				}
				
				for(int i = 0; i < array.length; i++){
					expression = expression + array [i];
				}
				expression = expression.replace("!", "! ");
				expression = expression.replace("(", " ( ");
				expression = expression.replace(")", " ) ");
				expression = expression.replace("+", " + ");
				expression = expression.replace("=", " = ");
				expression = expression.replace("*", " * ");
				expression = expression.replace("/", " / ");
				expression = expression.replace("<", " < ");
				expression = expression.replace(">", " > ");
				expression = expression.replace("&", " & ");
				expression = expression.replace("|", " | ");
				expression = expression.replace("%", " % ");
				expression = expression.replace("^", " ^ ");
				
				// if the expression's first character is ' ', get rid of it
				while(expression.charAt(0) == ' '){ 
					expression = expression.substring(1);
				}

				String [] tokens = expression.split("\\s+"); // split by spaces. each element has a token
				
				for(int i = 0; i < tokens.length; i++){
					postfix(tokens[i]);
				} // close for loop
				
				while(!stack.isEmpty()){ // put remaining stack things in queue
					String e = stack.pop();
					queue.enqueue(e); 
				}

				String postfixExpression = queue.getCurrent();
				
				// FROM HERE WE HAVE TO EVALUATE IT
				evaluate();
				
				double temp = Double.parseDouble(stack.pop());
				String temp2 = String.format("%.2f", temp);
				writer.write(postfixExpression + "\t\t" + temp2 +"\n");
				
				while(!stack.isEmpty()){ // empty the stack
					stack.pop();
				}
				
				while(!queue.isEmpty()){ // empty the queue
					queue.dequeue(); 
				}
			}
			catch(Exception e){
				while(!stack.isEmpty()){ // empty the stack
					stack.pop();
				}
				while(!queue.isEmpty()){ // empty the queue
					queue.dequeue(); 
				}
				error = true;
				writer.write(errorExpression + " is not a valid expression.\n");
			}
		} // close loop
		
		writer.close();
		if(error == true){
			System.out.println("Done! But there are error(s).");
		} else{
			System.out.println("Done! With no errors!");
		}
	}

	
	public static void postfix(String type){ // put into postfix notation
		String compare = compare(type);
		
		if (compare.equals("number")) { // check if number
			queue.enqueue(type);
		} 
		
		else if (compare.equals("parenthesis")) { // if it's parenthesis
			if (type.equals("(")) { // if stack is adding open parenthesis
				stack.push(type);
				stack.push("~"); // place holder for the parenthesis
			} else { // close parenthesis
				while(stack.peek().charAt(0) != '(') { // while not open parenthesis
					if(precedence(stack.peek()) > 3){ // number is the precedence of the token for parenthesis
						queue.enqueue(stack.pop()); // put stuff from stack into queue until the token is found
					} else{
						stack.pop();
					}
				}
				stack.pop(); // discards the open parenthesis
			}
			
		}
		
		else if(compare.equals("boolean")){
			if (type.equals("!") || type.equals("<") || type.equals(">")){
				stack.push(type);
				// don't have to do anything special with the less/greater than
				// because there has to be parentheses around them
			}
			
			else if (type.equals("=")){
				while(!stack.isEmpty() && precedence(stack.peek()) > precedence(type)){
					queue.enqueue(stack.pop());
				}
				stack.push(type);
			}
			
			else if (type.equals("&")){
				stack.push(type); 
			}
			
			else if (type.equals("|")){
				if(stack.peek() != null){
					if (stack.peek().equals("&")){
						queue.enqueue(stack.pop()); // put & in queue
						stack.push(type);
					} 
				}
				
				else{
					stack.push(type);
				}
			}
		}
		
		else{ // any other operator
			if (stack.isEmpty()) {
				stack.push(type);
			} else { // check precedence 
				if (precedence(type) == precedence(stack.peek())){
					queue.enqueue(stack.pop()); 
					stack.push(type);
				}
				else if (precedence(type) < precedence(stack.peek())) { // check precedence with top of stack
					while(!stack.isEmpty()){
						// this means it's of lower precedence than whatever's on the top of the stack
						queue.enqueue(stack.pop()); // add everything from stack to queue
					}
					stack.push(type); // add new operator to stack
				} else { // this means it's of higher precedence
					stack.push(type);
				}
			}
		}
	}
	
	public static void evaluate(){
		while(!queue.isEmpty()){ 
			if (compare(queue.peek()).equals("number")){
				stack.push(queue.dequeue()); // put number in stack
			} else {
				stack.push(queue.dequeue()); // put operator in stack
				stack.push(String.valueOf(operations(stack.pop()))); // takes operator, evaluates, and deletes it
			}
		}
	}
	
	public static double operations(String operator){ // define operators
		char operate = operator.charAt(0);
		switch (operate) {
			case '+':
				return Double.parseDouble(stack.pop()) + Double.parseDouble(stack.pop());
	        case '-':
	        	double temp4 = Double.parseDouble(stack.pop());
	        	return Double.parseDouble(stack.pop()) - temp4;
	        case '*':
	        	return Double.parseDouble(stack.pop()) * Double.parseDouble(stack.pop());
	        case '/':
	        	double temp = Double.parseDouble(stack.pop());
	        	return Double.parseDouble(stack.pop()) / temp;
	        case '^': 
	        	double temp2 = Double.parseDouble(stack.pop());
	        	return Math.pow(Double.parseDouble(stack.pop()), temp2);
	        case '%':
	        	double temp3 = Double.parseDouble(stack.pop());
	        	return Double.parseDouble(stack.pop()) % temp3;
	        case '=':
	        	if (Double.parseDouble(stack.pop()) == Double.parseDouble(stack.pop())){
	        		return 1.0;
	        	} else {
	        		return 0.0;
	        	}
	        case '!':
	        	if (Double.parseDouble(stack.pop()) == 1.0){
	        		return 0.0;
	        	} else {
	        		return 1.0;
	        	}
	        case '<':
	        	if (Double.parseDouble(stack.pop()) > Double.parseDouble(stack.pop())){
	        		return 1.0;
	        	} else {
	        		return 0.0;
	        	}
	        case '>':
	        	if (Double.parseDouble(stack.pop()) < Double.parseDouble(stack.pop())){
	        		return 1.0;
	        	} else {
	        		return 0.0;
	        	}
	        case '&':
	        	if (Double.parseDouble(stack.pop()) == 1.0 && Double.parseDouble(stack.pop()) == 1.0){
	        		return 1.0;
	        	} else {
	        		return 0.0;
	        	}
	        case '|':
	        	boolean first, second;
	        	double temp5 = Double.parseDouble(stack.pop());
	        	double temp6 = Double.parseDouble(stack.pop());
	        	if (temp5 == 0.0){
	        		first = false;
	        	} else {
	        		first = true;
	        	}
	        	if (temp6 == 0.0){
	        		second = false;
	        	} else {
	        		second = true;
	        	}

	        	if (first == true || second == true){
	        		return 1.0;
	        	} else {
	        		return 0.0;
	        	}
	        default:
	            throw new IllegalArgumentException("Operator unknown: " + operate);
	   }
	}
	
	public static String compare(String i){ // check operand, operator, or parenthesis
		if (isNumeric(i)) { 
			return "number";
		} 
		else if (i.equals("(") || i.equals(")") ) {
			return "parenthesis";
		}
		else if (i.equals("!") || i.equals("&") || i.equals("|") || i.equals("<") || i.equals(">") || i.equals("=") || i.equals("<") || i.equals(">")){
			return "boolean";
		}
		else {
			return "operator";
		}
	}
	
	
	public static boolean isNumeric(String str){  // check if a String is a number
	 
		try {  
			double d = Double.parseDouble(str);
		}  
		catch(NumberFormatException nfe) {  
			return false;  
		}  
		return true; 
	}
	
	
	public static int precedence(String j){ // check for precedence
		
		char i = j.charAt(0);
		
		switch (i) {
			case '|':
				return 0;
			case '&':
				return 1;
			case '!':
				return 2;
			case '~':
				return 3;
			case '=':
				return 4;
			case '<':
			case '>':
				return 5;
	        case '+':
	        case '-':
	            return 6;
	        case '*':
	        case '(':
	        case '/':
	        case '%': // extra credit for modulo
	            return 7;
	        case '^':
	            return 8; // extra credit for exponents
	        default:
	            throw new IllegalArgumentException("Operator unknown: " + i); // extra credit for this?
	   }
	}
}
