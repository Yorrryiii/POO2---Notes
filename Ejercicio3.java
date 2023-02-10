public class Ejercicio3 {
    // Crea una funcion recursiva para calcular:
    // a) el MCD de dos números
    // b) el factorial de un número
    // c) determinar si un numero es primo
    // d) el valor de Fibonacci para la posición dada

    public static int mcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return mcd(b, a % b);
        }
    }

    public static float factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static boolean esPrimo(int num, int i) {
        if (i == 1) {
            return true;
        }
        if (num % i == 0) {
            return false;
        }
        return esPrimo(num, i - 1);
    }
    
    public static boolean checkPrime(int num) {
        return esPrimo(num, num / 2);
    }

    public static int fibonacci(int num) {
        if (num <= 1) {
            return num;
        }
        return fibonacci(num - 1) + fibonacci(num - 2);
    }
    
    public static void main(String[] args) {
        System.out.println("MCD de 12215 y 32332: " + mcd(12215, 32332));
        System.out.println("MCD de 2366 y 273: " + mcd(2366, 273));
        System.out.println("Factorial de 5: " + factorial(5));
        System.out.println("Factorial de 39: " + factorial(39));
        System.out.println("Es primo 39: " + checkPrime(39));
        System.out.println("Es primo 2039: " + checkPrime(2039));
        System.out.println("Fibonacci de 5: " + fibonacci(5));
        System.out.println("Fibonacci de 30: " + fibonacci(30));
    }
}
