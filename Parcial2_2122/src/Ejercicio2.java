

import excepciones.PersonaNotFoundException;
import otrasClases.HelperPais;
import poblacion.Pais;
import poblacion.Persona;
import poblacion.Provincia;

import java.util.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        // Crear conjunto de nombres y apellidos
        Set<String> nombres = new HashSet<>(Arrays.asList("Pepe", "Juan", "Marcos", "Lucia", "Maria"));
        Set<String> apellidos = new HashSet<>(Arrays.asList("Gomez", "Perez", "Sanchez", "Garcia", "Lopez"));

        // Crear país
        Pais pais = new Pais("España");

        int numProvincias = 50;
        int minPersonas = 20;
        int maxPersonas = 50;

        for (int i = 0; i < numProvincias; i++) {
            Provincia provincia = new Provincia("Provincia " + (i + 1));

            // Determinar el número de personas de forma aleatoria entre el mínimo y máximo dado
            int numPersonas = (int) (Math.random() * (maxPersonas - minPersonas + 1)) + minPersonas;

            // Crear y añadir personas a la provincia
            for (int j = 0; j < numPersonas; j++) {
                Persona persona = HelperPais.createPersona(nombres, apellidos);
                provincia.addPersona(persona);
            }

            // Añadir la provincia al país
            pais.addProvincia(provincia);
        }

        // Buscar una persona existente
        try {
            List<Persona> personasEncontradas = HelperPais.buscaPersonas(pais, "Juan", "Gomez", "Sanchez");
            System.out.println("Se encontraron " + personasEncontradas.size() + " personas con el nombre y apellidos dados:");
            for (Persona persona : personasEncontradas) {
                System.out.println(persona);
            }
        } catch (PersonaNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Buscar una persona inexistente
        try {
            List<Persona> personasEncontradas = HelperPais.buscaPersonas(pais, "Maria", "Perez", "Gonzalez");
            System.out.println("Se encontraron " + personasEncontradas.size() + " personas con el nombre y apellidos dados:");
            for (Persona persona : personasEncontradas) {
                System.out.println(persona);
            }
        } catch (PersonaNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
