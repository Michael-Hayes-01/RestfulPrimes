package rbs;

import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Main functionality is encapsulated in this class to gather and store primes using
 * the seive of eratosthenes algo and cache in presentation format
 */
public class Primes {

    final static Logger logger = Logger.getLogger(Primes.class);

    private int[] primes;

    public int getMaxVal() {
        return maxVal;
    }

    private int maxVal;
    private final int START_VAL = 3;

    /**
     * cache is created in this constructor
     *
     * @param maxVal
     */
    public Primes(int maxVal) {
        if(maxVal < START_VAL)
            throw new RuntimeException("Minimum size of the PrimeSieve cache is " + START_VAL);

        // Integer.MAX_VALUE may well require additional heap memory and will quite likely result in an OutOfMemoryError
        this.maxVal = maxVal==Integer.MAX_VALUE ? maxVal-1 : maxVal;

        logger.info("Constructing cache with max prime value of " + this.maxVal);

        // the name 'isPrime' may be more readable however using the name notPrime utilises default value initialisation
        boolean [] notPrime = new boolean[this.maxVal+1];
        int count = this.maxVal;

        // mark the primes >= maxVal using seive of eratosthenes
        // cache construction is O(n log log n)
        // cache access will run at O(log n)
        for(int i=2; i*i<=this.maxVal; ++i){
            if(!notPrime[i]){
                for(int j=i; i*j<=this.maxVal; ++j){
                    if(!notPrime[i*j]){
                        notPrime[i*j] = true;
                        --count;
                    }
                }
            }
        }

        // populate the prime number cache
        primes = new int[count-1];
        primes[0]=2;
        int indx=1;
        for(int i=START_VAL; i<notPrime.length; ++i){
            if(!notPrime[i]){
                primes[indx++] = i;
            }
        }
        logger.info("Cache constructed with " + primes.length + " elements");
    }

    /**
     * query the cache to get the primes up to and including x
     * @param x
     * @return
     */
    public int[] getPrimesUpTo(int x){
        if(x>maxVal)
            throw new RuntimeException("OutOfBounds request for list of primes greater than supported. Max supported is " + getMaxVal());

        int indx = upperBound(x);
        return Arrays.copyOfRange(primes,0,indx);
    }

    // return the index of the value or the last prime before the value, I'd prefer not to provide this method as
    // my preference is always to use available library methods if possible
    private int upperBound(int x) {
        int low=0;
        int upper=primes.length-1;
        int pos=(low+upper)/2;
        while((primes[pos]!=x) && (low<=upper)){
            if(primes[pos] > x)
                upper = pos-1;
            else
                low = pos+1;
            pos=(low+upper)/2;
        }
        return primes[pos]<=x ? ++pos : pos;
    }
}
