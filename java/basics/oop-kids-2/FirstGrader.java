public class FirstGrader extends Student 
{
    
    private int age;

    public FirstGrader(String name, String SecondName, String ID, double Ocena, int age) {
        super(name, SecondName, ID, Ocena);
        this.age = age;
    }

    public String GetName() {
        return super.getName();
    }

    public String GetSecondName() {
        return super.getSacondName();
    }

    public double GetOcena() {
        return super.getOcena();
    }

    public String GetStudentId() {
        return super.getStudentId();
    }
    public int GetAge() {
        return age;
    }

    public void AllData() {
        System.out.println("Name: " + GetName());
        System.out.println("Second Name: " + GetSecondName());
        System.out.println("ID: " + GetStudentId());
        System.out.println("Grade: " + GetOcena());
        System.out.println("Age: " + GetAge());
    }



}





    

