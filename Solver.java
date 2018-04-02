import java.util.Scanner;
import java.util.Stack;

public class Solver {
        private static Stack<Constant> constantStack = new Stack<Constant>();
        private static int x0, y0;
        private static int gcd;
        public static void main(String[] args) {
            System.out.println("Enter an x and y, and c:");
            Scanner input = new Scanner(System.in);
            int x = input.nextInt();
            int y = input.nextInt();
            int c = input.nextInt();
            gcd = gcd(x, y);
            System.out.println("gcd(" + x + ", " + y + ") is " + gcd);
            System.out.println(solve(x, y, c));
        }
        public static boolean solve(int x, int y, int c) {
            boolean swapped = false;
            if (x < y) {
                int temp = x;
                x = y;
                y = temp;
                swapped = true;
            }
            if (c % gcd != 0) {
                return false;
            }
            Constant currentConstant = constantStack.pop();
            //Init constants
            int m = 1;
            int q = currentConstant.a;
            currentConstant = constantStack.pop();
            int n = -currentConstant.q;
            int a = currentConstant.a;

            //System.out.println("3 = " + m + "*" + a + " + " + n + "*" + q);
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
            System.out.println("x0 is " + x0 + ", y0 is " + y0);
            return true;
        }
        public static int gcd(int a, int b) {
            if (a < b) {
                int temp = a;
                a = b;
                b = temp;
            }
            int r;
            while(b != 0) {
                System.out.println(a);
                r = a % b;
                constantStack.push(new Constant(a, a/b, b, r));
                a = b;
                b = r;
            }
            return a;
        }
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
//            public int getA() { return a;}
//            public int getB() { return b;}
//            public int getQ() { return q;}
//            public int getR() { return r;}
            public String toString() {
                return "(a: " + a + ", q: " + q + ", b: " + b + ", r: " + r + ")";
            }
        }
}


