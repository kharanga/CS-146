package proj1;

/**
 * Name: Khang Nguyen
 * Student ID: 013626431
 * Description of solution: We going to use the recursive formula of C(n-1,k-1)+C(n-1,k) to find the number of possible way
 * we can choose an amount of elements from a list of numbers. 
 */

import static org.junit.Assert.*;

public class Q3 {

	// Return the number of ways to choose a subset of k distinct elements from a set of n elements  
	public static int C( int n, int k ) {
		// Check for invalid case of n < k
		if(n<k)
			return 0;
		// Check if whether k=0 or k=n. If yes, return 1.
		if(k ==0|| k==n) 
			return 1;
		// Formula for finding permutation using recursion.
		return C(n-1,k-1)+C(n-1,k);
	}
	
	public static void main(String[] args) {
		System.out.println("Testing...");
		assertEquals(C(14,3), 364);
		assertEquals(C(14,11), 364);
		assertEquals(C(18,8), 43758);
		System.out.println("Success!");
	}

}
