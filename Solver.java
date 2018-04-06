import java.util.Scanner;
import java.util.Stack;

public class Solver {
    static Scanner input;
    static Stack<Constant> constantStack = new Stack<Constant>();
    static int x0, y0;
	
    public static void main(String[] args) {
        displayIntro();
        input = new Scanner(System.in);
        boolean runLoop = true;
        while (runLoop) {
            System.out.println("---------------------------");
            int a = inputPrompt("a");
            int b = inputPrompt("b");
            int c = inputPrompt("c");
            solve(a, b, c);
            runLoop = contPrompt();
        }
        input.close();
    }
	
    /**
     * Prompts the user for input value of varName
     * @param varName variable name
     * @return user value
     */
    public static int inputPrompt(String varName) {
	System.out.print("Enter " + varName + ": ");
	return input.nextInt();
    }
	
    /**
     * Displays information about the program
     */
    public static void displayIntro() {
    	System.out.println("---------------------------");
    	System.out.println("Diophantine Equation Solver");
    	System.out.println("Enter equation ax + by = c");
    }

    
    /**
     * Prompt the user to run the program again
     * @return true if the user wants to run again
     */
    public static boolean contPrompt() {
    	System.out.println("If you want to solve another equation, type \'y\'");
    	String userInput = input.next();
    	return userInput.equalsIgnoreCase("y");
    }
    
    /**
     * Solves an+bm=c and displays a solution
     * @param x a
     * @param y b
     * @param c
     */
    public static void solve(int x, int y, int c) {
        boolean swapped = false;
        int gcd = gcd(x, y);
        System.out.println("---------------------------");
        System.out.println("gcd(" + x + ", " + y + ")=" + gcd);
        System.out.println("---------------------------");
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
            swapped = true;
        }
        if (c % gcd != 0) {
            System.out.println("No solution exists");
        }
        Constant currentConstant = constantStack.pop();
        //Init constants
        int m = 1;
        int q = currentConstant.a;
        currentConstant = constantStack.pop();
        int n = -currentConstant.q;
        int a = currentConstant.a;
        //Substitute using previous equation
        while (a != x) {
            int previousm = m;
            m = n;
            currentConstant = constantStack.pop();
            q = currentConstant.b;
            n = (n * -currentConstant.q) + previousm;
            a = currentConstant.a;
        }
        if (swapped) {
            x0 = n;
            y0 = m;
        } else {
            x0 = m;
            y0 = n;
        }
        x0 *= (c / gcd);
        y0 *= (c / gcd);
        //Display solution
        System.out.println("Particular solution:");
        System.out.println("x0=" + x0 + ", y0=" + y0);
        System.out.println("(" + x + ")*(" + x0 + ")+(" + y + ")*(" + y0+ ")=" + c);
        System.out.println("---------------------------");
    }
    
    /**
     * Calculate the gcd of two numbers by the Euclidean algorithm
     * @param a
     * @param b
     * @return gcd of a,b
     */
    public static int gcd(int a, int b) {
    	if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        int r;
        while(b != 0) {
            r = a % b;
            constantStack.push(new Constant(a, a/b, b, r));
            a = b;
            b = r;
        }
        return a;
    }

    /**
     * 
     * Class that holds data for each term in the expansion
     * from the Euclidean Algorithm
     *
     */
    private static class Constant {
        int a;
        int b;
        int q;
        int r;
        Constant(int a, int q, int b, int r) {
            this.a = a;
            this.b = b;
            this.q = q;
            this.r = r;
        }
        public String toString() {
            return "(a: " + a + ", q: " + q + ", b: " + b + ", r: " + r + ")";
        }
    }
}


