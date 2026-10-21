import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        int a;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter amount of numbers:");
        a = input.nextInt();
        input.nextLine(); 
        Calculator summa = new Calculator(a);
        for(int i = 0; i < a; i++){
            System.out.println("Enter number " + (i+1) + ":");
            summa.push(input.nextLine());
        }

        a = summa.getAns();
        System.out.println("Answer is: " + a);

        DevidedbyTwo fork = new DevidedbyTwo();
        fork.sayHelloWorld();

    }
    
}
