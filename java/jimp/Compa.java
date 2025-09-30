import java.util.*;

public class Compa implements Iterable<Map.Entry<String, Integer>> {
    private Map<String, Integer> kosz = new HashMap<>();
    
    public void putproduct(String towar, int Ilosc){
        if(kosz.containsKey(towar)) {
            kosz.replace(towar, Ilosc + kosz.get(towar));
        }
        else{
            kosz.put(towar, Ilosc);
        }
    }
    
    @Override
    public Iterator<Map.Entry<String, Integer>> iterator() {
        return kosz.entrySet().iterator();
    }
    
    
}