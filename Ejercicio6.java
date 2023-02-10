import java.util.Scanner;

public class Ejercicio6 {

    public static void imprimirTablero(int tam, String blanco, String negro) {
        for (int row = 0; row < tam; row++) {
            for (int col = 0; col < tam; col++) {
                if ((row + col) % 2 == 0) {
                    System.out.print(blanco + " ");
                } else {
                    System.out.print(negro + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el tamaño del tablero: ");
        int tam = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el carácter para las casillas blancas: ");
        String blanco = sc.nextLine().trim();
        System.out.print("Ingrese el carácter para las casillas negras: ");
        String negro = sc.nextLine().trim();

        System.out.println("A continuación, se muestra el tablero de tamaño \"" + tam + "\", con el caracter \"" + blanco + "\" en las casillas blancas y el caracter \"" + negro + "\" en las casillas negras:");

        imprimirTablero(tam, blanco, negro);
    }
}
