package otrasClases;

import java.util.*;

import excepciones.PersonaNotFoundException;
import poblacion.Pais;
import poblacion.Persona;
import poblacion.Provincia;

public class HelperPais {
    public static Persona createPersona(Set<String> nombres, Set<String> apellidos) {
        // Generar un nombre aleatorio
        int nombreIndex = new Random().nextInt(nombres.size());
        String nombre = (String) nombres.toArray()[nombreIndex];

        // Generar dos apellidos aleatorios
        List<String> apellidosList = new ArrayList<>(apellidos);
        Collections.shuffle(apellidosList);
        String apellido1 = apellidosList.get(0);
        String apellido2 = apellidosList.get(1);

        // Generar una edad aleatoria entre 1 y 100
        int edad = new Random().nextInt(100) + 1;

        return new Persona(nombre, apellido1, apellido2, edad);
    }

    public static Pais createPais(String nombrePais, int numProvincias, int minPersonas, int maxPersonas, Set<String> nombres, Set<String> apellidos) {
        Pais país = new Pais(nombrePais);

        for (int i = 0; i < numProvincias; i++) {
            Provincia provincia = new Provincia("Provincia " + (i + 1));

            // Determinar el número de personas de forma aleatoria entre el mínimo y máximo dado
            int numPersonas = new Random().nextInt(maxPersonas - minPersonas + 1) + minPersonas;

            // Crear y añadir personas a la provincia
            for (int j = 0; j < numPersonas; j++) {
                Persona persona = createPersona(nombres, apellidos);
                provincia.addPersona(persona);
            }

            // Añadir la provincia al país
            país.addProvincia(provincia);
        }

        return país;
    }

    public static List<Persona> buscaPersonas(Pais pais, String nombre, String apellido1, String apellido2) throws PersonaNotFoundException {
        List<Persona> personasEncontradas = new ArrayList<>();

        for (Provincia provincia : pais.getProvincias()) {
            for (Persona persona : provincia.getPersonas()) {
                if (persona.getNombre().equals(nombre) && persona.getApellido1().equals(apellido1) && persona.getApellido2().equals(apellido2)) {
                    personasEncontradas.add(persona);
                }
            }
        }

        if (personasEncontradas.isEmpty()) {
            throw new PersonaNotFoundException("No se encontraron personas con el nombre y apellidos dados en el país.");
        }

        return personasEncontradas;
    }
}
