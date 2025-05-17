import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {

    private ArrayList<String> stack = new ArrayList<String>();
    private int[] Sum;
    private int Ans = 0;
    private int n;




    public Calculator(int n){
        this.n = n;
        Sum = new int[n];
    }

    public void push(String s){
        stack.add(s);
    }

   private void convert(){
    for(int i = 0; i < n; i++){
        Sum[i] = Integer.parseInt(stack.get(i));
    }
   }
   
   private void SUMA(){
        convert();
        for(int i = 0; i < Sum.length; i++){
            Ans += Sum[i];
        }
   }

   public int getAns(){
    SUMA();
    return Ans;
   }


    
}
