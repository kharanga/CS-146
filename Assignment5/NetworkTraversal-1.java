package bonus_assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author andreopo
 * Khang Nguyen
 * 013626431
 */
public class NetworkTraversal {

	private Node[] adjacencyList= new Node[5000];
	private Queue<Node> queue = new LinkedList<>();
	// Read from a file and create a adjacency list
	public void createAdjacencyList() throws IOException {
		File file = new File("C:\\Users\\14084\\Documents\\Downloads\\3980.edges"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String str;
		while ((str = br.readLine()) != null) {
			str = str.replaceAll("(\\r|\\n)", ""); 
			String[] vertex_pair = str.split(" ",2);
			int x = Integer.parseInt(vertex_pair[0]);
			int y = Integer.parseInt(vertex_pair[1]);
			insert(x,new Node(y));
			insert(y,new Node(x));

		}

	}
	// Helper method of createAdjacencyList
	// Insert a Node(y) into the adjancencyList[x] and vice versa
	public void insert(int index ,Node node) {
		Node start = adjacencyList[index];
		if(adjacencyList[index]==null) {
			adjacencyList[index]= new Node(index);
			adjacencyList[index].setNext(node);
		}
		else {
			while(start.getNext()!=null) 
				start = start.getNext();
			start.setNext(node);
		}
	}
	// Search the shortest path using BFS
	public void BFS (int start) {
		BFShelper(start,1);
	}
	// BFS helper method
	public void BFShelper(int start, int distance) {
		Node node = adjacencyList[start];
		node.setVisited();
		while(node!=null) {
			if(node.getVisited()==false) {
				node.setDistance(distance);
				node.setVisited();
				if(node.getName()==3980) {
					//print out distance
					System.out.println("Distance to 3980 is " + node.getDistance());
					return;
				}
				//add to queue
				queue.add(node);
			}
			node = node.getNext();
		}
		//remove from queue
		Node removed = queue.poll();
		if(removed != null)
			BFShelper(removed.getName(), removed.getDistance()+1);
	}

	public static void main(String[] args) throws IOException {
		NetworkTraversal nt = new NetworkTraversal();
		nt.createAdjacencyList();
		nt.BFS(4014);
		nt.BFS(3985);
	
	
	}
}

