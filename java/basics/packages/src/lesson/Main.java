package lesson;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Robot robot = new Robot();
        Coordinates coords = robot.getCoordinates();
        System.out.println("Robot coordinates: " + coords.x + ", " + coords.y);
        System.out.println("Robot direction: " + coords.direction);

        System.out.println("Game starts now!");
        System.out.println("Enter the number of steps to move:");
        Scanner scanner = new Scanner(System.in);
        int steps = scanner.nextInt();

        for (int i = 0; i < steps; i++) {
            System.out.println("Enter the direction to move: 1 - North, 2 - South, 3 - West, 4 - East");
            int direction = scanner.nextInt();
            switch (direction) {
                case 1:
                    coords.y++;
                    break;
                case 2:
                    coords.y--;
                    break;
                case 3:
                    coords.x--;
                    break;
                case 4:
                    coords.x++;
                    break;
                default:
                    System.out.println("Invalid direction");
            }
            System.out.println("Current coordinates: " + coords.x + ", " + coords.y);
             
        }

    }

}
