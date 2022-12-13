import Index.Index;

public class Test {

	public static void main(String[] args) {
		Index index= new Index();
		index.setLoadFactor(0.5);
		index.useSSF();
		index.useLP();
		
		String a="a.txt";
		
		index.put("car", a);
		index.put("school",a);
		index.put("rac",a);
		index.put("rac", a);
		
		
		
	}

}
