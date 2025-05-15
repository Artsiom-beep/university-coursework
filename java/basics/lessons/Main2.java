public class Main2 {

    public static void sortirovka(int [] a){
        int pom = 0;
        for(int i = 0; i < a.length; i++) {
            for(int j = i; j < a.length; j++){
                if(a[i] > a[j]){
                    pom = a[i];
                    a[i] = a[j];
                    a[j] = pom;
                }
            }
        }
    }


    public static int[] mergeTwoArrays(int[] a, int[] b) {
    int[] c = new int[a.length + b.length];
    int i = 0, j = 0, k = 0;

    while (i < a.length && j < b.length) {
        if (a[i] < b[j]) {
            c[k++] = a[i++];
        } else {
            c[k++] = b[j++];
        }
    }

    while (i < a.length) {
        c[k++] = a[i++];
    }

    while (j < b.length) {
        c[k++] = b[j++];
    }

    return c;
}


    public static void main(String[] args) {
        int [] a = new int[4];
        int [] b = {5, 6, 7, 8};

        System.out.println("Please enter 4 numbers");
        for(int i = 0; i < 4; i++){
            a[i] = Integer.parseInt(System.console().readLine()); 
        }

        sortirovka(a);
        sortirovka(b);
    

        
        int[] c = mergeTwoArrays(a, b);
        System.out.println("\nMerged c:");
        for (int i : c) {
            System.out.print(i + " ");
        }

    }
    

    
}
