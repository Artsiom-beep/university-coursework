import java.util.*;

public class Main
{
	public static void main(String[] args) {
		ArrayList <Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		GenList wrp = new GenList();
		wrp.GoEvery(list);
		
		for(int next: list){
		    System.out.println(next);
		}
		
		Compa koszyk = new Compa();
		koszyk.putproduct("Milk", 20);
		koszyk.putproduct("Water", 20);
		koszyk.putproduct("Milk", 20);
		
	
	 for (Map.Entry<String, Integer> entry : koszyk) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
		
	}
	
	
	
	

    
}