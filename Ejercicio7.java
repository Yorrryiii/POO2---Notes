import java.util.Scanner;

public class Ejercicio7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la altura de la pirámide: ");
        int height = sc.nextInt();
        System.out.println("Ingrese la dirección de la pirámide (arriba, derecha, abajo, izquierda): ");
        String direction = sc.next();
        printPyramid(height, direction);
    }

    public static void printPyramid(int height, String direction) {
        switch (direction) {
            case "arriba":
                for (int i = 1; i <= height; i++) {
                    for (int j = height - i; j >= 1; j--) {
                        System.out.print(" ");
                    }
                    for (int j = 1; j <= i; j++) {
                        System.out.print("* ");
                    }
                    System.out.println();
                }
                break;
            case "derecha":
                // for (int i = 1; i <= height; i++) {
                //     for (int j = 1; j <= i; j++) {
                //         System.out.print("* ");
                //     }
                //     System.out.println();
                // }
                break;
            case "abajo":
                for (int i = height; i >= 1; i--) {
                    for (int j = height - i; j >= 1; j--) {
                        System.out.print(" ");
                    }
                    for (int j = 1; j <= i; j++) {
                        System.out.print("* ");
                    }
                    System.out.println();
                }
                break;
            case "izquierda":

                break;
            default:
                System.out.println("Opción inválida");
        }
}}
