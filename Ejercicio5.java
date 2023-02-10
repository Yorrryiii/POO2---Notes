public class Ejercicio5 {
    public static void main(String[] args) {
        for (int i = 0; i < 256; i++) {
            System.out.println(i + " " + Integer.toHexString(i) + " " + (char) i);
        }
    }
}
