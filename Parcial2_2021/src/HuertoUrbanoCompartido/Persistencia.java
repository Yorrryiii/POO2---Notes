package huertoUrbanoCompartido;

import java.io.*;
import java.util.*;

public class Persistencia {
    public static void toFile(HuertoUrbano huerto, String nombreFichero) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreFichero))) {
            writer.println("Tamaño: " + huerto.getTamaño());
            writer.println("Número de parcelas: " + huerto.getParcelas().size());
            
            for (Parcela parcela : huerto.getParcelas()) {
                Cliente cliente = parcela.getCliente();
                writer.println("------------------------");
                writer.println("Parcela:");
                writer.println("Tamaño: " + parcela.getTamaño());
                writer.println("Cliente: " + cliente.getNombre() + " " + cliente.getApellido());
                writer.println("Cultivos en la parcela:");
                
                for (Cultivo cultivo : parcela.getCultivos()) {
                    writer.println("Nombre: " + cultivo.getNombre());
                    writer.println("Necesidades de agua: " + cultivo.getNecesidadesAgua());
                    writer.println("Cantidad de plantas: " + cultivo.getCantidadPlantas());
                    writer.println("------------------------");
                }
            }
            
            System.out.println("HuertoUrbano guardado en el fichero: " + nombreFichero);
        } catch (IOException e) {
            System.out.println("Error al guardar el HuertoUrbano en el fichero: " + nombreFichero);
        }
    }
    
    public static HuertoUrbano fromFile(String nombreFichero) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            
            double tamaño = 0.0;
            int numParcelas = 0;
            Set<String> nombresPlantas = new HashSet<>();
            
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Tamaño: ")) {
                    tamaño = Double.parseDouble(linea.substring("Tamaño: ".length()));
                } else if (linea.startsWith("Número de parcelas: ")) {
                    numParcelas = Integer.parseInt(linea.substring("Número de parcelas: ".length()));
                } else if (linea.startsWith("Nombre: ")) {
                    String nombrePlanta = linea.substring("Nombre: ".length());
                    nombresPlantas.add(nombrePlanta);
                }
            }
            
            HuertoUrbano huerto = HelperHuerto.createHuerto(tamaño, numParcelas, nombresPlantas);
            
            System.out.println("HuertoUrbano cargado desde el fichero: " + nombreFichero);
            return huerto;
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + nombreFichero);
            return null;
        }
    }
    
    public static void toJerarquia(HuertoUrbano huerto) {
        String carpetaRaiz = "huerto_" + huerto.getTamaño();
        
        File raiz = new File(carpetaRaiz);
        raiz.mkdir();
        
        for (Parcela parcela : huerto.getParcelas()) {
            Cliente cliente = parcela.getCliente();
            String nombreCarpetaParcela = "parcela_" + cliente.getIdCliente();
            
            File carpetaParcela = new File(raiz, nombreCarpetaParcela);
            carpetaParcela.mkdir();
            
            int contador = 1;
            for (Cultivo cultivo : parcela.getCultivos()) {
                String nombreArchivoPlanta = cultivo.getNombre() + contador;
                File archivoPlanta = new File(carpetaParcela, nombreArchivoPlanta);
                try {
                    archivoPlanta.createNewFile();
                } catch (IOException e) {
                    System.out.println("Error al crear el archivo para la planta: " + nombreArchivoPlanta);
                }
                contador++;
            }
        }
        
        System.out.println("Estructura de jerarquía creada para el HuertoUrbano.");
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
        
        String nombreFichero = "huerto.txt";
        toFile(huerto, nombreFichero);
        
        HuertoUrbano huertoCargado = fromFile(nombreFichero);
        
        if (huertoCargado != null) {
            toJerarquia(huertoCargado);
        }
    }
}


