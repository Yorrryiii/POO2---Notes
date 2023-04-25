import java.util.HashSet;
import java.util.Set;

import Excepciones.PruebaRiego;
import HuertoUrbanoCompartido.HelperHuerto;
import HuertoUrbanoCompartido.HuertoUrbano;

public class Main {
    public static void main(String[] args) {
        double tamanoHuerto = 100; // tamaño del huerto en metros cuadrados
        int numParcelas = 5; // número de parcelas en el huerto
        Set<String> nombresPlantas = new HashSet<>(); // conjunto de nombres de plantas para el huerto
        nombresPlantas.add("Tomate");
        nombresPlantas.add("Lechuga");
        nombresPlantas.add("Zanahoria");
        nombresPlantas.add("Pimiento");
        nombresPlantas.add("Fresa");

        // crea un huerto con HelperHuerto
        HuertoUrbano huerto = HelperHuerto.createHuerto(tamanoHuerto, numParcelas, nombresPlantas);

        // riega el huerto con 10 litros de agua
        PruebaRiego.regarHuerto(huerto, 10);
    }
}

