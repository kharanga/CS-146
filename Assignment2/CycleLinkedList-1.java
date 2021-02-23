/**
 * 
 */
package a2;

import static org.junit.Assert.*;

/**
 * Name:Khang Nguyen
 * Student ID: 013626431		
 * Description of solution: I using hare and tortoise method to find out if there is a cycle within a linked list
 * Both hare and tortoise would start at the head node, after every iteration hare would move forward by 2 nodes while tortoise would move forward by 1 node 
 * If there is no cycle the hare would be able to reach null(end of list) 
 * If there is a cycle the tortoise and hare would meet at a node within the cycle
 * @author name
 *
 */


public class CycleLinkedList {

	/*This function returns true if given linked 
	list has a cycle, else returns false. */
	
	public static boolean hasCycle( Node head) {
		// Creates a hare and tortoise that start at head
		Node hare = head;
		Node tortoise = head;
		while(hare.getNext()!=null) {
			// Tortoise move forward by 1 node
			tortoise= tortoise.getNext();
			// Hare moves forward by 2 nodes
			hare=hare.getNext().getNext();
			// Check if hare and tortoise are on the same node
			if(hare==tortoise) 
				return true;
		}
		return false;
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = new Node("start");
		Node prev = head;
		for (int i =0; i<4; i++) {
			Node temp = new Node(Integer.toString(i));
			prev.setNext(temp);
			prev=temp;
		}

		boolean b = hasCycle(head);
		System.out.println("Testing...");
		assertEquals(b, false);
		System.out.println("Success!");

		prev.setNext(head.getNext());

		b = hasCycle(head);
		System.out.println("Testing...");
		assertEquals(b, true);
		System.out.println("Success!");

	}

}
