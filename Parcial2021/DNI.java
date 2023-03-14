package Parcial2021;

public class DNI {
    // 1.1
    protected int numeros;
    protected char letra;

    // 1.2
    public static char calculaLetra(int numero) {
        String posiblesLetras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int suma = 0;
        int resto;
        char letra;

        while (numero > 0) {
            suma += numero % 10;
            numero /= 10;
        }

        resto = suma % 26;
        letra = posiblesLetras.charAt(resto);

        return letra;
    }

    // 1.3
    public DNI(int numeros) {
        this.numeros = numeros;
        this.letra = calculaLetra(numeros);
    }

    public DNI() {
        this.numeros = (int) (Math.random() * 100000000);
        this.letra = calculaLetra(numeros);
    }

    public DNI(int numeros, char letra) {
        this.numeros = numeros;
        this.letra = letra;
    }

    public boolean esValido() {
        return this.letra == calculaLetra(this.numeros);
    }

    // 1.4
    public static boolean sonDistintos(DNI[] dnis) {
        boolean distintos = true;
        int i = 0;

        while (i < dnis.length - 1 && distintos) {
            if (dnis[i].numeros == dnis[i + 1].numeros) {
                distintos = false;
            }
            i++;
        }

        return distintos;
    }

    // 1.5
    @Override
    public String toString() {
        String numero = String.format("%08d", this.numeros);
        return numero + "-" + this.letra + " (" + this.esValido() + ")";
    }
}
