import java.util.Scanner;

public class Main {
    
    public static void main(String[] args){
        Student student = getData();
        FirstGrader firstGrader = getDataFirstGrader();
        firstGrader.AllData();
        Teacher teacher = new Teacher();
        teacher.setGrade(firstGrader.getOcena());
        System.out.println(student.getName());
        System.out.println(teacher.getGrade());

    }

    public static Student getData(){
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, Student \nEnter your name: ");
        String name = input.nextLine();
        System.out.println("Enter your second name: ");
        String secondName = input.nextLine();
        System.out.println("Enter your ID: ");
        String id = input.nextLine();
        System.out.println("Enter your grade: ");
        double grade = input.nextDouble();

        return new Student(name, secondName, id, grade);
    }

    public static FirstGrader getDataFirstGrader(){
        Scanner input = new Scanner(System.in);
        System.out.println("\nHello, noob!\n Enter your name: ");
        String name = input.nextLine();
        System.out.println("Enter your second name: ");
        String secondName = input.nextLine();
        System.out.println("Enter your ID: ");
        String id = input.nextLine();
        System.out.println("Enter your grade: ");
        double grade = input.nextDouble();
        System.out.println("Enter your age: ");
        int age = input.nextInt();

        return new FirstGrader(name, secondName, id, grade, age);
    }

    


}