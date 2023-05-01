package huertoUrbanoCompartido;

import java.util.ArrayList;
import java.util.List;

public class Parcela {
    private double tamaño;
    private Cliente cliente;
    private List<Cultivo> cultivos;

    public Parcela(double tamaño, Cliente cliente) {
        this.tamaño = tamaño;
        this.cliente = cliente;
        this.cultivos = new ArrayList<>();
    }

    public double getTamaño() {
        return tamaño;
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cultivo> getCultivos() {
        return cultivos;
    }

    public void addCultivo(Cultivo cultivo) {
        cultivos.add(cultivo);
    }
}