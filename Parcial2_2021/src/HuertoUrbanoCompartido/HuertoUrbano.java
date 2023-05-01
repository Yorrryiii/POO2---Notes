package huertoUrbanoCompartido;

import java.util.ArrayList;
import java.util.List;

import excepciones.AguaInsuficienteException;

public class HuertoUrbano {
    private double tamaño;
    private List<Parcela> parcelas;

    public HuertoUrbano(double tamaño) {
        this.tamaño = tamaño;
        this.parcelas = new ArrayList<>();
    }

    public double getTamaño() {
        return tamaño;
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void addParcela(Parcela parcela) {
        parcelas.add(parcela);
    }

    public void regar(double litrosAgua) throws AguaInsuficienteException {
        double litrosRestantes = litrosAgua;
        
        for (Parcela parcela : parcelas) {
            List<Cultivo> cultivos = parcela.getCultivos();
            
            for (Cultivo cultivo : cultivos) {
                int cantidadPlantas = cultivo.getCantidadPlantas();
                String necesidadesAgua = cultivo.getNecesidadesAgua();
                double litrosConsumidos = 0.0;
                
                if (necesidadesAgua.equals("alta")) {
                    litrosConsumidos = cantidadPlantas * 3;
                } else if (necesidadesAgua.equals("media")) {
                    litrosConsumidos = cantidadPlantas * 2;
                } else if (necesidadesAgua.equals("baja")) {
                    litrosConsumidos = cantidadPlantas * 1;
                }
                
                if (litrosConsumidos > litrosRestantes) {
                    throw new AguaInsuficienteException("Agua insuficiente. No se pudo regar todo.");
                }
                
                litrosRestantes -= litrosConsumidos;
            }
        }
        
        System.out.println("He regado todo.");
    }
}

