package pl.edu.pw.ee.aisd2025zex4.performance;

import java.util.Random;

public class DataGenerator {

    private final Random random = new Random();


    public String[] generateStringRandData(int size) {
        String[] data = new String[size];

        for (int i = 0; i < size; i++) {
            data[i] = String.valueOf(random.nextInt(Integer.MAX_VALUE));
        }

        return data;
    }


    public String[] generateStringAscData(int size) {
        String[] data = new String[size];

        for (int i = 0; i < size; i++) {
            data[i] = String.valueOf(i);
        }

        return data;
    }


    public String[] generateStringDescData(int size) {
        String[] data = new String[size];

        for (int i = 0; i < size; i++) {
            data[i] = String.valueOf(size - 1 - i);
        }

        return data;
    }
}
