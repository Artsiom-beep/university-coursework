public class Student {
    private String name;
    private String SecondName;
    private double Ocena;
    private String studentId;

    public Student(String name, String SecondName, String ID, double Ocena) {
        this.name = name;
        this.Ocena = Ocena;
        this.SecondName = SecondName;
        this.studentId = ID;
    }

   
    public String getName() {
        return name;
    }

    public String getSacondName(){
        return SecondName;
    }

    public double getOcena() {
        return Ocena;
    }

    public String getStudentId() {
        return studentId;
} 
    
}
