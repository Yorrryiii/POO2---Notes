package empresa;

public class Manager implements Trabajador {
    public void trabaja(Empresa empresa) {
        if (Math.random() < 0.1) {
            empresa.aumentarDinero(100);
            empresa.aumentarTrabajoPendiente(100);
        }
    }
}
