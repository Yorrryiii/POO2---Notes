import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio1Conjunto {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        Set<Integer> setUnion = new HashSet<Integer>();
        Set<Integer> setIntersection = new HashSet<Integer>();


        for(int i = 0; i < 10; i++){
            set1.add(ThreadLocalRandom.current().nextInt(0, 11));
        }
        
        for(int i = 0; i < 10; i++){
            set2.add(ThreadLocalRandom.current().nextInt(0, 11));
        }

        System.out.println(set1 + " - " + set1.size());
        System.out.println(set2 + " - " + set2.size());

        // UNIÓN
        setUnion.addAll(set1);
        setUnion.addAll(set2);
        // set1.addAll(set2);

        System.out.println("Unión:" + setUnion + " - " + setUnion.size());

        // INTERSECCIÓN
        setIntersection.addAll(set1);
        setIntersection.retainAll(set2);

        System.out.println("Intersección:" + setIntersection + " - " + setIntersection.size());

        
        // Imprimir los sets
        Iterator<Integer> iter = set1.iterator();

        while(iter.hasNext()){
            Integer aux = iter.next();

            System.out.println(aux + " va a ser procesado");

            System.out.println("El siguiente del set: " + iter.next());

            // Voy a quitar los pares
            if(aux % 2 == 0) iter.remove();
        }

        // NO SE DEBERIA HACER DE ESTA FORMA
            // for(int i = 0; i < set1.size(); i++){
            //     System.out.println("El siguiente del set: " + iter.next());
            // }


        // Creacion de un HashMap
        HashMap<Integer, String> mapa = new HashMap<Integer, String>();

        mapa.put(17, "Diecisiete");
        mapa.put(18, "Dieciocho");
        
    }
}
