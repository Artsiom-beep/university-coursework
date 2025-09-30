import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Main {


    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter name of file: ");
        String nameofFile = scanner.next();

        try{
            File file = new File (nameofFile);
        Scanner cin = new Scanner(file);
        String a = cin.nextLine();
        a = cin.nextLine();
        a = cin.nextLine();
        System.out.println(a);
    }
    catch(FileNotFoundException e){
        System.out.println("File not found");
    }

        scanner.close();
       
    }
    
}
