package excepciones;

import java.util.HashSet;
import java.util.Set;

import huertoUrbanoCompartido.HelperHuerto;
import huertoUrbanoCompartido.HuertoUrbano;

public class PruebaRiego {
    public static void regarHuerto(HuertoUrbano huerto, double litros) {
        try {
            huerto.regar(litros);
            System.out.println("Se han utilizado " + litros + " litros para regar el huerto.");
        } catch (AguaInsuficienteException e) {
            System.out.println("No ha habido suficiente agua.");
            double litrosIncrementados = litros + 10.0;
            regarHuerto(huerto, litrosIncrementados);
        }
    }
    
    public static void main(String[] args) {
        double tamañoHuerto = 5000.0;
        int numParcelas = 22;
        
        Set<String> nombresPlantas = new HashSet<>();
        nombresPlantas.add("Tomate");
        nombresPlantas.add("Cebolla");
        nombresPlantas.add("Lechuga");
        nombresPlantas.add("Zanahoria");
        nombresPlantas.add("Albahaca");
        
        HuertoUrbano huerto = HelperHuerto.createHuerto(tamañoHuerto, numParcelas, nombresPlantas);
        
        double litros = 50.0;
        regarHuerto(huerto, litros);
    }
}
