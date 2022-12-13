package Index;

import java.util.HashMap;

public class Node {
	private String key;
	private HashMap<String, Integer> value;
	
	public Node(String k) {
		key=k;
		value=null;
	}
	
	public Node(String k,String v) {
		this(k);
		value =new HashMap<String, Integer>();
		value.put(v, 1);
	}
	
	public void put(String v) {
		if(value.get(v)!=null) {
			value.replace(v, value.get(v)+1);
		}else {
			value.put(v,1);
		}
	}
	public String getKey() {
		return key;
	}
	
	public HashMap<String, Integer> getValue() {
		return value;
	}
}
