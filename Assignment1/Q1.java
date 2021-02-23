package proj1;

/**
 * Name:Khang Nguyen
 * Student ID: 013626431
 * Description of solution: Using recursion we going to find every possible combination of a string without altering its character order.
 * For my solution I find the last element of a string and its substring, then I will add that last element to every possible substring.
 * An example with the string "abc", we will start from the shortest string which is "". Then we will add the last elements we removed which is "a"
 * Now we will add "b" to our sublist strings including ("","a"), which will add ("b", and "ab") to our sublist.
 * Lastly we do the same process with "c", our final list should be ("","a","b","ab","c","ac","bc","abc");  
 */

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Q1 {

	// Return all substrings of the String s
	public static ArrayList<String> allSubstrings(String s){
		ArrayList<String> list = new ArrayList<String>();
		// Check if the string is empty or not
		if(s.isEmpty()) {
			list.add("");
			return list;
		}
		// Find every possible of substrings of a substring without its last element
		ArrayList<String> subList = allSubstrings(s.substring(0,s.length()-1));
		char last = s.charAt(s.length()-1);
		list.addAll(subList);
		// Add the last element to substrings of a string
		for(String x : subList) {
			list.add(x+last);
		}
		return list;
	}
	public static void main(String[] args) {
		ArrayList<String> s = allSubstrings("abcde");
		System.out.println("Testing...");
		assertEquals(s.size(), 32);
		assertEquals(s.contains(""), true);
		assertEquals(s.contains("abcd"), true);
		assertEquals(s.contains("abce"), true);
		assertEquals(s.contains("abcde"), true);
		System.out.println("Success!");
	}

}
