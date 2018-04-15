import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
public class NT_functions {
    public static ArrayList<Integer> factors = new ArrayList<Integer>();
    public static ArrayList<Integer> primes;
    public static void primeFactorization(int n) {
        primes = new PrimeSieve(n).getPrimes();
        factor(n);
    }
    public static void uniqueFactors(int n) {
        primeFactorization(n);
        Set<Integer> fs = new HashSet<>();
        fs.addAll(factors);
        factors.clear();
        factors.addAll(fs);
    }
    public static void factor(int n) {
        if (primes.contains(n)) {
            factors.add(n);
        }
        else {
            for (int p: primes) {
                if (n % p == 0) {
                    factors.add(p);
                    factor(n / p);
                    break;
                }
            }
        }
    }
    public static int EulersTotient(int n) {
        double totient = n;
        uniqueFactors(n);
        for (int factor: factors) {
            totient *= (1 - (1.0 / factor));
        }
        return (int)totient;
    }
    public static void main(String[] args) {
        uniqueFactors(100);
        System.out.println(factors);
        System.out.println(EulersTotient(100));
    }
}
