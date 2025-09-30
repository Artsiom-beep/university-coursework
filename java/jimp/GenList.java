import java.util.*;

public class GenList implements Iterable<GenList>{
    
    public void GoEvery (ArrayList a){
        
        Iterator<Integer> it = a.iterator();
        while (it.hasNext()){
            int o = it.next();
            System.out.println(o);
        }
        
        
    }
    
    public Iterator <GenList> iterator(){
        return Collections.emptyIterator();
    }
    
    
    
}