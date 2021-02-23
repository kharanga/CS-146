/**
 * 
 */
package a3;

import static org.junit.Assert.*;


/**
 * 
 *
 */
public class MaxHeap {

    private static int[] HeapArray; 
    public int[] getHeapArray() {
		return HeapArray;
	}

	private static int size; 
    private int maxsize; 
  
    private static final int FRONT =1; 
  
    public MaxHeap(int maxsize) 
    { 
        this.maxsize = maxsize; 
        this.size = maxsize;
        HeapArray = new int[maxsize]; 
        //initialize heap array to a set of numbers, rearranged a little
        for (int i = FRONT; i < HeapArray.length; i++) {
        	HeapArray[i] = maxsize-i;
        }
    } 
  
    // Return the index of the parent of the node currently at pos 
    private int parent(int pos) 
    { 
        return (pos / 2); 
    } 
  
    // Return the index of the leftchild of the node currently at pos 
    private int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
  
    // Return the index of the rightchild of the node currently at pos 
    private int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 


    //Function to heapify the node at position i, up to the position n 
    private void maxHeapify(int [] A, int i, int n) 
    { 
        /**
         * TODO implement these as shown in class.
         */
    	int largest;
    	int l = leftChild(i);
    	int r = rightChild(i);
    	if(l<=n && A[l]>A[i]) {
    		largest = l;
    	}
    	else {
    		largest = i;
    	}
    	if(r<=n && A[r]>A[largest]) {
    		largest = r;
    	}
    	if (largest != i) { 
    		swap(i,largest);
    		maxHeapify(A,largest,n);
    	}
    }

    
    public void buildMaxHeap(int []A, int n) {
        /**
         * TODO implement these as shown in class.
         */
    	for(int i = n/2; i>=1; i--) {
    		maxHeapify(A,i,n);
    	}
    }

    public void sort(int []A, int n) {
        /**
         * TODO implement these as shown in class.
         */
    	buildMaxHeap(A,n);
    	for(int i = n; i>=2; i--) {
    		swap(1,i);
    		maxHeapify(A,1,i-1);
    	}
    }

    
    
    // Swap two nodes of the heap at index first second
    private void swap(int first, int seconds) 
    { 
        int tmp; 
        tmp = HeapArray[first]; 
        HeapArray[first] = HeapArray[seconds]; 
        HeapArray[seconds] = tmp; 
    } 
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int SIZE = 30;
		MaxHeap1 heap = new MaxHeap1(SIZE);
		heap.sort(HeapArray,size);
		int[] heapArr = heap.getHeapArray();
		assertEquals(heapArr[0], 0);
		assertEquals(heapArr[1], 1);
		assertEquals(heapArr[2], 2);
		assertEquals(heapArr[SIZE-1], SIZE-1);
		assertEquals(heapArr.length, SIZE);
	}

}

