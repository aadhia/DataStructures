import java.util.ArrayList;

public class HashTableImpl<K,V> implements HashTable<K,V> {

	private LinkedListNode<K,V>[] buckets;
	int tableSize;
	
	public HashTableImpl(){
		buckets = new LinkedListNode[1031];
		tableSize = buckets.length;
	}
	
	public void put (K key, V value){
		int i = key.hashCode() % tableSize;
		LinkedListNode<K,V> current = buckets[i];
		LinkedListNode<K,V> node = new LinkedListNode<K,V>(key, value, null);
		if(current == null){buckets[i] = node;}
		else{
		while(current.getNext()!=null){
			if(current.getKey().equals(key)){
				current.setValue(value);
				return;
			}
			current = current.getNext();
		}
			if(current.getKey().equals(key)){
				current.setValue(value);
			}
			else{current.setNext(node);}
		}
	}
	
	public V get(K key){
		int i = key.hashCode() % tableSize;
		LinkedListNode<K,V> current = buckets[i];
		while(current != null){
			if(current.getKey().equals(key)){
				return current.getValue();
			}
			current = current.getNext();
		}
		return null;
	}
	
	public V remove(K key){
		int i = key.hashCode() % tableSize;
		LinkedListNode<K,V> current = buckets[i];
		LinkedListNode<K,V> prev = null;
		while(current != null){
			if(current.getKey().equals(key)){
				if(prev == null){
					buckets[i] = current.getNext();
				}
				prev.setNext(current.getNext());
				return current.getValue();
			}
			prev = current;
			current = current.getNext();
		}
		return null;
	}
}
