package empresa;

public class ManagerConSuerte implements Trabajador {
    protected int suerte;

    public ManagerConSuerte() {
        this.suerte = (int) (Math.random() * 1000 + 1);
    }

    public void trabaja(Empresa empresa) {
        if (Math.random() < 0.1) {
            empresa.aumentarDinero(this.suerte);
            empresa.aumentarTrabajoPendiente(this.suerte / 2);
        }
        empresa.reducirDinero(this.suerte / 10);
    }
}
