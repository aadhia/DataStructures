package assignment1;

public class Stack<T> {
	private Node<T> top;
	
	public Stack(){
		top = null;
	}
	
	public boolean isEmpty(){
		if(top == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void push(T element){
		Node<T> temp = new Node<T>(element);
		if(!isEmpty()){
			temp.setNext(top);
		}
		top = temp;
	}
	
	public T peek(){
		if(!isEmpty()){
			return top.getData();
		}
		else return null;
	}
	
	public T pop(){
			T t = top.getData();
			top = top.getNext();
			return t;
	}
}
