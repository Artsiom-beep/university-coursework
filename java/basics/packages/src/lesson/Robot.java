package lesson;

import java.util.Scanner;

public class Robot {

    public Coordinates getCoordinates() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates of the robot:");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        System.out.println("Enter direction of the robot: 1 - North, 2 - South, 3 - West, 4 - East");
        int direction = scanner.nextInt();

        return new Coordinates(x, y, direction);
    }

}