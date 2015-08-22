package rbs.serialization;

// Prime Data Object
public class PrimesPOD {
    private int initial;
    private int [] primes;

    public PrimesPOD(int initial, int[] primes) {
        this.initial = initial;
        this.primes = primes;
    }

    public int getInitial() {
        return initial;
    }

    public int[] getPrimes() {
        return primes;
    }
}
