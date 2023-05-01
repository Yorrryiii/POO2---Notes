package Excepciones;

import HuertoUrbanoCompartido.HuertoUrbano;

public class PruebaRiego {
    
    public static void regarHuerto(HuertoUrbano huerto, int litros) {
        try {
            huerto.regar(litros);
            System.out.println("Se han utilizado " + litros + " litros de agua para regar el huerto.");
        } catch (Exception e) {
            System.out.println("No ha habido suficiente agua para regar el huerto.");
            regarHuerto(huerto, litros + 10);
        }
    }
}

