package proj1;

/**
 * Name:Khang Nguyen
 * Student ID: 013626431
 * Description of solution: Using recursion we going to find every possible sums that can be produce using an array of integer.
 * For my solution we going to start when there is no integer in a equation, which will produces a sum of 0.
 * Then we going to add all the possible sum to the next integer until we reach the last integer.
 * For example if we have an array of [1,2].
 * We will start from zero and add the first element to all the sums which is 0+1=1.
 * Then we will add the next element, 2, to all the sums which are 0+2=2, 1+2=3.
 * After the recursion we will get a list of all the possible sums, which is [0,1,2,3].
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

public class Q2 {

	// Return all sums that can be formed from subsets of elements in arr
	public static ArrayList<Integer> allSums( ArrayList<Integer> arr ) {
		HashSet<Integer> sumNoDup = new HashSet<>();
		// If the array is empty the return 0
		if(arr.isEmpty()) {
			sumNoDup.add(0);
			ArrayList<Integer> sum= new ArrayList<>(sumNoDup);
			return sum;
		}
		int last = arr.remove(arr.size()-1);
		// Find every possible sums without the last element
		ArrayList<Integer> subList = allSums(arr); 
		sumNoDup.addAll(subList);
		// Add the last element to the sublist
		for(int x : subList) {
			sumNoDup.add(x+last);
		}
		// Convert from a hashset to and arraylist to prevent any duplication
		ArrayList<Integer> sum= new ArrayList<>(sumNoDup);
		return sum;
	}
	
	public static void main(String[] args) {
		//https://www.baeldung.com/java-file-to-arraylist
		ArrayList<Integer> result = new ArrayList<Integer>();//= Files.readAllLines(Paths.get("nums.txt"));
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\14084\\Videos\\Captures\\nums.txt"));
			while (br.ready()) {
				result.add(Integer.parseInt(br.readLine()));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ArrayList<Integer> sums = allSums(result);
		
		System.out.println("Testing...");
		assertEquals(sums.size(), 8);
		assertEquals(sums.contains(0), true);
		assertEquals(sums.contains(1), true);
		assertEquals(sums.contains(2), true);
		assertEquals(sums.contains(4), true);
		assertEquals(sums.contains(3), true);
		assertEquals(sums.contains(5), true);
		assertEquals(sums.contains(6), true);
		assertEquals(sums.contains(7), true);
		System.out.println("Success!");
	}
}
