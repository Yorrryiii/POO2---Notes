public class Ejercicio2 {
    public static void main(String[] args) {
        int n = 10;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0 && i % 3 == 0){
                System.out.println(i + " PAR TRIO");
            } else if (i % 2 == 0) {
                System.out.println(i + " PAR");
            } else if (i % 3 == 0) {
                System.out.println(i + " TRIO");
            } else {
                System.out.println(i);
            }
        }
    }
}
