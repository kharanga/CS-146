/**
 * 
 */
package a5;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * This is an implementation of a hash table.
 * @author andreopo
 * Khang Nguyen
 * 013626431
 */
public class HashTable {


	/* Class containing next node and key and name value*/
	class Node { 
		private long key;

		public long getKey() {
			return key;
		}

		public void setKey(long key) {
			this.key = key;
		}

		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		Node next; 

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		Node(long key, String value) {
			this.key = key;
			this.value = value;
			next = null;
		}
	} 

	//The hash table array
	Node[] array = null;
	//hash table size. We typically choose m to be a power of 2
	int m = 0;

	// Constructor 
	HashTable(int size) {  
		m = size;
		array = new Node[size];  
	}

	HashTable() {  
		this(100);
	} 

	// Search for a given key in the hashtable
	// Note: you need to use hashFunction(long key) to determine the slot (array index) you will search in
	public Node search(long key) 
	{
		int index = hashFunction(key);
		Node start = array[index];
		while(start!=null) {
			if(start.getKey()==key)
				return start;
			start = start.getNext();
		}
		return null;
	}

	// Insert a new Node with a key (nameToKey(name)) and value (name) in the hashtable
	// Note: you need to use hashFunction(long key) to determine the slot (array index) you will insert into
	public void insert(long key, String value) {
		/**
		 * TODO
		 */
		int index = hashFunction(key);
		Node node = array[index];
		if(node == null)
			array[index] = new Node(key, value);
		else {
			while(node.getNext()!=null) {
				node = node.getNext();
			}
			node.setNext(new Node(key,value));
		}
	}


	/**
	 * This is the multiplication method as our hash function.
	 * @param key  It computes the hash function to find the slot (bucket) for key
	 * @return  the hash
	 */
	private int hashFunction(long key) {
		double k = (Math.sqrt(5) - 1.0) / 2.0;
		int hash = (int) (((double)m) * ((k*(double)key) % 1.0));
		return hash;
	}

	//Convert a name to a key using radix-26:
	//Use all of the letters of the alphabet as your digits.
	//Assume we only deal with lower-case letters for simplicity.
	//Then you can create the radix(base)-26 alphabet number system.
	//For example:
	// "alice" in radix-26 is 26^4*1 + 26^3*12 + 26^2*9 + 26^1*3 + 26^0*5 = 674055
	// "zoe" in radix-26 is 26^2*26 + 26^1*15 + 26^0*5 = 17971 
	public static long nameToKey(String name) {
		long key = 0;
		/**
		 * TODO
		 */
		int length = name.length();
		for(int i = 0; i< length; i++) {
			int val = (int) (Math.pow(26, length-i-1)*toRadix(name.substring(i,i+1)));
			key+= val;
		}
		
		return key;
	}
	// Turn a char into a number according to its alphabetical index
	public static int toRadix(String ch) {
		int index = find(ch);
		return index+1;
		
	}
	// Find the alphabetical index of a char
	public static int find(String ch) {
		String [] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"}; 
		int index = -1;
		for(int i = 0; i<alphabet.length; i++) {
			if(alphabet[i].equals(ch)) {
				index = i;
			}
		}
		return index;
	}

	public static void main(String[] args) throws IOException {

		//For runtime computations
		long startTime, endTime;
		double duration = 0;

		//Assert nameToKey works right
		// "alice" in radix-26 is 26^4*1 + 26^3*12 + 26^2*9 + 26^1*3 + 26^0*5 = 674055
		// "zoe" in radix-26 is 26^2*26 + 26^1*15 + 26^0*5 = 17971 
		assertEquals(nameToKey("alice"), 674055);
		assertEquals(nameToKey("zoe"), 17971);


		/**
		 * _______HashTable - BIG arraysize of 2^10=1024
		 */
		System.out.println("_______HashTable - BIG arraysize of 2^10=1024");
		startTime = System.currentTimeMillis();
		HashTable ht = new HashTable((int)Math.pow(2, 10)); 
		//Assert hashFunction works right for m=2^10
		assertEquals(ht.hashFunction(1000), 34);
		/**
		 * TODO:
		 * Read in the names from the names.txt file, and
		 * Insert all the keys and names in the hashtable by calling:
		 *  insert(nameToKey(name), name)
		 */
		File file = new File("C:\\Users\\14084\\eclipse-workspace\\proj5\\names.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String name; 
		while ((name = br.readLine()) != null)
			ht.insert(nameToKey(name), name); 
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable - BIG arraysize of 2^10=1024 - insertion runtime is " + duration);


		/**
		 * Verify that search returns the correct result
		 */
		startTime = System.currentTimeMillis();
		Node n = ht.search(nameToKey("aaaa"));
		assertEquals(n, null);
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for aaaa is " + duration);

		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("alice"));
		assertEquals(n.getKey(), 674055);
		assertEquals(n.getValue(), "alice");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for alice is " + duration);

		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("zoe"));
		assertEquals(n.getKey(), 17971);
		assertEquals(n.getValue(), "zoe");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for zoe is " + duration);

		
		/**
		 * _______HashTable - MEDIUM arraysize of 2^7=128
		 */
		System.out.println("_______HashTable - MEDIUM arraysize of 2^7=128");
		startTime = System.currentTimeMillis();
		ht = new HashTable((int)Math.pow(2, 7)); 
		//Assert hashFunction works right for m=2^7
		assertEquals(ht.hashFunction(1000), 4);
		/**
		 * TODO:
		 * Read in the names from the names.txt file, and
		 * Insert all the names in the hashtable
		 */
		BufferedReader bt = new BufferedReader(new FileReader(file)); 
		while ((name = bt.readLine()) != null)
			ht.insert(nameToKey(name), name); 
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable - MEDIUM arraysize of 2^7=128 - insertion runtime is " + duration);


		/**
		 * Verify that search returns the correct result
		 */
		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("aaaa"));
		assertEquals(n, null);
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for aaaa is " + duration);

		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("alice"));
		assertEquals(n.getKey(), 674055);
		assertEquals(n.getValue(), "alice");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for alice is " + duration);

		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("zoe"));
		assertEquals(n.getKey(), 17971);
		assertEquals(n.getValue(), "zoe");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for zoe is " + duration);

		
		/**
		 * _______HashTable - SMALL arraysize of 2^4=16
		 */
		System.out.println("_______HashTable - SMALL arraysize of 2^4=16");
		startTime = System.currentTimeMillis();
		ht = new HashTable((int)Math.pow(2, 4)); 
		//Assert hashFunction works right for m=2^4
		assertEquals(ht.hashFunction(1000), 0);
		/**
		 * TODO:
		 * Read in the names from the names.txt file, and
		 * Insert all the names in the hashtable
		 */
		BufferedReader by = new BufferedReader(new FileReader(file)); 
		while ((name = by.readLine()) != null)
			ht.insert(nameToKey(name), name); 
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable - SMALL arraysize of 2^4=16 - insertion runtime is " + duration);


		/**
		 * Verify that search returns the correct result
		 */
		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("aaaa"));
		assertEquals(n, null);
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for aaaa is " + duration);

		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("alice"));
		assertEquals(n.getKey(), 674055);
		assertEquals(n.getValue(), "alice");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for alice is " + duration);

		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("zoe"));
		assertEquals(n.getKey(), 17971);
		assertEquals(n.getValue(), "zoe");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for zoe is " + duration);

	}
}
