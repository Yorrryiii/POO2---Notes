package HuertoUrbanoCompartido;

import Excepciones.AguaInsuficienteException;

public class HuertoUrbano {
    private double tamano;
    private Parcela[] parcelas;

    public HuertoUrbano(double tamano, Parcela[] parcelas) {
        this.tamano = tamano;
        this.parcelas = parcelas;
    }

    public HuertoUrbano(int tamano) {
        this.tamano = tamano;
        this.parcelas = new Parcela[0];
    }

    public double getMetrosCuadrados() {
        return tamano;
    }

    public Parcela[] getParcelas() {
        return parcelas;
    }

    public void setParcelas(Parcela[] parcelas) {
        this.parcelas = parcelas;
    }

    public void addParcela(Parcela parcela) {
        Parcela[] parcelas = getParcelas();
        Parcela[] newParcelas = new Parcela[parcelas.length + 1];
        for (int i = 0; i < parcelas.length; i++) {
            newParcelas[i] = parcelas[i];
        }
        newParcelas[newParcelas.length - 1] = parcela;
        setParcelas(newParcelas);
    }

    public double getSuperficieTotal() {
        double superficieTotal = 0;
        for (Parcela parcela : getParcelas()) {
            superficieTotal += parcela.getMetrosCuadrados();
        }
        return superficieTotal;
    }

    public double getSuperficieDisponible() {
        return getMetrosCuadrados() - getSuperficieTotal();
    }

    public double getSuperficieOcupada() {
        return getSuperficieTotal();
    }

    public void regar(int litrosAgua) throws AguaInsuficienteException {
        for (Parcela parcela : getParcelas()) {
            for (Cultivo cultivo : parcela.getCultivos()) {
                int litrosPorPlanta = 0;
                switch (cultivo.getNecesidadAgua()) {
                    case "Alta":
                        litrosPorPlanta = 3;
                        break;
                    case "Media":
                        litrosPorPlanta = 2;
                        break;
                    case "Baja":
                        litrosPorPlanta = 1;
                        break;
                }
                int litrosNecesarios = litrosPorPlanta * cultivo.getCantidadPlantas();
                if (litrosAgua < litrosNecesarios) {
                    throw new AguaInsuficienteException("No hay suficiente agua para regar todas las plantas");
                }
                litrosAgua -= litrosNecesarios;
            }
        }
        System.out.println("He regado todas las plantas del huerto");
    }
}
