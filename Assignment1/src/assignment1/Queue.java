package assignment1;

public class Queue<T> {
	private Node<T> first;
	private Node<T> last;
	
	public Queue(){
		first = null;
		last = null;
	}
	
	public boolean isEmpty(){
		if(first == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void enqueue(T element){
		Node<T> temp = new Node<T>(element);
		if(isEmpty()){
			first = temp;
			last = temp;
		}
		else{
			last.setNext(temp);
			last = temp;
		}
	}
	
	public T dequeue(){
			T t = first.getData();
			first = first.getNext();
			return t;
	}
	
	public T peek(){
		if(!isEmpty()){
			return first.getData();
		}
		else return null;
	}
	
}
