package HuertoUrbanoCompartido;

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
}
