package Project;

public class Car extends Transport {

    
    
     public Car(float speed, int wheight, String color, byte[] coordinates, String NumberofCar){

        super(speed, wheight, color, coordinates, NumberofCar);
        
    }

    public Car(int wheight, byte [] coordinates){
        
        super(wheight, coordinates);
        
    }
    
}
