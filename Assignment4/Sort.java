package a4;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;

/**
 * Template code for assignment 4, CS146
 * @author andreopo
 * Khang Nguyen
 * 42013059 
 */
public class Sort {

	/**
	 * Build a random array
	 * @param rand a Random object
	 * @param LENGTH The range of the integers in the array 
	 *             will be from 0 to LENGTH-1
	 * @return
	 */
	private static int[] build_random_array(Random rand, int LENGTH) {
		int[] array = new int[LENGTH];
		//set index 0 to 0 for consistency with CLRS, where sorting starts at index 1
		array[0] = 0; 
		for (int i = 1; i < LENGTH; i++) {
			// Generate random integers in range 0 to 999 
			int rand_int = rand.nextInt(LENGTH); 
			array[i] = rand_int;
		}
		return array;
	}

	/**
	 * Assert the array is sorted correctly
	 * @param array_unsorted The original unsorted array
	 * @param array_sorted The sorted array
	 */
	public static void assert_array_sorted(int[] array_unsorted, int[] array_sorted) {
		int a_sum = array_unsorted[0];
		int b_sum = array_sorted[0];
		for (int i = 1; i < array_unsorted.length; i++) {
			a_sum += array_unsorted[i];
		}
		for (int i = 1; i < array_sorted.length; i++) {
			b_sum += array_sorted[i];
			assertTrue(array_sorted[i-1] <= array_sorted[i]);
		}
		assertEquals(a_sum, b_sum);
	}

	/* Compared a values and moving it backward until is no longer smaller than
	 * the values before it.
	 */
	public static void insertionSort(int[] array) {
		for(int i = 1; i<array.length; i++) {
			int key = array[i];
			int j = i-1;
			// Check if the key value is smaller than its left neighbor
			while(j>=0&& key<array[j]){
				// Move every values after j up a index
				array[j+1] = array[j];
				j--;
			}
			// Set the current index to key
			array[j+1] = key;
		}
	}
	/* Look for the smallest available value and place it at the beginning
	 * of the unsorted part of an array.
	 */
	public static void selectionSort(int[] array) {
		for(int i = 0; i < array.length-1; i++) {
			int min = i;
			for(int j = i+1; j<array.length; j++) {
				// Check if the min value is smaller than the current value of not
				if(array[min]>array[j]) {
					// Set min to the current index
					min = j;
				}
			}
			// Switch min and the first index within the unsorted part
			int temp = array[min];
			array[min] = array[i];
			array[i] = temp;
		}
	}
	public static void heapSort(int[] array) 
	{ 
		int n = array.length; 

		// Build heap
		for (int i = n / 2 - 1; i >= 0; i--) 
			heapify(array, n, i); 

		// Extract an element from heap 
		for (int i=n-1; i>=0; i--) 
		{ 
			// Move current root to end 
			int temp = array[0]; 
			array[0] = array[i]; 
			array[i] = temp; 

			// Call max heapify on the reduced heap 
			heapify(array, i, 0); 
		} 
	} 
	public static void heapify(int array[], int n, int i) 
	{ 
		int largest = i; // Initialize largest as root 
		int l = 2*i + 1; 
		int r = 2*i + 2;

		// If left child is larger than root 
		if (l < n && array[l] > array[largest]) 
			// Set left child to largest
			largest = l; 

		// If right child is larger than largest so far 
		if (r < n && array[r] > array[largest]) 
			// Set right child to largest
			largest = r; 

		// If largest is not root 
		if (largest != i) 
		{ 
			int swap = array[i]; 
			array[i] = array[largest]; 
			array[largest] = swap; 

			// Recursively heapify the affected sub-tree 
			heapify(array, n, largest); 
		} 
	} 

