import java.util.ArrayList;
public class PrimeSieve {
    private ArrayList<Integer> primes;
    private int n;
    public PrimeSieve(int n) {
        this.n = n;
        initPrimes();
    }
    public void initPrimes() {
        boolean[] ray = new boolean[n + 1];
        java.util.Arrays.fill(ray, true);
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if (ray[i]) {
                int k = n / i;
                for (int j = i; j <= k; j++) {
                    ray[i * j] = false;
                }
            }
        }
        primes = new ArrayList<>();

        for (int i = 2; i < n; i++) {
            if (ray[i]) {
                primes.add(i);
            }
        }
    }
    public ArrayList<Integer> getPrimes() {
        return primes;
    }
    public static void main(String[] args) {
        PrimeSieve sieve = new PrimeSieve(100);
        System.out.println(sieve.primes);
    }

}
