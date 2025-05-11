package Project;

public class TruckBig extends Transport{
    
    private boolean isLoaded;

    public void setLoaded(boolean isLoaded){
        this.isLoaded = isLoaded;
    }

    public void getLoaded(){
        if(isLoaded){
            System.out.println("Truck is loaded");
        } else {
            System.out.println("Truck is not loaded");
        }
    }


     public TruckBig(float speed, int wheight, String color, byte[] coordinates, String NumberofCar){

        super(speed, wheight, color, coordinates, NumberofCar);
        
    }

    public TruckBig(int wheight, byte [] coordinates, boolean isLoaded){
        super(wheight, coordinates);
        this.isLoaded = isLoaded;
    }




}
