package Project;

public class Testdrive {
    
    public void SpeedTest(float speed1, float speed2, String number1, String number2){
        if (speed1 > speed2){
            System.out.println(number1 +" is greater than " + number2);
        } else if (speed1 < speed2){
            System.out.println(number1 + " is less than " + number2);
        } else {
            System.out.println(number1 + " is equal to " + number2);
        }
    }


    


}
