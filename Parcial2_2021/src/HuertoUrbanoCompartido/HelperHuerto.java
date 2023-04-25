package HuertoUrbanoCompartido;

import java.util.Random;
import java.util.Set;

public class HelperHuerto {
    public static HuertoUrbano createHuerto(double tamano, int numParcelas, Set<String> nombresPlantas) {
        Parcela[] parcelas = new Parcela[numParcelas];
        int tamanoParcela = (int) tamano / numParcelas;
        Random rnd = new Random();
        for (int i = 0; i < numParcelas; i++) {
            String nombrePlanta = nombresPlantas.toArray()[i % nombresPlantas.size()].toString();
            double superficie = rnd.nextDouble() * tamanoParcela;
            int cantidad = rnd.nextInt(10) + 1;
            double distanciaAcomunales = rnd.nextDouble() * 10;
            parcelas[i] = new Parcela(nombrePlanta, superficie, cantidad, distanciaAcomunales);
        }
        return new HuertoUrbano(tamano, parcelas);
    }
}
