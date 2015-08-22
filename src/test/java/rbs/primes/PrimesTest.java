package rbs;

import static org.junit.Assert.*;
import org.junit.Test;

import java.lang.Exception;
import java.lang.System;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class PrimesTest
{
    @Test
    public void prividedTest()
    {
        int exp[] = {2,3,5,7};
        Primes cache = new Primes(10);
        int result[] = cache.getPrimesUpTo(10);
//        System.out.println(Arrays.toString(exp) + Arrays.toString(result));
        assertEquals("primes up to 10", Arrays.toString(exp), Arrays.toString(result));
    }

    @Test
    public void outOfBoundsTest()
    {
        try{
            Primes cache = new Primes(2);
            assertTrue("the constructor should throw",false); // unreachable
        }
        catch (Exception ex){
            assertTrue("Cache too small", true);  // expected
        }
        try{
            Primes cache = new Primes(3);
            int result[] = cache.getPrimesUpTo(4);
            assertTrue("the getter should throw",false); // unreachable
        }
        catch (Exception ex){
            assertTrue("out of bounds request", true);  // expected
        }
    }

    @Test
    public void lowTest()
    {
        {
            int exp[] = {2, 3};
            Primes cache = new Primes(3);
            int result[] = cache.getPrimesUpTo(3);
//            System.out.println(Arrays.toString(exp) + Arrays.toString(result));
            assertEquals("primes up to 3", Arrays.toString(exp), Arrays.toString(result));
        }
        {
            int exp[] = {2, 3, 5};
            Primes cache = new Primes(5);
            int result[] = cache.getPrimesUpTo(5);
//            System.out.println(Arrays.toString(exp) + Arrays.toString(result));
            assertEquals("primes up to 5", Arrays.toString(exp), Arrays.toString(result));
        }
    }

    @Test
    public void primesUpTo1000Test(){
        // canned data
        int exp[] = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,433,439,443,449,457,461,463,467,479,487,491,499,503,509,521,523,541,547,557,563,569,571,577,587,593,599,601,607,613,617,619,631,641,643,647,653,659,661,673,677,683,691,701,709,719,727,733,739,743,751,757,761,769,773,787,797,809,811,821,823,827,829,839,853,857,859,863,877,881,883,887,907,911,919,929,937,941,947,953,967,971,977,983,991,997};
        Primes cache = new Primes(1000);
        int result[] = cache.getPrimesUpTo(1000);
//        System.out.println(Arrays.toString(exp) + Arrays.toString(result));
        assertEquals("primes up to 1000", Arrays.toString(exp), Arrays.toString(result));
    }

    @Test
    public void primesUpTo1000_10Test(){
        // canned data
        int exp[] = {2,3,5,7};
        Primes cache = new Primes(1000);
        int result[] = cache.getPrimesUpTo(10);
        assertEquals("primes up to 10", Arrays.toString(exp), Arrays.toString(result));
    }

    @Test
    public void multipleRequestTest(){
        // canned data
        int exp[] = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
        Primes cache = new Primes(100);
        int result[] = cache.getPrimesUpTo(100);
//        System.out.println(Arrays.toString(exp) + Arrays.toString(result));
        assertEquals("primes up to 100", Arrays.toString(exp), Arrays.toString(result));

        result = cache.getPrimesUpTo(99);
        assertEquals("primes up to 99", Arrays.toString(exp), Arrays.toString(result));

        result = cache.getPrimesUpTo(97);
        assertEquals("primes up to 99", Arrays.toString(exp), Arrays.toString(result));

        result = cache.getPrimesUpTo(96);
        assertNotEquals("primes up to 96", Arrays.toString(exp), Arrays.toString(result));
    }

    @Test
    public void metricForDefaultTest()
    {
        long start_time = System.currentTimeMillis();
        Primes cache = new Primes(2147483);
        long cache_setup_time = System.currentTimeMillis();
        int result[] = cache.getPrimesUpTo(2147483);
        long end_time = System.currentTimeMillis();
        System.out.println("STATs -- start: " + start_time + ", init: " + cache_setup_time + ", end: " + end_time
                            + ", max heap: " +  Runtime.getRuntime().maxMemory() + ", num primes: " + result.length);
        System.out.println("cache init: " + (cache_setup_time - start_time) + "ms, query time: " + (end_time - cache_setup_time) + "ms");
    }

}
