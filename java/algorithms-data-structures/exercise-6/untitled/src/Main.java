import static java.lang.Math.max;
import static java.lang.Math.min;
import java.math.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int k = 10;

        int[] ceny = new int[k];
        Random random = new Random();

        ceny[0] = 10;
        for (int i = 1; i < k; i++) {
            int a = random.nextInt(15, 100);
            if (a < ceny[i - 1]) {
                ceny[i] = ceny[i - 1] + 5;
            } else {
                ceny[i] = a;
            }
        }

        int[] best = new int[k + 1];
        best[0] = 0;

        for (int l = 1; l <= k; l++) {
            int max = Integer.MIN_VALUE;
            for (int cut = 1; cut <= l; cut++) {
                int candidate = ceny[cut - 1] + best[l - cut];
                if (candidate > max) {
                    max = candidate;
                }
            }
            best[l] = max;
        }

        for (int i = 0; i < k; i++) {
            System.out.print(ceny[i] + " ");
        }
        System.out.println();

        for (int l = 1; l <= k; l++) {
            System.out.print(best[l] + " ");
        }
        System.out.println();
    }
}