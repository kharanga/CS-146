/**
 * 
 */
package bonus_assignment;


/**
 * @author andreopo
 * Khang Nguyen
 * 013626431
 */
public class Node {
	private int name;
	public int distance;
	public boolean visited;
	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	private Node next;
	
	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node(int s) {
		name = s;
		next = null;
		distance = Integer.MAX_VALUE;
		visited = false;
	}

	public Node(int s, Node n) {
		name = s;
		next = n;
		distance = Integer.MAX_VALUE;
		visited = false;
	}
	public void setDistance(int i) {
		distance = i;
	}
	public void setVisited() {
		visited = true;
	}
	public boolean getVisited() {
		return visited;
	}
	public int getDistance() {
		return distance;
	}
}

