package HuertoUrbanoCompartido;

public class Parcela {
    private double tamano;
    private Cliente cliente;
    private Cultivo[] cultivos;

    public Parcela(double tamano, Cliente cliente, Cultivo[] cultivos) {
        this.tamano = tamano;
        this.cliente = cliente;
        this.cultivos = cultivos;
    }

    public Parcela(String nombrePlanta, double superficie, int cantidad, double distanciaAcomunales) {
        this.tamano = superficie;
        this.cliente = new Cliente("Cliente", "Cliente", "Cliente");
        this.cultivos = new Cultivo[] { new Cultivo(nombrePlanta, "Baja", cantidad) };
    }

    public Parcela(int idCliente, int metrosCuadradosParcela) {
        this.tamano = metrosCuadradosParcela;
        this.cliente = new Cliente("Cliente", "Cliente", "Cliente");
        this.cultivos = new Cultivo[] { new Cultivo("Planta", "Baja", 1) };
    }

    public double getMetrosCuadrados() {
        return tamano;
    }

    public void setTamano(double tamano) {
        this.tamano = tamano;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Cultivo[] getCultivos() {
        return cultivos;
    }

    public void setCultivos(Cultivo[] cultivos) {
        this.cultivos = cultivos;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void addCultivo(Cultivo cultivo) {
        Cultivo[] cultivos = getCultivos();
        Cultivo[] newCultivos = new Cultivo[cultivos.length + 1];
        for (int i = 0; i < cultivos.length; i++) {
            newCultivos[i] = cultivos[i];
        }
        newCultivos[newCultivos.length - 1] = cultivo;
        setCultivos(newCultivos);
    }

    @Override
    public String toString() {
        return "Parcela [cliente=" + cliente + ", cultivos=" + cultivos + ", tamano=" + tamano + "]";
    }
}
