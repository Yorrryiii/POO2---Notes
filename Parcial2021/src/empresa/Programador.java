package empresa;

public class Programador implements Trabajador{
    protected int horasTrabajadas;
    protected int sueldo;

    public Programador() {
        this.horasTrabajadas = (int) (Math.random() * 6 + 5);
        this.sueldo = (int) (Math.random() * 6 + 5);
    }

    public void trabaja(Empresa empresa) {
        empresa.reducirDinero(this.sueldo);
        empresa.reducirTrabajoPendiente(this.horasTrabajadas);
    }
}
