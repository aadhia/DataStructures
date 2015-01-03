package assignment1;

public class Node<T> {
	private T data;
	private Node<T> next;
	
	public Node(T t){
		this.next = null;
		data = t;
	}
	
	public T getData(){
		return data;
	}
	
	public void setNext(Node<T> next){
		this.next = next;
	}
	
	public Node<T> getNext(){
		return next;
	}
}
