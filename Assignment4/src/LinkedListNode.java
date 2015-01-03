
public class LinkedListNode<K,V> {

	private K key;
	private V value;
	private LinkedListNode<K,V> next;
	
	public LinkedListNode(K key, V value, LinkedListNode<K,V> next){
		this.key = key;
		this.value = value;
		this.next = next;
	}	
	
	public K getKey(){
		return key;
	}
	
	public V getValue(){
		return value;
	}
	
	public void setValue(V value){
		this.value = value;
	}

	public void setNext( LinkedListNode<K,V> next){
		this.next = next;
	}
	
	public LinkedListNode<K,V> getNext(){
		return next;
	}
}
