import java.util.Stack;
import java.util.StringTokenizer;

public class PushbackTokenizer implements PushbackableTokenizer {
	
	Stack<String> firstStack;
	Stack<String> secondStack;
	
	public PushbackTokenizer(String data){
		//constructor
		this.firstStack = new Stack<String>();
		this.secondStack = new Stack<String>();
		
		StringTokenizer tokens = new StringTokenizer(data, " ");
		
		assert !data.equals(""): "Empty";
		
		while (tokens.hasMoreElements()) {
			firstStack.push((String) tokens.nextElement());
		}
		
		assert !firstStack.empty():"Empty";
		
		while (!firstStack.isEmpty()) {
			secondStack.push(firstStack.pop());
		}
	}

	@Override
	public String nextToken() {
		// TODO Auto-generated method stub
		assert hasMoreTokens():"2nd stack empty";
		String secondPop = secondStack.peek();
		firstStack.push(secondStack.pop());
		assert firstStack.peek().equals(secondPop):"Top != pop";
		
		return firstStack.peek();
	}

	@Override
	public boolean hasMoreTokens() {
		assert secondStack.peek() != null:"Checking";
		return !secondStack.isEmpty();
	}

	@Override
	public void pushback() {
		// TODO Auto-generated method stub
		String firstPop = firstStack.peek();
		secondStack.push(firstStack.pop());
		
		assert secondStack.peek().equals(firstPop):"Top of 2nd stack != popped off 1st stack";

	}

}
