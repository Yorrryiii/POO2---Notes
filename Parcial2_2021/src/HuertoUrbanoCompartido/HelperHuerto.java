package huertoUrbanoCompartido;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class HelperHuerto {
    private static final String[] APELLIDOS = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller"};
    private static final String[] TELEFONOS = {"123456789", "987654321", "555555555", "111111111", "999999999"};
    
    public static HuertoUrbano createHuerto(double tamaño, int numParcelas, Set<String> nombresPlantas) {
        HuertoUrbano huerto = new HuertoUrbano(tamaño);
        
        Random random = new Random();
        
        for (String nombrePlanta : nombresPlantas) {
            Cliente cliente = createCliente();
            double tamañoParcela = tamaño / numParcelas;
            
            Parcela parcela = new Parcela(tamañoParcela, cliente);
            
            Cultivo cultivo = new Cultivo(nombrePlanta, getRandomNecesidadesAgua(), getRandomCantidadPlantas());
            parcela.addCultivo(cultivo);
            
            huerto.addParcela(parcela);
        }
        
        return huerto;
    }
    
    private static Cliente createCliente() {
        String nombre = getRandomElement(APELLIDOS);
        String apellido = getRandomElement(APELLIDOS);
        String telefono = getRandomElement(TELEFONOS);
        
        return new Cliente(nombre, apellido, telefono);
    }
    
    private static String getRandomElement(String[] array) {
        int randomIndex = new Random().nextInt(array.length);
        return array[randomIndex];
    }
    
    private static String getRandomNecesidadesAgua() {
        String[] necesidadesAgua = {"alta", "media", "baja"};
        int randomIndex = new Random().nextInt(necesidadesAgua.length);
        return necesidadesAgua[randomIndex];
    }
    
    private static int getRandomCantidadPlantas() {
        return new Random().nextInt(100) + 1; // Generate random number between 1 and 100
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
        
        HuertoUrbano huerto = createHuerto(tamañoHuerto, numParcelas, nombresPlantas);
        
        List<Parcela> parcelas = huerto.getParcelas();
        
        System.out.println("Huerto Urbano:");
        System.out.println("Tamaño: " + huerto.getTamaño());
        System.out.println("Número de parcelas: " + parcelas.size());
        System.out.println("------------------------");
        
        for (Parcela parcela : parcelas) {
            System.out.println("Tamaño de parcela: " + parcela.getTamaño());
            System.out.println("Cliente: " + parcela.getCliente().getNombre() + " " + parcela.getCliente().getApellido());
            System.out.println("Cultivos en la parcela:");
            List<Cultivo> cultivos = parcela.getCultivos();
            
            for (Cultivo cultivo : cultivos) {
                System.out.println("Nombre: " + cultivo.getNombre());
                System.out.println("Necesidades de agua: " + cultivo.getNecesidadesAgua());
                System.out.println("Cantidad de plantas: " + cultivo.getCantidadPlantas());
                System.out.println("------------------------");
            }
            
            System.out.println("========================");
        }
    }
}



