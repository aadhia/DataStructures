
public class NodeImpl implements Node{

	private static int id_counter = 0;
	private String name;
	private int id;
	
	public NodeImpl(String name){
		this.name = name;
		id = id_counter++;
	}
	
	public String getName(){
		return name;
	}
	
	public int getId(){
		return id;
	}
}