	public static void mergeSort(int[] a) {
		int n = a.length; 
		// If array equal to one or 0 return the array
		if (n < 2) {
			return;
		}
		int mid = n / 2; // Find mid point
		int[] l = new int[mid]; // Create an array with half of the elements 
		int[] r = new int[n - mid]; // Creat an array with half of the elements
		// Input all left side values into the temporary array
		for (int i = 0; i < mid; i++) {
			l[i] = a[i];
		}
		// Input all right side values into the temporary array
		for (int i = mid; i < n; i++) {
			r[i - mid] = a[i];
		}
		// Call mergesort recursively to sort the two half on the original array
		mergeSort(l);
		mergeSort(r);
		// Merge the left and right sorted arrays
		merge(a, l, r, mid, n - mid);
	}
	// Merge left and right sorted arrays into one
	public static void merge(int[] a, int[] l, int[] r, int left, int right) {

		int i = 0, j = 0, k = 0;
		/* While both left and right still content elements 
		 * Compare elements from both side and sort them accordingly
		 */
		while (i < left && j < right) {
			if (l[i] <= r[j]) {
				a[k++] = l[i++];
			}
			else {
				a[k++] = r[j++];
			}
		}
		// While right side run out of their elements input the rest of the left side into the array
		while (i < left) {
			a[k++] = l[i++];
		}
		// While left side run out of their elements input the rest of the right side into the array
		while (j < right) {
			a[k++] = r[j++];
		}
	}

	public static int partition(int array[], int low, int high) 
	{ 
		int pivot = array[high];  
		int i = (low-1); // index of smaller element 
		for (int j=low; j<high; j++) 
		{ 
			// If current element is smaller than the pivot 
			if (array[j] < pivot) 
			{ 
				i++; 

				// swap array[i] and array[j] 
				int temp = array[i]; 
				array[i] = array[j]; 
				array[j] = temp; 
			} 
		} 

		// swap array[i+1] and array[high] ( 
		int temp = array[i+1]; 
		array[i+1] = array[high]; 
		array[high] = temp; 

		return i+1; 
	} 

	public static void quickSort(int array[]) 
	{ 
		int low = 0; // begin
		int high = array.length-1; // end 
		quickSort(array,low,high);

	}
	// @Override
	public static void quickSort(int arr[], int begin, int end) {
		if (begin < end) {
	        int partitionIndex = partition(arr, begin, end);
	 
	        quickSort(arr, begin, partitionIndex-1);
	        quickSort(arr, partitionIndex+1, end);
	    }
	}

	public static void bucketSort(int[] array) {
		int bucketCount = array.length/2;
		int minIntValue = 0;
		int maxIntValue = array.length - 1;
		// Create bucket array
		List<Integer>[] buckets = new List[bucketCount];
		// Associate a list with each index in the bucket array         
		for(int i = 0; i < bucketCount; i++){
			buckets[i] = new LinkedList<>();
		}

		// Assign integers from array to the proper bucket
		for (int i = 0; i < array.length; i++) {
            buckets[(array[i] - minIntValue) / bucketCount].add(array[i]);
        }

		// sort buckets
		for(List<Integer> bucket : buckets){
			Collections.sort(bucket);
		}
		int i = 0;
		// Merge buckets to get sorted array
		for(List<Integer> bucket : buckets){
			for(int num : bucket){
				array[i++] = num;
			}
		}
	}

	public static void main(String[] args) {
		int NUM_RUNS = 3;
		// create instance of Random class 
		Random rand = new Random(); 

		/////////////////////////////////////////
		int LENGTH=100;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_100 = build_random_array(rand, LENGTH);

		//For runtime computations
		long startTime, endTime;

		double duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);


		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		/////////////////////////////////////////
		LENGTH=1000;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_1000 = build_random_array(rand, LENGTH);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);


		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);



		/////////////////////////////////////////
		LENGTH=10000;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_10000 = build_random_array(rand, LENGTH);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);


		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);


		/////////////////////////////////////////
		LENGTH=100000;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_100000 = build_random_array(rand, LENGTH);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);


		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

	}

}
