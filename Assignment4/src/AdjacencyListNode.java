
public class AdjacencyListNode {

	private Node node;
	private AdjacencyListNode next;
	
	public AdjacencyListNode(Node node, AdjacencyListNode next){
		this.node = node;
		this.next = next;
	}
	
	public Node getNode(){
		return node;
	}
	
	public AdjacencyListNode getNext(){
		return next;
	}
	
	public void setNext(AdjacencyListNode next){
		this.next = next;
	}
}
