import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ComparePersonas {
    public static void main(String[] args) {
        Persona p1 = new Persona("Mario", 10);
        Persona p2 = new Persona("Mario", 20);

        System.out.println(p1);
        System.out.println(p2);

        // p1.name = "Pepe";

        System.out.println(p2.name);

        if(p1 == p2) System.out.println("Son iguales con ==");
        else if(p1.equals(p2)) System.out.println("Son iguales con equals");
        else {
            System.out.println("No son == ni equals");
            System.out.println(p1);
            System.out.println(p2);
        }

        Set<Persona> personas = new HashSet<Persona>();

        personas.add(p1);
        personas.add(p1);
        personas.add(p1);

        System.out.println("Tamaño del set: " + personas.size());

        List<Persona> personaLista = new LinkedList<>();

        personaLista.add(p1);
        personaLista.add(p1);

        if(personaLista.contains(p2)) System.out.println("Está en la lista");

        System.out.println("Tamaño de la lista: " + personaLista.size());
    }
}
