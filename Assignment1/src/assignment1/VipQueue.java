package assignment1;

public class VipQueue<T> {
	public Stack<T> stack;
	public Queue<T> queue;
	
	public VipQueue(){
		stack = new Stack<T>();
		queue = new Queue<T>();
	}
	
	public boolean isEmpty(){
		if(stack.isEmpty() && queue.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void vipEnqueue(T element){
		stack.push(element);
	}
	
	public void enqueue(T element){
		queue.enqueue(element);
	}
	
	public T dequeue(){
		if(!stack.isEmpty()){
			return stack.pop();
		}
		else {
			return queue.dequeue();
		}
	}
	
	public T peek(){
		if(!stack.isEmpty()){
			return stack.peek();
		}
		else if(!queue.isEmpty()){
			return queue.peek();
		}
		return null;
	}
}
