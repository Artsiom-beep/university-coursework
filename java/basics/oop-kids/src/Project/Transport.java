package Project;


public class Transport {

    protected float speed;
    protected int wheight;
    protected String color;
    protected byte[] coordinates;
    private String NumberofCar;


    public Transport(float speed, int wheight, String color, byte[] coordinates, String NumberofCar){

        setValues(speed, wheight, color, coordinates, NumberofCar);
        
    }

    public Transport(int wheight, byte [] coordinates){
        this.wheight = wheight;
        this.coordinates = coordinates;
        
    }

    public void setValues(float speed, int wheight, String color, byte[] coordinates, String NumberofCar)
    {
        this.speed = speed;
        this.wheight = wheight;
        this.color = color; 
        this.coordinates = coordinates;
        this.NumberofCar = NumberofCar;
    }

    public String getValues(){

        return "\nObject speed: " + speed + "\n" +
               "Object wheight: " + wheight + "\n" +
               "Object color: " + color + "\n" +
               "Object coordinates: " + coordinates[0] + ", " + coordinates[1] + ", " + coordinates[2]
               + "\nObject NumberofCar: " + NumberofCar + "\n";
    }

    public String getNumber(){
        return NumberofCar;
    }


    public float speed(){
        return speed;
    }
    




}
