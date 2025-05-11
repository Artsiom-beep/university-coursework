package Project;


public class Main {

    public static void main(String args[]){

        Car bmw = new Car(250.5f, 1500, "White", new byte[] {0, 0, 0}, "BMP-ORESHNIK");
        TruckBig truck = new TruckBig(5600, new byte[] {100, 0, 100}, false);
        truck.setValues(120.0f, 5600, "Black", new byte[] {100, 0, 100}, "Belaz");
        Testdrive speedTest = new Testdrive();
        System.out.println(bmw.getValues());
        System.out.println(truck.getValues());
        speedTest.SpeedTest(bmw.speed(), truck.speed(), bmw.getNumber(), truck.getNumber());

        truck.setLoaded(true);
        truck.getLoaded();
         
       
    }

}
