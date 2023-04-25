package HuertoUrbanoCompartido;

import java.io.*;
import java.util.*;

public class Persistencia {
    public static void toFile(HuertoUrbano huerto, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(huerto.getMetrosCuadrados() + "\n");
        for (Parcela parcela : huerto.getParcelas()) {
            writer.write(parcela.getCliente().getId() + " " + parcela.getMetrosCuadrados() + "\n");
            for (Cultivo cultivo : parcela.getCultivos()) {
                writer.write(cultivo.getNombre() + " " + cultivo.getCantidadPlantas() + "\n");
            }
        }
        writer.close();
    }

    public static HuertoUrbano fromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int metrosCuadrados = Integer.parseInt(reader.readLine());
        HuertoUrbano huerto = new HuertoUrbano(metrosCuadrados);
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            int idCliente = Integer.parseInt(parts[0]);
            int metrosCuadradosParcela = Integer.parseInt(parts[1]);
            Parcela parcela = new Parcela(idCliente, metrosCuadradosParcela);
            huerto.addParcela(parcela);
            for (int i = 2; i < parts.length; i += 2) {
                String nombreCultivo = parts[i];
                int cantidadPlantas = Integer.parseInt(parts[i + 1]);
                Cultivo cultivo = new Cultivo(nombreCultivo, cantidadPlantas);
                parcela.addCultivo(cultivo);
            }
        }
        reader.close();
        return huerto;
    }

    public static void toJerarquia(HuertoUrbano huerto, String folderName) throws IOException {
        File folder = new File(folderName + "/huerto_" + huerto.getMetrosCuadrados());
        folder.mkdir();
        for (Parcela parcela : huerto.getParcelas()) {
            File subfolder = new File(folder, "parcela_" + parcela.getCliente().getId());
            subfolder.mkdir();
            int count = 1;
            for (Cultivo cultivo : parcela.getCultivos()) {
                File file = new File(subfolder, cultivo.getNombre() + count);
                file.createNewFile();
                count++;
            }
        }
    }
}
