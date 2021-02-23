/**
 * 
 */
package a2;


/**
 * @author andreopo
 *
 */
public class Node {
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Node next;
	
	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node(String s) {
		name = s;
		next = null;
	}

	public Node(String s, Node n) {
		name = s;
		next = n;
	}
}

