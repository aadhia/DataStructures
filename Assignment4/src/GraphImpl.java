
public class GraphImpl extends Graph{
	
	private AdjacencyListNode[] list;
	private HashTable<String, Node> table = HashTableFactory.create();
	private int[] indegrees;
	
	private int nodeCount;
	
	public GraphImpl(int nodeCount){
		this.nodeCount = nodeCount;
		indegrees = new int[nodeCount];
		list = new AdjacencyListNode[nodeCount];
	}
	
	public void addNode(Node node){
		table.put(node.getName(), node);
		list[node.getId()]=new AdjacencyListNode(node,null);
	}
	
	public void addEdge(Node node1, Node node2){
		AdjacencyListNode current = list[node1.getId()];
		while(current.getNext() != null){
			current = current.getNext();
		}
		current.setNext(new AdjacencyListNode(node2, null));
		indegrees[node2.getId()]++;
	}
	
	public Node lookupNode(int id){
		return list[id].getNode();
	}
	
	public Node lookupNode(String name){
		return table.get(name);
	}
	
	public boolean isAcyclic(){
		int position = 0;
		int[] indegreesCopy = new int[nodeCount];
		for(int i = 0; i < indegrees.length; i++){
			indegreesCopy[i] = indegrees[i];
		}
		
		while(position<nodeCount){
			boolean zero = false;
			for(int i = 0; i <nodeCount; i++){
				if(indegreesCopy[i] == 0){
					zero = true;
					indegreesCopy[i]--;
					position++;
					AdjacencyListNode current = list[i];
					while(current.getNext() != null){
						current = current.getNext();
						indegreesCopy[current.getNode().getId()]--;
					}
				}
			}
			if(!zero){return false;}
		}
		return true;
	}
	
	public int[] sort(){
		int[] order = new int[nodeCount];
		int position = 0;
		while(position<nodeCount){
			for(int i = 0; i <nodeCount; i++){
				if(indegrees[i] == 0){
					order[position++] = i;
					indegrees[i]--;
					AdjacencyListNode current = list[i];
					while(current.getNext() != null){
						current = current.getNext();
						indegrees[current.getNode().getId()]--;
					}
				}
			}
		}
		return order;
	}

}
