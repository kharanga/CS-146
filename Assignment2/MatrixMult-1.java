package a2;

import static org.junit.Assert.*;

/**
 * Name: Khang Nguyen		
 * Student ID: 013626431	
 * Description of solution: I'm using Strassen's Matrix Multiplication
 * I basically divides the matrixes into smaller sub matrixes, calculates the 7 products, then calculates the value of each index using those product
 * and then joins them together into a single matrix 
 * Explanation
 * 	- Master's Theorem case 1 because c=2, and log(7)=2.8, which mean c<logb(a)
 * 	- The time complexity of the method above is O(N^2.80)
 *  - I expects this method to run faster then the naive method since the time complexity for the naive method is O(N^3)
 * @author name
 *
 */
public class MatrixMult {

    /** Function to multiply matrices 
     *  Assumes A and B are square and have the same size!
     *  @param A - matrix A to multiply (first operand)
     *  @param B - matrix B to multiply (second operand)
     ***/
    public static int[][] multiply(int[][] A, int[][] B)
    {        
        int n = A.length;
        int[][] R = new int[n][n];
        /** base case **/
        if (n == 1)
            R[0][0] = A[0][0] * B[0][0];
        else
        {
        	/**
        	 * FILL IN
        	 */  	
        	// Creates 8 new matrixes and stores value at certain index that is equivalent to its name 
        	int[][] A11 = new int[n/2][n/2];
            int[][] A12 = new int[n/2][n/2];
            int[][] A21 = new int[n/2][n/2];
            int[][] A22 = new int[n/2][n/2];
            int[][] B11 = new int[n/2][n/2];
            int[][] B12 = new int[n/2][n/2];
            int[][] B21 = new int[n/2][n/2];
            int[][] B22 = new int[n/2][n/2];
            split(A, A11, 0 , 0);
            split(A, A12, 0 , n/2);
            split(A, A21, n/2, 0);
            split(A, A22, n/2, n/2);
            split(B, B11, 0 , 0);
            split(B, B12, 0 , n/2);
            split(B, B21, n/2, 0);
            split(B, B22, n/2, n/2);
            // Calculate 7 products that requires by the Strassen's method
            int [][] p1 = multiply(add(A11, A22), add(B11, B22));
            int [][] p2 = multiply(add(A21, A22), B11);
            int [][] p3 = multiply(A11, sub(B12, B22));
            int [][] p4 = multiply(A22, sub(B21, B11));
            int [][] p5 = multiply(add(A11, A12), B22);
            int [][] p6 = multiply(sub(A21, A11), add(B11, B12));
            int [][] p7 = multiply(sub(A12, A22), add(B21, B22));
            // Calculate the value of each index within the product matrix
            int [][] C11 = add(sub(add(p1, p4), p5), p7);
            int [][] C12 = add(p3, p5);
            int [][] C21 = add(p2, p4);
            int [][] C22 = add(sub(add(p1, p3), p2), p6);
            // Input those values above into the product matrix
            join(C11, R, 0 , 0);
            join(C12, R, 0 , n/2);
            join(C21, R, n/2, 0);
            join(C22, R, n/2, n/2);
        }
        /** return result **/    
        return R;
    }
    
    /** 
     * Function to subtract two matrices A and B
     ***/
    public static int[][] sub(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }
    
    /** 
     * Function to add two matrices A and B
     ***/
    public static int[][] add(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }
    
    /** 
     * Function to split parent matrix into child matrices.
     * Assumes C is initialized.
     * @param P - parent matrix
     * @param C - A child matrix that will get the corresponding indices of the parent
     * @param iB - start row in parent
     * @param jB - start column in parent
     ***/
    public static void split(int[][] P, int[][] C, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }
    
    /** 
     * Function to join child matrices into a parent matrix 
     * Assumes C is initialized.
     * @param P - parent matrix
     * @param C - A child matrix that will provide the corresponding indices of the parent
     * @param iB - start row in parent
     * @param jB - start column in parent
     ***/
    public static void join(int[][] C, int[][] P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Ensure that multiplying the two matrices gives the correct result!
		int[][] A = {{1, 2}, {3, 4}};
		int[][] B = {{5, 6}, {7, 8}};

		int[][] C = multiply(A, B);
		System.out.println(C.toString());
		System.out.println("Testing...");
		assertEquals(C[0][0], 19);
		assertEquals(C[0][1], 22);
		assertEquals(C[1][0], 43);
		assertEquals(C[1][1], 50);
		System.out.println("Success!");
	}

}
